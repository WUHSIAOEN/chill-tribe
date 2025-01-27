package web.supplier.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.util.Core;
import web.supplier.service.SupplierService;
import web.supplier.service.impl.SupplierServiceImpl;
import web.supplier.vo.Supplier;

@WebServlet("/supplier/login")
public class SupplierLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Gson gson = new Gson();
			Supplier supplier = gson.fromJson(req.getReader(), Supplier.class);
			SupplierService service = new SupplierServiceImpl();
			supplier = service.login(supplier);
			if (supplier != null) {
				 if (req.getSession(false) != null) {
				 req.changeSessionId(); 
				 } 
				 req.getSession().setAttribute("supplier", supplier);
				} 
				 Core core = new Core();
				 core.setSuccessful(supplier != null);
				 resp.getWriter().write(gson.toJson(core));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
