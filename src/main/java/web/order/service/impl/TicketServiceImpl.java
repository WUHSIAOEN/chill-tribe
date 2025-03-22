package web.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.order.dao.TicketDao;
import web.order.service.TicketService;
import web.order.vo.Ticket;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao ticketDao;

	// 新增票券
	@Override
	public List<Ticket> getOrderTickets(int orderId) {
		List<Ticket> tickets = ticketDao.selctByOrderId(orderId);
		return tickets;
	}

}
