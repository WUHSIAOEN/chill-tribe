package web.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//@RestController
//@RequestMapping({"member/profile2", "/profile2"})
@WebServlet("/profile2")
public class ProfileController2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String GOOGLE_USERINFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
//    https://www.googleapis.com/oauth2/v1/certs
//    https://www.googleapis.com/oauth2/v2/userinfo

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Credential credential = (Credential) req.getSession().getAttribute("credential");
        String accessToken = credential.getAccessToken();

        String userInfoJson = getUserInfoFromGoogle(accessToken);

        JsonObject obj = new JsonObject();
        obj.addProperty("access_token", accessToken);
        obj.addProperty("profiles", userInfoJson);
        resp.setContentType("application/json");
        
        resp.getWriter().write(obj.toString());
    }

    public String getUserInfoFromGoogle(String accessToken) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(GOOGLE_USERINFO_URL + "?access_token=" + accessToken);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                ) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                }
            } else {
                System.err.println("Error: HTTP response code " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

}
