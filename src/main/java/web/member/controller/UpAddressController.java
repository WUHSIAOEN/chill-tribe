//package web.member.controller;
//
//import java.io.IOException;
//
//import javax.naming.NamingException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonObject;
//
//import web.member.service.MemberService;
//import web.member.service.impl.MemberServiceImpl;
//import web.member.vo.Member;
//
//// 一般會員地址新增
//@WebServlet("/member/memberaddress")
//public class UpAddressController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private MemberService service;
//	
//	@Override
//	public void init() throws ServletException {
//		 try {
//			service = new MemberServiceImpl();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//       
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
//}
