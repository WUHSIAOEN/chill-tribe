package web.order.dao;

import java.util.List;

import web.order.vo.Orders;

public interface OrderDao {
	
	// 新增訂單
	int insert(Orders order);
	
	// 查訂單資訊
	Orders selectOrderbyId(Integer orderId);
	
	List<Orders> selectOrdersByMemberId(Integer memberId);
	
}
