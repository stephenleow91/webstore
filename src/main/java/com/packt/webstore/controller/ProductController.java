package com.packt.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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
import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping("/market")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category",
				"unitsInStock", "condition", "productImage", "productUserManual");
	}

	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, Model model) {
		logger.info("getAddNewProductForm");

		model.addAttribute("newProduct", newProduct);

		return "addProduct";
	}

	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result, HttpServletRequest request) {
		logger.info("processAddNewProductForm");

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
				productImage.get().transferTo(new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".jpg"));
			}catch(Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		if (productUserManual.isPresent()) {
			try {
				productUserManual.get().transferTo(new File(rootDirectory + "resources\\pdf\\" + newProduct.getProductId() + ".pdf"));
			}catch(Exception e) {
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

		model.addAttribute("products", productService.getProductsByCategory(productCategory));

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
	public String getProductById(Model model, @RequestParam("id") String productId) {
		logger.info("getProductById");

		model.addAttribute("product", productService.getProductById(productId));

		return "product";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		logger.info("list");

		productService.updateAllStock();

		return "redirect:/market/products";
	}

}
