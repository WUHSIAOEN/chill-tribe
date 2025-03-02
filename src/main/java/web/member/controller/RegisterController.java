package web.member.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;
import web.member.vo.MemberOther;

// 一般會員註冊
@RestController
@RequestMapping("member/register")
//@WebServlet("/member/register")
public class RegisterController  {
//	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberService service;
	
//	public void init() throws ServletException {
//		 try {
//			service = new MemberServiceImpl();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
       
	@PostMapping
//	@Override
	public Member register(@RequestBody Member member) {
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			return member;
		}

		
		member = service.register(member);
		return member;
		
//		Gson gson = new Gson();
//		Member member = gson.fromJson(req.getReader(), Member.class);
//		
//		String errMsg = service.register(member);
//		
//		JsonObject respBody = new JsonObject();
//		respBody.addProperty("successful", errMsg == null);
//		respBody.addProperty("errMsg", errMsg);
//		resp.getWriter().write(respBody.toString());
	}
}
