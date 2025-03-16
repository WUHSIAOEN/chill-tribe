package web.order.service;

public interface OrderforpayService {
	
	String ecpay(String activityName, Integer countNumber, Integer totalPrice, Integer orderId);
	
}
