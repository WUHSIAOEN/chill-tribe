package web.order.service;

import web.order.vo.Orders;

public interface OrderService {
	
	String placeOrderWithPayment(Orders order);
	
	String placeOrderWithoutPayment(Orders order);
	
}
