package web.member.controller;

import java.io.IOException;

import javax.naming.NamingException;
//import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Addresses;
//import web.test.member.dao.MemberDao;
//import web.test.member.dao.impl.MemberDaoImpl;
import web.member.vo.Member;

// 一般會員地址查詢
@RestController
@RequestMapping({"member/findaddress", "/findaddress"})
//@WebServlet("/member/findaddress")
public class FindAddressController {
	
	@Autowired
	private MemberService service;

	@GetMapping("{memberid}")
//	@Override
	public Addresses findaddressAll (@PathVariable Integer memberid) {
		return service.selectaddress(memberid);
	}
//	public Member findaddressAll (@PathVariable Integer memberid) {
//		return service.selectaddress(memberid);
//	}
	
	
	
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Member member = (Member) req.getSession().getAttribute("member");
////		Gson gson = new Gson();
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy-MM-dd")
//				.create();
//		member = service.selectaddress(member);
//		resp.getWriter().write(gson.toJson(member));
//	}
}
