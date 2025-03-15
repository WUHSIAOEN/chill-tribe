package web.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.activity.vo.Activities;
import web.order.service.OrderService;
import web.order.vo.Orders;

@RestController
@RequestMapping("orders/order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PostMapping
	public Orders order(@RequestBody Orders order) {
		
		if (order == null) {
			order = new Orders();
			order.setMessage("無訂單資訊");
			order.setSuccessful(false);
			return order;
		}
		return service.placeOrderWithoutPayment(order);
	}
	
	@GetMapping("{orderId}")
	public Orders showOrderInfo(@PathVariable Integer orderId) {
		Orders order = service.getOrderInfo(orderId);
		System.out.println("orderID 是" + order.getOrderId());
		return order;
	}
	
	
}
