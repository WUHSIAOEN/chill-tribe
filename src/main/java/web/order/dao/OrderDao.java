package web.order.dao;

import web.order.vo.Orders;

public interface OrderDao {
	
	// 新增訂單
	int insert(Orders order);
	
}
