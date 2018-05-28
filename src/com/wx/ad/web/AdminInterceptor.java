package com.wx.ad.web;

import java.net.URLEncoder;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import my.web.IUser;
import my.web.RequestContext;
import my.web.RequestUtils;

public class AdminInterceptor extends my.web.ContextInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String strUrl = request.getRequestURI();
		IUser user = RequestContext.get().getLoginUer();

		logger.debug(strUrl);

		if (user == null) {
			String contextpath = request.getContextPath();
			if (contextpath.length() > 0) {
				strUrl = strUrl.substring(contextpath.length());
			}

			String strToUrl = request.getContextPath() + "/admin/login" + "?FromUrl="
					+ URLEncoder.encode(request.getRequestURI(), "utf-8");
			response.sendRedirect(strToUrl);
			return false;
		}

		logger.debug(strUrl);
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	

}
