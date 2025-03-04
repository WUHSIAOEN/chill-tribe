package web.supplier.controller;

import java.io.IOException;

//import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.member.service.MemberService;
import web.member.vo.Member;
import web.supplier.service.SupplierService;
//import web.test.member.dao.MemberDao;
//import web.test.member.dao.impl.MemberDaoImpl;
import web.supplier.vo.Supplier;

// 供應商查詢
@RestController
@RequestMapping("/supplier/supplierfind")
//@SessionAttributes("supplier")
//@WebServlet("/supplier/supplierfind")
public class SupplierFindController {
//	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SupplierService service;
	
	@GetMapping("{supplier_id}")
//	@Override
	public Supplier supplierfind (@PathVariable Integer supplier_id) {
		return service.selectBySupplierID(supplier_id);
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Supplier supplier = (Supplier) req.getSession().getAttribute("supplier");
////		Gson gson = new Gson();
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy-MM-dd")
//				.create();
//		resp.getWriter().write(gson.toJson(supplier));
	}
}
