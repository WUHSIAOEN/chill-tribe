package web.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.order.dao.OrderDao;
import web.order.service.OrderService;
import web.order.vo.Orders;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao dao;
	
	@Override
	public Orders placeOrderWithPayment(Orders order) {
		return order;
	}
	
//	免付款下訂單
	@Override
	public Orders placeOrderWithoutPayment(Orders order) {
		int resultCount = dao.insert(order);
		System.out.println(resultCount);
		if (resultCount > 0) {
			order.setMessage("訂單新增成功");
			order.setSuccessful(true);
		} else {
			order.setMessage("訂單新增失敗");
			order.setSuccessful(false);
		}
		return order;
	}

//	查已成立訂單
	@Override
	public Orders getOrderInfo(Integer orderId) {
		Orders order = dao.selectOrderbyId(orderId);
		return order;
	}



}
