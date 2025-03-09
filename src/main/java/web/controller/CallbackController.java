package web.controller;

import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/callback")
public class CallbackController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String TOKEN_URI = "https://oauth2.googleapis.com/token";
    private static final String CLIENT_ID = "224274855650-058eb8p8d84m05f3sodeggamp88u4u3s.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8080/oauthTest/callback";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            HttpTransport transport = new NetHttpTransport();
            JsonFactory jsonFactory = new GsonFactory();

            TokenResponse tokenResponse = new AuthorizationCodeTokenRequest(
                    transport, jsonFactory, new GenericUrl(TOKEN_URI), code)
                    .setClientAuthentication(new ClientParametersAuthentication(CLIENT_ID, CLIENT_SECRET))
                    .setRedirectUri(REDIRECT_URI)
                    .execute();

            String accessToken = tokenResponse.getAccessToken();
            req.getSession().setAttribute("accessToken", accessToken);
            resp.sendRedirect("http://localhost:8080/chill-tribe/oauth2.0.html");
        } else {
            resp.getWriter().println("Authorization code not received.");
        }
    }
}
