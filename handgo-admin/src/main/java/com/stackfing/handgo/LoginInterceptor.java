package com.stackfing.handgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: fing
 * @Description:
 * @Date: 下午12:50 17-12-27
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private String[] excludedUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		return super.preHandle(request, response, handler);

//		String requestUri = request.getRequestURI();
//		excludedUrls = new String[]{"/admin"};
//		for (String s : excludedUrls) {
//			System.out.println("asf");
//			if (requestUri.endsWith(s)) {
//				return true;
//			}
//		}

		ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
		Cookie[] cookies = request.getCookies();

		String uid = null;
		String token = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("uid")) {
				uid = cookie.getValue();
			}

			if (cookie.getName().equals("token")) {
				token = cookie.getValue();
			}
		}

		if (null != uid && null != token) {
			if (stringStringValueOperations.get(token).equals(uid)) {
				return true;
			}
		}

		return false;
	}

	public void setExcludedUrls(String[] excludedUrls) {
		this.excludedUrls = excludedUrls;
	}
}
