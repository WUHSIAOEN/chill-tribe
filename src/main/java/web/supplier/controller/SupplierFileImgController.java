package web.supplier.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.supplier.service.SupplierService;
import web.supplier.service.impl.SupplierServiceImpl;
import web.supplier.vo.Supplier;

// 一般會員大頭照
@WebServlet("/supplier/supplierimg")
public class SupplierFileImgController extends HttpServlet {
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
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy/MM/dd")
				.create();
		
		Supplier supplier = gson.fromJson(req.getReader(), Supplier.class);
		
		supplier = service.updateimg(supplier);
		
		supplier.setcPassword(null);
		resp.setContentType("application/json");
		resp.getWriter().write(gson.toJson(supplier));
	}
}
