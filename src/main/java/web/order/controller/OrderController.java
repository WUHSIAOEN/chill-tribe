package web.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.order.service.OrderService;
import web.order.vo.Orders;

@RestController
@RequestMapping("orders/order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	// 下訂單
	@PostMapping
	public Orders order(@RequestBody Orders order) {
		if (order == null) {
			order = new Orders();
			order.setMessage("訂單成立失敗");
			order.setSuccessful(false);
			return order;
		}
		return service.placeOrderWithoutPayment(order);
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
		return service.getOrderInfo(orderId);
	}
	
	
}
