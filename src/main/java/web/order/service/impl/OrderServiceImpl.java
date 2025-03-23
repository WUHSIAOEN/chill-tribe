package web.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.activity.dao.ActivityDao;
import web.activity.dao.ActivitySearchDao;
import web.activity.vo.Activities;
import web.order.dao.OrderDao;
import web.order.dao.TicketDao;
import web.order.service.OrderService;
import web.order.vo.Orders;
import web.order.vo.Ticket;

@Service
@Transactional
public class OrderServiceImpl extends OrderforpayServiceImpl implements OrderService {

	@Autowired
	private OrderDao ordDao;

	@Autowired
	private ActivityDao actDao;

	@Autowired
	private ActivitySearchDao actSearchDao;
	
	@Autowired
	private TicketDao ticketDao;

	@Override
	public String placeOrderWithPayment(Orders order) {
		Orders nOrder = updateInventory(order);
		Activities activity = actDao.selectByActivityId(order.getActivityId());
		int totalPrice = activity.getUnitPrice() * nOrder.getQuantity();
		
		createTickets(nOrder);
		
		// 呼叫綠界付款API
		String result = ecpay(activity.getActivityName(), nOrder.getQuantity(), totalPrice, nOrder.getOrderId());
		// 付款成功要變更訂單狀態，待優畫
		ticketDao.updateOrderStatus(nOrder.getOrderId(), "paid");
		return result;
	}

//	免付款下訂單
	@Override
	public void placeOrderWithoutPayment(Orders order) {
		Orders nOrder = updateInventory(order);		
		createTickets(nOrder);	
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
//		System.out.println(order.getActivityId());
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

			int orderInsertResultCount = ordDao.insert(order);
			
			if (orderInsertResultCount > 0 ) {
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

	@Override
	public Ticket createTicket(Ticket ticket) {
		int TicketInsertResultCount = ticketDao.insert(ticket);
		if (TicketInsertResultCount > 0 ) {
			ticket.setMessage("訂單/票券新增成功");
			ticket.setSuccessful(true);
		} else {
			ticket.setMessage("訂單/票券新增失敗");
			ticket.setSuccessful(false);
		}
		return ticket;
	}

	@Override
	public List<Ticket> createTickets(Orders nOrder) {
		List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < nOrder.getQuantity(); i++) {
			Ticket ticket = new Ticket(); 
			ticket.setOrderId(nOrder.getOrderId());
			ticket.setActivityId(nOrder.getActivityId());
			int ticketInsertResultCount = ticketDao.insert(ticket);
			
			if (ticketInsertResultCount > 0 ) {
				ticket.setMessage("票券新增成功");
				ticket.setSuccessful(true);
			} else {
				ticket.setMessage("票券新增失敗");
				ticket.setSuccessful(false);
			}
			tickets.add(ticket);
		}
		
		return tickets;
	}

	@Override
	public List<Orders> getMemberOrders(Integer memberId) {
		return ordDao.selectOrdersByMemberId(memberId);
	}
	
	
	
	

}
