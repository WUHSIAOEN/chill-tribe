package web.order.service;

import web.order.vo.Orders;

public interface OrderService {
	
	Orders placeOrderWithPayment(Orders order);
	
	Orders placeOrderWithoutPayment(Orders order);
	
	Orders getOrderInfo(Integer orderId);
	
}
