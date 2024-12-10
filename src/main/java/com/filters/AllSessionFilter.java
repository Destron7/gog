package com.filters;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.entity.UserEntity;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class AllSessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req  = (HttpServletRequest) request;

		HttpSession session = req.getSession(false);
		String uri = req.getRequestURI().toLowerCase();
		
		Boolean status = false;
		
		if(uri.contains("signin") || uri.contains("signup")) {
			status = true;
		}else if(session != null) {
			UserEntity user = (UserEntity) session.getAttribute("user");
			if(user != null) {
				status = true;
			}
		}
		
		if(status) {
			chain.doFilter(request, response);
		}else {
			request.getRequestDispatcher("signin").forward(request, response);
		}
	}
}
