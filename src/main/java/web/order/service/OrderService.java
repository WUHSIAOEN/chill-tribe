package web.order.service;

import web.order.vo.Orders;
import web.order.vo.Ticket;

public interface OrderService {
	
	String placeOrderWithPayment(Orders order);
	
	Ticket placeOrderWithoutPayment(Orders order);
	
	Orders getOrderInfo(Integer orderId);
	
	Orders updateInventory(Orders order);
	
	Ticket createTickets(Ticket ticket);
	
}
