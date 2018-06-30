package com.raj.ehttp.filters;

import java.io.IOException;
import java.util.Base64;

import javax.annotation.Priority;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raj.ehttp.api.handler.CreateResponseHandler;

@Priority(1)
@Component
public class CatchAllFilter implements Filter {
	@Autowired
	CreateResponseHandler catchAllFilter;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url = ((HttpServletRequest) request).getRequestURI();
		if (!url.startsWith("/echo/")) {

			catchAllFilter.buildResponse(url,response);

		}
		System.err.println(url);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
