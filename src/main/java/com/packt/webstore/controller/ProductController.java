package com.packt.webstore.controller;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.NoProductsFoundUnderCategoryException;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;
import com.packt.webstore.validator.ProductValidator;

@Controller
@RequestMapping("/market")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductValidator productValidator;

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setValidator(productValidator);
		binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category",
				"unitsInStock", "condition", "productImage", "productUserManual", "language");
	}

	@RequestMapping("/products/invalidPromoCode")
	public String invalidPromoCode() {
		return "invalidPromoCode";
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleProductNotFoundException(HttpServletRequest request, ProductNotFoundException exception) {
		logger.info("handleProductNotFoundException");

		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}

	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, Model model) {
		logger.info("getAddNewProductForm");

		model.addAttribute("newProduct", newProduct);

		return "addProduct";
	}

	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct,
			BindingResult result, HttpServletRequest request) {
		logger.info("processAddNewProductForm");

		if (result.hasErrors()) {
			return "addProduct";
		}

		String[] suppressedFields = result.getSuppressedFields();

		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attemting to bind disallowrd fields : "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		Optional<MultipartFile> productImage = Optional.ofNullable(newProduct.getProductImage());
		Optional<MultipartFile> productUserManual = Optional.ofNullable(newProduct.getProductUserManual());
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		if (productImage.isPresent()) {
			try {
				productImage.get().transferTo(
						new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".jpg"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		if (productUserManual.isPresent()) {
			try {
				productUserManual.get()
						.transferTo(new File(rootDirectory + "resources\\pdf\\" + newProduct.getProductId() + ".pdf"));
			} catch (Exception e) {
				throw new RuntimeException("Product User Manual saving failed", e);
			}
		}

		productService.addProduct(newProduct);

		return "redirect:/market/products";
	}

	@RequestMapping("/products/{category}/{price}")
	public String getProductsByCategoryPriceBrand(Model model, @PathVariable("category") String productCategory,
			@MatrixVariable(pathVar = "price") Map<String, List<String>> filterParams,
			@RequestParam("brand") String brand) {

		logger.info("getProductsByCategoryPriceBrand");

		model.addAttribute("product",
				productService.getProductsByCategoryPriceBrand(productCategory, filterParams, brand));

		return "product";
	}

	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(Model model,
			@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams) {
		logger.info("getProductsByFilter");

		model.addAttribute("products", productService.getProductsByFilter(filterParams));

		return "products";
	}

	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		logger.info("getProductsByCategory");

		Optional<List<Product>> productList = Optional.of(productService.getProductsByCategory(productCategory));

		if (productList.get().size() < 1) {
			throw new NoProductsFoundUnderCategoryException();
		}

		model.addAttribute("products", productList.get());

		return "products";
	}

	@RequestMapping("/all")
	public ModelAndView allProducts() {
		logger.info("allProducts");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", productService.getAllProducts());
		modelAndView.setViewName("products");

		return modelAndView;
	}

	@RequestMapping("/products")
	public String list(Model model) {
		logger.info("list");

		model.addAttribute("products", productService.getAllProducts());

		return "products";
	}

	@RequestMapping("/product")
	public String getProductById(Model model, @RequestParam("id") String productId, HttpServletRequest request) {
		logger.info("getProductById");

		Product product = productService.getProductById(productId);

		File productImage = new File(request.getSession().getServletContext().getRealPath("/") + "resources\\images\\"
				+ product.getProductId() + ".jpg");
		File productUserManual = new File(request.getSession().getServletContext().getRealPath("/") + "resources\\pdf\\"
				+ product.getProductId() + ".pdf");

		try {

			byte[] encodedProductImage = Base64.getEncoder().encode(FileUtils.readFileToByteArray(productImage));
			product.setBase64ProductImage(new String(encodedProductImage, StandardCharsets.UTF_8));

			byte[] encodedProductUserManual = Base64.getEncoder().encode(FileUtils.readFileToByteArray(productUserManual));
			product.setBase64ProductUserManual(new String(encodedProductUserManual, StandardCharsets.UTF_8));

		} catch (Exception e) {
			logger.error("Exception : " + e.getMessage(), e);
		}

		model.addAttribute("product", product);

		return "product";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		logger.info("list");

		productService.updateAllStock();

		return "redirect:/market/products";
	}

}
