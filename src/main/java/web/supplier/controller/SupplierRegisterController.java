package web.supplier.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.supplier.service.SupplierService;
import web.supplier.service.impl.SupplierServiceImpl;
import web.supplier.vo.Supplier;

@WebServlet("/supplier/register")
public class SupplierRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SupplierService service;
	
	@Override
	public void init() throws ServletException {
		 try {
			service = new SupplierServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收view層來的請求參數
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//		String cPassword = req.getParameter("cPassword");
//		String nickname = req.getParameter("nickname");
//		Member member = new Member();
//		member.setUsername(username);
//		member.setPassword(password);
//		member.setcPassword(cPassword);
//		member.setNickname(nickname);
		
		Gson gson = new Gson();
		Supplier supplier = gson.fromJson(req.getReader(), Supplier.class);
		
//		String errMsg = service.register(member);
//		System.out.println(errMsg);
//		resp.getWriter().write(errMsg);
	
		String errMsg = service.register(supplier);
		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successful", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.getWriter().write(respBody.toString());
	}
}
