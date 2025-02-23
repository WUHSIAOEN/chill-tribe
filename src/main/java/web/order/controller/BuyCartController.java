package web.order.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.member.vo.Member;
import web.order.service.OrderService;
import web.order.service.OrderforpayService;
import web.order.service.impl.OrderServiceImpl;
import web.order.service.impl.OrderforpayServiceImpl;
import web.order.vo.Order;
import web.order.vo.Orderforpay;

//一般會員購物
@WebServlet("/order/buy")
public class BuyCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderforpayService service;
	
	
	
	@Override
	public void init() throws ServletException {
//		 try {
			service = new OrderforpayServiceImpl();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
	}
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		
		Gson gson = new Gson();
		Orderforpay order = gson.fromJson(req.getReader(), Orderforpay.class);
		
		String activity_name = order.getActivity_name();  
	    Integer count_number = order.getCount_number();    
	    Integer total_price = order.getTotal_price();  
		
		String errMsg = service.ecpay(activity_name, count_number, total_price);
		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successful", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.getWriter().write(respBody.toString());
		
	}
}
