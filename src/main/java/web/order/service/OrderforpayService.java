package web.order.service;

import web.order.vo.Orders;

public interface OrderforpayService {
	
	String placeOrder(Orders order);
	
	String ecpay(String activity_name, Integer count_number, Integer total_price);
	
}
