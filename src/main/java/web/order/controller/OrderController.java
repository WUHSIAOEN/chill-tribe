package web.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.order.service.OrderService;
import web.order.service.TicketService;
import web.order.vo.Orders;
import web.order.vo.Ticket;

@RestController
@RequestMapping("orders/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TicketService ticketService;

	// 下訂單 - 無須付款
	@PostMapping("/orderWithoutPayment")
	public Ticket orderWithoutPayment(@RequestBody Orders order) {
		if (order == null) {
			Ticket ticket = new Ticket();
			order = new Orders();
			order.setMessage("訂單成立失敗");
			order.setSuccessful(false);
			
			ticket.setMessage("票券成立失敗");
			ticket.setSuccessful(false);
			ticket.setOrder(order);
			return ticket;
		}
		
		return orderService.placeOrderWithoutPayment(order);
	}
	
	// 下訂單 - 無須付款
	@PostMapping("/orderWithPayment")
	public String orderWithPayment(@RequestBody Orders order) {
		String result = orderService.placeOrderWithPayment(order);
		return result;
	}
	
	
	// 撈訂單資訊
	@GetMapping("{orderId}")
	public Orders showOrderInfo(@PathVariable Integer orderId) {
		if (orderId == null) {
			Orders order = new Orders();
			order.setMessage("無訂單資訊");
			order.setSuccessful(false);
			return order;
		}
		return orderService.getOrderInfo(orderId);
	}
	
	// 查詢訂單票券
		@GetMapping("/ticket/{orderId}")
		public List<Ticket> createTickets(@PathVariable Integer orderId) {
			return ticketService.getOrderTickets(orderId);
		}

}
