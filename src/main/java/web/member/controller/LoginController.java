package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import core.util.CommonUtil;
import core.util.Core;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;

// 一般會員登入
@RestController
@RequestMapping({ "member/login", "/login" })
//@WebServlet({"/member/login", "/login"})
public class LoginController {
//	private static final long serialVersionUID = 1L;

	@Autowired
	private MemberService service;

	@GetMapping("{email}/{password}")
//	@Override
	protected Member login(HttpServletRequest request, @PathVariable String email, @PathVariable String password) {
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = new Member();
		if (email == null || password == null) {
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			return member;
		}

		member.setEmail(email);
		member.setPassword(password);
		member = service.login(member);
		if (member.isSuccessful()) {
//			if (request.getSession(false) != null) {
//				request.changeSessionId();
			HttpSession existingSession = request.getSession(false);
			if (existingSession != null) {
				existingSession.invalidate();
			}
			HttpSession session = request.getSession(true);
//			}
//			final HttpSession session = request.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("member", member);
			session.setAttribute("memberid", member.getMemberid());
		}
		return member;
//		try {
//			Gson gson = new Gson();
//			Member member = gson.fromJson(req.getReader(), Member.class);
//			MemberService service = new MemberServiceImpl();
//			member = service.login(member);
//			if (member != null) {
//				 if (req.getSession(false) != null) {
//				 req.changeSessionId(); 
//				 } 
//				 req.getSession().setAttribute("member", member);
//				} 
//				 Core core = new Core();
//				 core.setSuccessful(member != null);
//				 resp.getWriter().write(gson.toJson(core));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
	}
}
