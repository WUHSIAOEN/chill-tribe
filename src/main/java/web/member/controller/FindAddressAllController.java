package web.member.controller;

import java.io.IOException;
import java.util.List;

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
//import web.test.member.dao.MemberDao;
//import web.test.member.dao.impl.MemberDaoImpl;
import web.member.vo.Member;

// 一般會員地址查詢
@RestController
@RequestMapping({"member/findaddressall", "/findaddressall"})
//@WebServlet("/member/findaddressall")
public class FindAddressAllController {
//	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberService service;

//	@Override
//	public void init() throws ServletException {
//		 try {
//			service = new MemberServiceImpl();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	@GetMapping("{memberid}")
//	@Override
	public Member findaddressAll (@PathVariable Integer memberid) {
		return service.selectaddressAll(memberid);
	}
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Member sessionMember = (Member) req.getSession().getAttribute("member");
//		List<Member> member = service.selectaddressAll(sessionMember.getMemberid());
////		Gson gson = new Gson();
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy-MM-dd")
//				.create();
//		resp.getWriter().write(gson.toJson(member));
//	}
}
