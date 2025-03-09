package web.googlelogin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.Credential;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import core.vo.GoogleData;
import web.googlelogin.service.GoogleService;
import web.googlelogin.service.impl.GoogleServiceImpl;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;

@WebServlet("/googlelogin/findGoogleMember")
public class FindGoogleMemberController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MemberService service;

	public void init() throws ServletException {
		try {
			service = new MemberServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getSession().getAttribute("GoogleEmail"));
		GoogleData gd = (GoogleData) request.getSession().getAttribute("GoogleEmail");
		List<Member> memberList = service.findAll();
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.create();
		response.getWriter().write(gson.toJson(gd));
    }
}
