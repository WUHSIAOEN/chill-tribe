package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/oauthlogin")
public class OauthController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String OAUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	private static final String CLIENT_ID = "";
	private static final String REDIRECT_URI = "http://localhost:8080/oauthTest/callback";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String authURL = OAUTH_URL
				.concat("?client_id=")
				.concat(CLIENT_ID)
				.concat("&redirect_uri=")
				.concat(REDIRECT_URI)
				.concat("&response_type=code&scope=email%20profile");

		resp.sendRedirect(authURL);
	}
}
