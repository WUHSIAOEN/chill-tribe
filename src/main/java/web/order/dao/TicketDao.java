package web.order.dao;

import java.util.List;

import web.order.vo.Ticket;

public interface TicketDao {
	
	int insert(Ticket ticket);
	
	Ticket selctByTicketId(int ticketId);
	
	List<Ticket> selctByOrderId(int orderId);
	
	
}
