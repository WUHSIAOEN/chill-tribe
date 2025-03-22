package web.order.service;

import java.util.List;

import web.order.vo.Orders;
import web.order.vo.Ticket;

public interface OrderService {
	
	String placeOrderWithPayment(Orders order);
	
	void placeOrderWithoutPayment(Orders order);
	
	Orders getOrderInfo(Integer orderId);
	
	Orders updateInventory(Orders order);
	
	Ticket createTicket(Ticket ticket);
	
	List<Ticket> createTickets(Orders nOrder);
	
	List<Orders> getMemberOrders(Integer memberId);
	
}
