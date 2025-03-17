package web.member.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Addresses;
import web.member.vo.Member;

// 一般會員地址新增
@RestController
@RequestMapping("member/memberaddress")
//@WebServlet("/member/memberaddress")
public class UpAddressController {
	
	@Autowired
	private MemberService service;
	
	
	@PostMapping
//	@Override
	public Addresses upaddress(@RequestBody Addresses addresses) {
		addresses = service.upaddress(addresses);
		return addresses;
	}
       
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		Gson gson = new Gson();
//		Member member = gson.fromJson(req.getReader(), Member.class);
//		
//		String errMsg = service.upaddress(member);
//		
//		JsonObject respBody = new JsonObject();
//		respBody.addProperty("successful", errMsg == null);
//		respBody.addProperty("errMsg", errMsg);
//		resp.getWriter().write(respBody.toString());
//	}
}
