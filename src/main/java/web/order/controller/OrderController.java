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
import web.shoppingcart.vo.ShoppingCart;

@RestController
@RequestMapping("orders/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TicketService ticketService;

	// 下訂單 - 無須付款
	@PostMapping("/orderWithoutPayment")
	public Orders orderWithoutPayment(@RequestBody Orders order) {
		if (order == null) {
			order = new Orders();
			order.setMessage("訂單成立失敗");
			order.setSuccessful(false);

			return order;
		}
		orderService.placeOrderWithoutPayment(order);
		return order;
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
	@GetMapping("/tickets/{orderId}")
	public List<Ticket> createTickets(@PathVariable Integer orderId) {
		return ticketService.getOrderTickets(orderId);
	}
	
	// 查會員的所有訂單
	@GetMapping("/list/{memberId}")
	public List<Orders> getMemcerAllOrders(@PathVariable Integer memberId){
		List<Orders> orders = orderService.getMemberOrders(memberId);
//		如果是空值的判斷要再調整
		if (orders == null) {
			System.out.println("沒訂單");
		}
		return orders;
	}

}
