package com.packt.webstore.configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;

import com.packt.webstore.domain.Product;
import com.packt.webstore.interceptor.ProcessingTimeLogInterceptor;
import com.packt.webstore.interceptor.PromoCodeInterceptor;
import com.packt.webstore.interceptor.RequestMappingInterceptor;
import com.packt.webstore.validator.ProductImageValidator;
import com.packt.webstore.validator.ProductValidator;
import com.packt.webstore.validator.UnitsInStockValidator;

@Configuration
@EnableWebMvc
@ComponentScan("com.packt.webstore")
public class WebApplicationContextConfig implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(WebApplicationContextConfig.class);

	@Override
	public Validator getValidator() {
		return validator();
	}

	// in controller level put in interceptor
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("addInterceptors");

		registry.addInterceptor(new ProcessingTimeLogInterceptor());

		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor);
		registry.addInterceptor(promoCodeInterceptor()).addPathPatterns("/**/market/products/specialOffer");
		// registry.addInterceptor(initRequestMappingInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.info("addResourceHandlers");

		registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
		registry.addResourceHandler("/pdf/**").addResourceLocations("/resources/pdf/");
		registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
	}

	// for Matrix variable
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		logger.info("configurePathMatch");

		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		logger.info("configureDefaultServletHandling");

		// configurer.enable("ErrorHandlingServlet");
	}

	@Bean
	public ProductValidator productValidator() {
		Set<Validator> springValidators = new HashSet<>();
		springValidators.add(new UnitsInStockValidator());
		springValidators.add(new ProductImageValidator());

		ProductValidator productValidator = new ProductValidator();
		productValidator.setSpringValidators(springValidators);

		return productValidator;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public HandlerInterceptor initRequestMappingInterceptor() {
		logger.info("initRequestMappingInterceptor");

		RequestMappingInterceptor requestMappingInterceptor = new RequestMappingInterceptor();
		requestMappingInterceptor.setErrorRedirect("errorPage");
		return requestMappingInterceptor;
	}

	@Bean
	public HandlerInterceptor promoCodeInterceptor() {
		logger.info("promoCodeInterceptor");

		PromoCodeInterceptor promoCodeInterceptor = new PromoCodeInterceptor();
		promoCodeInterceptor.setPromoCode("OFF3R");
		promoCodeInterceptor.setOfferRedirect("market/products");
		promoCodeInterceptor.setErrorRedirect("invalidPromoCode");
		return promoCodeInterceptor;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		return resolver;
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		logger.info("contentNegotiatingViewResolver");

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		List<View> views = new ArrayList<>();
		views.add(jsonView());
		views.add(xmlView());
		resolver.setDefaultViews(views);
		return resolver;
	}

	@Bean
	public MappingJackson2JsonView jsonView() {
		logger.info("jsonView");

		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);
		return jsonView;
	}

	@Bean
	public MarshallingView xmlView() {
		logger.info("xmlView");

		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Product.class);
		MarshallingView xmlView = new MarshallingView(marshaller);
		return xmlView;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		logger.info("multipartResolver");

		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		// resolver.setMaxUploadSize(10240000);
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		logger.info("messageSource");

		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		logger.info("getInternalResourceViewResolver");

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");

		return resolver;
	}

	// @Override
	// public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver>
	// handler) {
	// logger.info("configureHandlerExceptionResolvers");
	//
	// HandlerExceptionResolver resolver = new HandlerExceptionResolver() {
	//
	// @Override
	// public ModelAndView resolveException(HttpServletRequest request,
	// HttpServletResponse response,
	// Object handler, Exception ex) {
	// return new ModelAndView("errorPage");
	// }
	//
	// };
	//
	// handler.add(resolver);
	// }

}
