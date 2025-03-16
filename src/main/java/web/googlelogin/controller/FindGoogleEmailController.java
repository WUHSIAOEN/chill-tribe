package web.googlelogin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.Credential;
import com.google.gson.JsonObject;

import core.vo.GoogleData;
import web.googlelogin.service.GoogleService;
import web.googlelogin.service.impl.GoogleServiceImpl;
import web.member.vo.Member;

@WebServlet("/googlelogin/findGoogleEmail")
public class FindGoogleEmailController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GoogleService service;

	public void init() throws ServletException {
		try {
			service = new GoogleServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

        JsonObject obj = new JsonObject();

        if (session != null) {
            GoogleData gd = (GoogleData) session.getAttribute("Google");
            if (gd != null) {
                obj.addProperty("email", gd.getEmail());
                obj.addProperty("name", gd.getName());
                Member member = service.googlelogin(gd);
                obj.addProperty("memberid", member.getMemberid());
            	session.setAttribute("member", member);
            }
            response.setContentType("application/json");
            response.getWriter().write(obj.toString());
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            obj.addProperty("message", "Oh..糟糕錯誤了哦~");
            response.getWriter().write(obj.toString());
        }
    }
}
