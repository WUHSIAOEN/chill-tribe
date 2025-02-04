package web.member.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.member.vo.Member;

public class LoginCheckFilter extends HttpFilter {
    private static final long serialVersionUID = 1L;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        // 確保 req 被轉型為 HttpServletRequest
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();

        // 從 Session 中取得 member 物件，這裡判斷使用者是否登入
        Member member = (Member) session.getAttribute("member"); // ←取得登⼊記號
        if (member != null) {
            // 已登入，繼續執行後續過濾鏈
            chain.doFilter(req, res);
        } else {
            // 如果未登入，將欲前往的網址儲存下來，並轉發至 login.jsp
            String targetPath = httpRequest.getRequestURL().toString(); // 正確使用 httpRequest 來取得請求的 URL
            session.setAttribute("targetPath", targetPath);
            req.getRequestDispatcher("member-register.html").forward(req, res); // ←直接跳轉⾄登入頁面
        }
    }
}
