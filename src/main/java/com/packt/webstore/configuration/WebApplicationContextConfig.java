package com.packt.webstore.configuration;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebMvc
@ComponentScan("com.packt.webstore")
public class WebApplicationContextConfig implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(WebApplicationContextConfig.class);

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

		configurer.enable("ErrorHandlingServlet");
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

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> handler) {
		logger.info("configureHandlerExceptionResolvers");

		HandlerExceptionResolver resolver = new HandlerExceptionResolver() {

			@Override
			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
					Object handler, Exception ex) {
				return new ModelAndView("errorPage");
			}

		};

		handler.add(resolver);
	}

}
