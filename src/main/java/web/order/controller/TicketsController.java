package web.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.order.service.TicketService;
import web.order.vo.Ticket;

@RestController
@RequestMapping("tickets")
public class TicketsController {

	@Autowired
	private TicketService service;

	// 查詢訂單票券
	@GetMapping("{id}")
	public List<Ticket> createTickets(@PathVariable int orderId) {
		return service.getOrderTickets(orderId);
	}

}
