package web.member.controller;

import java.io.IOException;

//import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import web.test.member.dao.MemberDao;
//import web.test.member.dao.impl.MemberDaoImpl;
import web.member.vo.Member;

// 查詢
@WebServlet("/member/find")
public class FindController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 配合登入功能修改
		Member member = (Member) req.getSession().getAttribute("member");
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.create();
		resp.getWriter().write(gson.toJson(member));
//		// 暫時寫法
//		MemberDao dao;
//		try {
//			dao = new MemberDaoImpl();
//			// 資料庫上有的資料
//			Member member = dao.selectByUsername("ernietest");
//			// 轉換格式，取得
//			Gson gson = new Gson();
//			resp.setContentType("application/json");
//			resp.getWriter().write(gson.toJson(member));
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
	}
}
