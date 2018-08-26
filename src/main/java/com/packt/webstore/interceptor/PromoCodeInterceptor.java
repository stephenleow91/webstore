package com.packt.webstore.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PromoCodeInterceptor extends HandlerInterceptorAdapter {

	private String promoCode;
	private String errorRedirect;
	private String offerRedirect;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {

		String givenPromoCode = request.getParameter("promo");

		if (promoCode.equals(givenPromoCode)) {
			// cannot put offerRedirect only, offerRedirect will be appended to current request URL
			// (eg. http://localhost:8181/webstore/market/products/market/products)
			// request.getContextPath() + "/" + offerRedirect = http://localhost:8181/webstore/market/products)
			response.sendRedirect(request.getContextPath() + "/" + offerRedirect);
		} else {
			response.sendRedirect(errorRedirect);
		}

		return false;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getErrorRedirect() {
		return errorRedirect;
	}

	public void setErrorRedirect(String errorRedirect) {
		this.errorRedirect = errorRedirect;
	}

	public String getOfferRedirect() {
		return offerRedirect;
	}

	public void setOfferRedirect(String offerRedirect) {
		this.offerRedirect = offerRedirect;
	}

}
