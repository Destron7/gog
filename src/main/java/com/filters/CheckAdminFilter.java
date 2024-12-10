package com.filters;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import com.entity.UserEntity;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CheckAdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		String url = httpReq.getRequestURL().toString().toLowerCase();
		
		HttpSession session = httpReq.getSession(false);	
		
		int status = -1; 
		
		if(url.contains("admin")) {
			if(session == null) {
				status = 1; //login 
			}else {
				UserEntity user = (UserEntity) session.getAttribute("user");
				if(user.getRole().equals("Admin")) {
					status = 3; //goahead 
				}else {
					status = 1;//login 
				}
			}
			
		}else {
			status = 3; //goahead 
		}
		
		if(status == 1) {
			request.getRequestDispatcher("sigin").forward(request, response);
			
		}else {
			chain.doFilter(request, response);
		}
	 
		//if user req for admin url we need to check user role is admin if yes then we will say go ahead 
		//else we will send them to login page 
		
	}

}
