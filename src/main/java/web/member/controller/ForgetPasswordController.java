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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import core.util.SendEmail;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import web.member.service.MemberService;
import web.member.vo.Member;

// 一般會員忘記密碼
@RestController
@RequestMapping({ "member/forgetpassword", "forgetpassword" })
@SessionAttributes("member")
public class ForgetPasswordController {
	@Autowired
	private MemberService service;
	
	@PutMapping
	public Member memberforgetpassword(@RequestBody Member member) {
		
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			return member;
		}
		
		member = service.forgetpassowrd(member);
		
		// 判斷有才發
		if (member != null) {
		// 呼叫SendEmail
		SendEmail sendEmail = new SendEmail();
		
		Region region = Region.AP_NORTHEAST_1;
		SesV2Client sesv2Client = SesV2Client.builder().region(region).build();
		// 發信者
		String sender = "crystalwu@metaage.com.tw";
		// 收件者
		String recipient = member.getEmail();
		// 主旨
		String subject = "忘記密碼通知";
		
		// 取得亂碼
		String newPassword = member.getPassword(); 
		
		// 內容
		String bodyHTML =
				"<html>" + "<head></head>" + "<body>" + "<h1>提供您預設密碼：" + newPassword + "</h1>"
				+ "<p><a href='http://localhost:8080/chill-tribe/chilltribe.html'> 請點擊此連結回到首頁 </a></p>" + "</body>" + "</html>";
		sendEmail.send(sesv2Client, sender, recipient, subject, bodyHTML);
		}
		return member;
	}
}
