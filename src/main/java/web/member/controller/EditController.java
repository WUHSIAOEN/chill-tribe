package web.member.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;

// 一般會員修改
@WebServlet("/member/memberedit")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
		 try {
			service = new MemberServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy/MM/dd")
				.create();
		// 舊的session資料
		Member sessionMember = (Member) req.getSession().getAttribute("member");
		// edit後更新的session資料
		Member updateMember = gson.fromJson(req.getReader(), Member.class);
		
		// to do list 更新了session的資料但是如果沒有異動的會沒資料所以要做判斷是否有異動
		
		
        sessionMember = service.edit(sessionMember, updateMember);
		
		req.getSession().setAttribute("member", sessionMember);
		
		sessionMember.setcPassword(null);
		resp.setContentType("application/json");
		resp.getWriter().write(gson.toJson(sessionMember));
	}
}
