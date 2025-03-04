package web.supplier.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.member.vo.Member;
import web.supplier.service.SupplierService;
import web.supplier.service.impl.SupplierServiceImpl;
import web.supplier.vo.Supplier;

// 供應商修改
@RestController
@RequestMapping("/supplier/supplieredit")
@SessionAttributes("supplier")
//@WebServlet("/supplier/supplieredit")
public class SupplierEditController  {
//	private static final long serialVersionUID = 1L;
	@Autowired
	private SupplierService service;
	
//	@Override
//	public void init() throws ServletException {
//		 try {
//			service = new SupplierServiceImpl();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	@PutMapping
//	@Override
	public Supplier supplieredit(@RequestBody Supplier reqsSupplier, @SessionAttribute("supplier") Supplier seSupplier) {
		final Integer supplier_id = seSupplier.getSupplier_id();
		reqsSupplier.setSupplier_id(supplier_id);
		return service.edit(reqsSupplier);
		
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////		Gson gson = new Gson();
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy/MM/dd")
//				.create();
//		
//		Supplier supplier = gson.fromJson(req.getReader(), Supplier.class);
//		
//		supplier = service.edit(supplier);
//		
//		supplier.setcPassword(null);
//		resp.setContentType("application/json");
//		resp.getWriter().write(gson.toJson(supplier));
	}
}
