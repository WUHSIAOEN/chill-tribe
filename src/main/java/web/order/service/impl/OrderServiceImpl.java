package web.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.activity.dao.ActivityDao;
import web.activity.dao.ActivitySearchDao;
import web.activity.vo.Activities;
import web.order.dao.OrderDao;
import web.order.service.OrderService;
import web.order.vo.Orders;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao ordDao;
	
	@Autowired
	private ActivityDao actDao;
	
	@Autowired
	private ActivitySearchDao actSearchDao;
	
	@Override
	public Orders placeOrderWithPayment(Orders order) {
		return order;
	}
	
//	免付款下訂單
	@Transactional
	@Override
	public Orders placeOrderWithoutPayment(Orders order) {
		// 查未更改的當前票券庫存
		Activities activity = actDao.selectByActivityId(order.getActivityId());
		Integer oActInventory = activity.getInventoryCount();
		
		if (order.getQuantity() <= oActInventory) {
			int nActInventory = oActInventory - order.getQuantity();
			int updateResultCount = actSearchDao.updateActInventoryById(order.getActivityId(), nActInventory);

			if (updateResultCount <= 0) {
				order.setMessage("訂單新增失敗");
				order.setSuccessful(false);
				return order;
			} 
			
			int insertResultCount = ordDao.insert(order);
			
			if (insertResultCount > 0) {
				order.setMessage("訂單新增成功");
				order.setSuccessful(true);
			} else {
				order.setMessage("訂單新增失敗");
				order.setSuccessful(false);
			}
			return order;
		}else {
			order.setMessage("票存庫存已不足");
			order.setSuccessful(false);
			return order;
		}
	}

//	查已成立訂單
	@Override
	public Orders getOrderInfo(Integer orderId) {
		Orders order = ordDao.selectOrderbyId(orderId);
		return order;
	}



}
