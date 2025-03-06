package web.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;


// 一般會員登出
@RestController
@RequestMapping({"member/logout", "/logout"})
public class LogoutController  {
	
	@GetMapping
	protected void login(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
	}
}
