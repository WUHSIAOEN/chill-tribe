package web.controller;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import core.util.Core;
import core.vo.GoogleData;
import web.googlelogin.service.GoogleService;
import web.googlelogin.service.impl.GoogleServiceImpl;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;

import javax.jdo.JDOHelper;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/callback2")
public class CallbackController2 extends AbstractAuthorizationCodeCallbackServlet {
    private static final String CLIENT_ID = "224274855650-058eb8p8d84m05f3sodeggamp88u4u3s.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final java.io.File DATA_STORE_DIR = new java.io.File("credentials");
    private static final DataStoreFactory DATA_STORE_FACTORY;
    
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
    
    
    
    
    static {
        try {
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential) throws ServletException, IOException {
        req.getSession().setAttribute("credential", credential);
        
        ProfileController2 test2 = new ProfileController2();
        String accessToken = credential.getAccessToken();

        String userInfoJson = test2.getUserInfoFromGoogle(accessToken);
        
        
        JsonObject obj = new JsonObject();
        obj.addProperty("access_token", accessToken);
        obj.addProperty("profiles", userInfoJson);
        resp.setContentType("application/json");
        
        resp.getWriter().write(obj.toString());
        
        Gson gson = new Gson();
        GoogleData gd = gson.fromJson(userInfoJson, GoogleData.class);
        System.out.println(userInfoJson);
        
        HttpSession session = req.getSession(true);
        session.setAttribute("loggedin", true);
        session.setAttribute("credential", credential);
        session.setAttribute("Google", gd);
        System.out.println("最外層"+gd);
        System.out.println("最外層"+gd.getEmail());
        
        if(gd.getEmail() == null || gd.getEmail().isEmpty()) {
        	System.out.println("判斷為空時"+gd.getEmail());
        	System.out.println("ㄟㄟ是空的哦");
        } else {
        	System.out.println("好耶進入判斷");
        	Member member = service.googlelogin(gd);
        	session.setAttribute("member", member);
        }
        
        resp.sendRedirect("http://localhost:8080/chill-tribe/chilltribe.html");
    }
    
    
    
    
    

    @Override
    protected void onError(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse) throws ServletException, IOException {
        System.out.println(errorResponse.getError());
    }

    @Override
    protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
        GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath("/chill-tribe/callback2");
        return url.build();
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws IOException {
        Set<String> scopes = new HashSet<>();
        scopes.add("email");
        scopes.add("profile");
        return new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            CLIENT_ID,
            CLIENT_SECRET,
            scopes
        )
            .setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
    }

    @Override
    protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
        return req.getSession().getId();
    }
}

