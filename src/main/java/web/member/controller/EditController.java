package web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import web.member.service.MemberService;
import web.member.vo.Member;

// 一般會員修改個人資料
@RestController
@RequestMapping("member/memberedit")
@SessionAttributes("member")
public class EditController  {
	@Autowired
	private MemberService service;
	
	@PutMapping
//	@ResponseBody
	public Member memberedit(@RequestBody Member reqMember, @SessionAttribute("member") Member seMember) {
		final Integer memberid = seMember.getMemberid();
		reqMember.setMemberid(memberid);
		return service.edit(reqMember);
	}
}
