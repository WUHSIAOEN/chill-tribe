package web.order.service;

import web.order.vo.Orders;

public interface OrderService {
	
	String placeOrderWithPayment(Orders order);
	
	Orders placeOrderWithoutPayment(Orders order);
	
	Orders getOrderInfo(Integer orderId);
	
	Orders updateInventory(Orders order);
	
}
