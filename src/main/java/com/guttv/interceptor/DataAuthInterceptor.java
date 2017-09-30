package com.guttv.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.guttv.util.DataThreadLocalUtil;

@Component
public class DataAuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(DataAuthInterceptor.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String role = request.getHeader("userDataRole");
		Set<Integer> roles = null;
		if (StringUtils.isNotBlank(role)) {
			String[] roleIds = role.split(",");
			roles = new HashSet<>();
			for (String string : roleIds) {
				roles.add(Integer.valueOf(string));
			}
		}else{
			Object object = session.getAttribute("userDataRole");
			if (object != null) {
				roles = (Set<Integer>)object;
			}
		}
		DataThreadLocalUtil.setData(roles);
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		DataThreadLocalUtil.removeData();
	}
}
