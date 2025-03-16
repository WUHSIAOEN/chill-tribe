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
public class OrderServiceImpl extends OrderforpayServiceImpl implements OrderService {

	@Autowired
	private OrderDao ordDao;

	@Autowired
	private ActivityDao actDao;

	@Autowired
	private ActivitySearchDao actSearchDao;

	@Override
	public String placeOrderWithPayment(Orders order) {
		Orders nOrder = updateInventory(order);
		Activities activity = actDao.selectByActivityId(order.getActivityId());
		int totalPrice = activity.getUnitPrice() * nOrder.getQuantity();
		// 呼叫綠界付款API
		String result = ecpay(activity.getActivityName(), nOrder.getQuantity(), totalPrice, nOrder.getOrderId());
		return result;
	}

//	免付款下訂單
	@Override
	public Orders placeOrderWithoutPayment(Orders order) {
		return updateInventory(order);
	}

//	查已成立訂單
	@Override
	public Orders getOrderInfo(Integer orderId) {
		Orders order = ordDao.selectOrderbyId(orderId);
		return order;
	}

	// 因為要付款跟不用付款都需要更新庫存，所以另外拉出來
	@Transactional
	@Override
	public Orders updateInventory(Orders order) {
		// 查未更改的當前票券庫存
		System.out.println(order.getActivityId());
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
		} else {
			order.setMessage("票存庫存已不足");
			order.setSuccessful(false);
			return order;
		}
	}

}
