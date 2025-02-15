package web.member.controller;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Test
public class TestFilter extends HttpFilter {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		Time time = new Time(System.currentTimeMillis());
		System.out.println(time);
		
		chain.doFilter(req, res);
		
		time = new Time(System.currentTimeMillis());
		System.out.println(time);
	}
}
