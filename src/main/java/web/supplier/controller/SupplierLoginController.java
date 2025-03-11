package web.supplier.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import core.util.Core;
import web.member.service.MemberService;
import web.member.vo.Member;
import web.supplier.service.SupplierService;
import web.supplier.service.impl.SupplierServiceImpl;
import web.supplier.vo.Supplier;

// 供應商登入
@RestController
@RequestMapping("/supplier/supplierlogin")
//@WebServlet("/supplier/supplierlogin")
public class SupplierLoginController {
//	private static final long serialVersionUID = 1L;

	@Autowired
	private SupplierService service;

	@GetMapping("{email}/{password}")
//	@Override
	protected Supplier supplierlogin(HttpServletRequest request, @PathVariable String email,
			@PathVariable String password) {
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Supplier supplier = new Supplier();
		if (email == null || password == null) {
			supplier.setMessage("無會員資訊");
			supplier.setSuccessful(false);
			return supplier;
		}

		supplier.setEmail(email);
		supplier.setPassword(password);
		supplier = service.login(supplier);
		if (supplier.isSuccessful()) {
//			if (request.getSession(false) != null) {
//				request.changeSessionId();
			HttpSession existingSession = request.getSession(false);
			if (existingSession != null) {
				existingSession.invalidate();
			}
			HttpSession session = request.getSession(true);
//			}
//			final HttpSession session = request.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("supplier", supplier);
		}
		return supplier;

//		try {
//			Gson gson = new Gson();
//			Supplier supplier = gson.fromJson(req.getReader(), Supplier.class);
//			SupplierService service = new SupplierServiceImpl();
//			supplier = service.login(supplier);
//			if (supplier != null) {
//				 if (req.getSession(false) != null) {
//				 req.changeSessionId(); 
//				 } 
//				 req.getSession().setAttribute("supplier", supplier);
//				} 
//				 Core core = new Core();
//				 core.setSuccessful(supplier != null);
//				 resp.getWriter().write(gson.toJson(core));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
	}
}
