package web.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import core.vo.GoogleData;

// 一般會員登出
@RestController
@RequestMapping({ "member/logout", "/logout" })
public class LogoutController {

	@DeleteMapping
	protected void logout(SessionStatus sessionStatus, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		GoogleData gd = (GoogleData) session.getAttribute("Google");
		System.out.println("執行登出");
		System.out.println("第一次" + session);
		System.out.println("第一次" + gd);
		sessionStatus.setComplete();
		session.invalidate();
		session = request.getSession(false);
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setPath("/chill-tribe");
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		response.addCookie(cookie);
		response.setHeader("Set-Cookie", "JSESSIONID=; Path=/chill-tribe; Max-Age=0; HttpOnly; Secure; SameSite=Lax");
		System.out.println("第二次" + session);
		System.out.println("第二次" + gd);
	}
}
