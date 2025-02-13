package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.util.Core;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;

// 登入
@WebServlet({"/member/login", "/login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Gson gson = new Gson();
			Member member = gson.fromJson(req.getReader(), Member.class);
			MemberService service = new MemberServiceImpl();
			member = service.login(member);
			if (member != null) {
				 if (req.getSession(false) != null) {
				 req.changeSessionId(); 
				 } 
				 req.getSession().setAttribute("member", member);
				} 
				 Core core = new Core();
				 core.setSuccessful(member != null);
				 resp.getWriter().write(gson.toJson(core));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
