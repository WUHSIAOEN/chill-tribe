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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;
import web.member.vo.MemberOther;

// 一般會員大頭照
@RestController
@RequestMapping("member/memberimg")
@SessionAttributes("member")
//@WebServlet("/member/memberimg")
public class FileImgController  {
//	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberService service;
	
	
//	@Override
//	public void init() throws ServletException {
//		try {
//			service = new MemberServiceImpl();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	@PostMapping
//	@Override
	public Member memberimg(@RequestBody Member reqMember, @SessionAttribute("member") Member seMember) {
		final Integer memberid = seMember.getMemberid();
		reqMember.setMemberid(memberid);
		return service.updateimg(reqMember);
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Gson gson = new Gson();
		
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy/MM/dd")
//				.create();

		// 舊的session資料
//		Member sessionMember = (Member) req.getSession().getAttribute("member");
//		// edit後更新的session資料
//		Member updateMember = gson.fromJson(req.getReader(), Member.class);
//		
////		MemberOther memberOther = sessionMember.get;
//
//		// to do list 更新了session的資料但是如果沒有異動的會沒資料所以要做判斷是否有異動
//
//		sessionMember = service.updateimg(sessionMember, updateMember);
//
//		req.getSession().setAttribute("member", sessionMember);
//
////		sessionMember.setcPassword(null);
//		resp.setContentType("application/json");
//		resp.getWriter().write(gson.toJson(sessionMember));
	}
}
