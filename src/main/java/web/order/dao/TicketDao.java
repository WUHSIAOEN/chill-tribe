package web.order.dao;

import java.util.List;

import web.order.vo.Ticket;

public interface TicketDao {
	
	int insert(Ticket ticket);
	
	Ticket selctByTicketId(Integer ticketId);
	
	List<Ticket> selctByOrderId(Integer orderId);
	
	int updateOrderStatus(Integer orderId, String status);
	
	
}
