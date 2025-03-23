package web.order.service;

import java.util.List;

import web.order.vo.Ticket;

public interface TicketService {
	
	List<Ticket> getOrderTickets(Integer orderId);

}
