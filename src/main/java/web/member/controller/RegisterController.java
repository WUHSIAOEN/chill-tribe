package web.member.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import web.member.service.MemberService;
import web.member.vo.Member;

// 一般會員註冊
@RestController
@RequestMapping("member/register")
public class RegisterController {

	@Autowired
	private MemberService service;

	@PostMapping
	public Member register(@RequestBody Member member) {
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			return member;
		}

		member = service.register(member);
		return member;
	}
}
