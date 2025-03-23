package web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.member.service.MemberService;
import web.member.vo.Addresses;

// 一般會員地址新增
@RestController
@RequestMapping("member/memberaddress")
public class UpAddressController {
	
	@Autowired
	private MemberService service;
	
	@PostMapping
	public Addresses upaddress(@RequestBody Addresses addresses) {
		addresses = service.upaddress(addresses);
		return addresses;
	}
}
