package web.member.controller;

//import java.io.IOException;

import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.member.vo.Member;

// Filter限制 除了login
//@WebFilter("/member/*")
public class LoginFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
		try {
			if (req.getServletPath().startsWith("/member/login") ||
				req.getServletPath().startsWith("/member/register")) {
				chain.doFilter(req, res);
			}	else {
				HttpSession session = req.getSession();
				Member member = (Member) session.getAttribute("member");
				if (member != null) {
					chain.doFilter(req, res);
				} else {
					String targetPath = req.getRequestURI().toString();
					session.setAttribute("targetPath", targetPath);
					req.getRequestDispatcher("member-register.html").forward(req, res);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
