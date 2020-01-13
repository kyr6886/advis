package com.noaa.base.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;



public class CookieHelper {
	Cookie[] cookies;
	public CookieHelper(Cookie[] cookies){
		this.cookies=cookies;
	}

	public boolean hasName(String paramCookieName) {
		boolean rs = false;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if ((cookie.getName()).equals(paramCookieName)) {
					rs = true;
					break;
				}
			}
		}
		return rs;
	}
	
	public String getValue(String paramCookieName){
		String rs=null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(paramCookieName)) {
					rs=cookie.getValue();
					break;
				}
			}
		}
		return rs;
	}
	
	public void clear(HttpServletResponse response,String paramCookieName){
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(paramCookieName)) {
					cookie.setMaxAge(0);
					cookie.setValue(null);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
	}
}
