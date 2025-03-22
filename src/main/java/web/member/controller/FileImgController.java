package web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import web.member.service.MemberService;
import web.member.vo.Member;

// 一般會員大頭照
@RestController
@RequestMapping("member/memberimg")
@SessionAttributes("member")
public class FileImgController  {
	@Autowired
	private MemberService service;

	@PostMapping
	public Member memberimg(@RequestBody Member reqMember, @SessionAttribute("member") Member seMember) {
		final Integer memberid = seMember.getMemberid();
		reqMember.setMemberid(memberid);
		return service.updateimg(reqMember);
	}
}
