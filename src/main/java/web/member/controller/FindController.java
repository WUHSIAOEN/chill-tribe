package web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.member.service.MemberService;
import web.member.vo.Member;

// 一般會員查詢
@RestController
@RequestMapping({ "member/find", "/find" })
public class FindController {

	@Autowired
	private MemberService service;

	@GetMapping("{memberid}")
	public Member find(@PathVariable Integer memberid) {
		return service.selectByMemberID(memberid);
	}
}
