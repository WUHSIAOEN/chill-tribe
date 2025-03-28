//package web.member.controller;
//
////import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
////import javax.servlet.ServletException;
////import javax.servlet.ServletRequest;
////import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import web.member.vo.Member;
//import web.supplier.vo.Supplier;
//
//// Filter限制 除了login
////@WebFilter("/member/*")
//@WebFilter("/*")
//public class LoginFilter extends HttpFilter {
//	
//	private static final long serialVersionUID = 1L;
//	
//	@Override
//    public void init(FilterConfig filterConfig) throws ServletException {
////        System.out.println("Filter被初始化時，Web容器會⾃動呼叫此⽅法，因為Filtet支援的是3.1版後，但是我們加了Google的API只有2.4版不支援所以要創一個初始化");
//    }
//	
//	@Override
//	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
//		
//		try {
//			// 排除css.js.img資源
//	        String requestURI = req.getRequestURI();
//	        if (
//	        		requestURI.endsWith(".css") || 
//	        		requestURI.endsWith(".js") || 
//	        		requestURI.endsWith(".jpg") || 
//	        		requestURI.endsWith(".woff2") || 
//	        		requestURI.endsWith(".woff") || 
//	        		requestURI.endsWith(".ttf") || 
//	        		requestURI.endsWith(".png")) {
//	            chain.doFilter(req, res); 
//	            return; 
//	        }
//			
//	        // 排除請求&再次排除靜態資源跟網頁
//			if (req.getServletPath().startsWith("/member/login") ||
//				req.getServletPath().startsWith("/supplier/supplierlogin") ||
//				req.getServletPath().startsWith("/supplier/register") ||
//				req.getServletPath().startsWith("/supplier/supplierforgetpassword") ||
//				req.getServletPath().startsWith("/member/register") ||
//				req.getServletPath().startsWith("/member/forgetpassword") ||
//				req.getServletPath().startsWith("/member/header") ||
//				req.getServletPath().startsWith("/member/footer") ||
//				req.getServletPath().startsWith("/member/css/index.css") ||
//				req.getServletPath().startsWith("/admin/plugins/fontawesome-free/css/all.min.css") ||
//				req.getServletPath().startsWith("/admin/plugins/icheck-bootstrap/icheck-bootstrap.min.css") ||
//				req.getServletPath().startsWith("/admin/dist/css/adminlte.min.css") ||
//				req.getServletPath().startsWith("/member/js/member-register.js") ||
//				req.getServletPath().startsWith("/member/js/loginmodal.js") ||
//				req.getServletPath().startsWith("/member/js/index-ernie.js") ||
//				req.getServletPath().startsWith("/admin/plugins/jquery/jquery.min.js") ||
//				req.getServletPath().startsWith("/admin/plugins/bootstrap/js/bootstrap.bundle.min.js") ||
//				req.getServletPath().startsWith("/admin/dist/js/adminlte.min.js") ||
//				req.getServletPath().equals("/chilltribe") ||
//				req.getServletPath().equals("/chilltribe.html") ||
//				req.getServletPath().equals("/member/member-register.html") ||
//				req.getServletPath().equals("/member/supplier-register.html") ||
//				req.getServletPath().equals("/member/forgot-password.html") ||
//				req.getServletPath().equals("/supplier/supplierlogin.html") ||
//				req.getServletPath().equals("/supplier/supplierforgetpassword.html") ||
//				req.getServletPath().equals("/"))
//			{
//				chain.doFilter(req, res);
//			}	else {
//				HttpSession session = req.getSession();
//				Member member = (Member) session.getAttribute("member");
//				Supplier supplier = (Supplier) session.getAttribute("supplier");
//				if (member != null || supplier != null) {
//					chain.doFilter(req, res);
//				} else {
//					String targetPath = req.getRequestURI().toString();
//					session.setAttribute("targetPath", targetPath);
//					res.sendRedirect("http://localhost:8080/chill-tribe/chilltribe.html");
////					req.getRequestDispatcher("/chilltribe.html").forward(req, res);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//    public void destroy() {
////        System.out.println("Filter被銷毀時，Web容器會⾃動呼叫此⽅法，因為Filtet支援的是3.1版後，但是我們加了Google的API只有2.4版不支援所以要創一個銷毀");
//    }
//}
