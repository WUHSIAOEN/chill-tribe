package web.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.member.service.MemberService;
import web.member.vo.Member;

// 一般會員登入
@RestController
@RequestMapping({ "member/login", "/login" })
public class LoginController {

	@Autowired
	private MemberService service;

	@GetMapping("{email}/{password}")
	protected Member login(HttpServletRequest request, @PathVariable String email, @PathVariable String password) {
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
			HttpSession existingSession = request.getSession(false);
			if (existingSession != null) {
				existingSession.invalidate();
			}
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedin", true);
			session.setAttribute("member", member);
			session.setAttribute("memberid", member.getMemberid());
		}
		return member;
	}
}
