package web.order.vo;

import java.sql.Timestamp;

public class Orderforpay {
	private Integer orderId;
	private String orderPrefix;
    private Integer memberId;
    private Integer activityId;
    private Integer quantity;
    private String orderStatus;
    private String paymentMethod;
    private String merchantTradeNo;
    private String tradeNo;
    private Timestamp orderUpdateDatetime;
    private Timestamp orderCreateDatetime;
	
    
 // 訂單test
 	private String activity_name;
 	private Integer count_number;
     private Integer total_price;
     
     
 	
     public Integer getOrderId() {
 		return orderId;
 	}
 	public void setOrderId(Integer orderId) {
 		this.orderId = orderId;
 	}
 	public String getOrderPrefix() {
 		return orderPrefix;
 	}
 	public void setOrderPrefix(String orderPrefix) {
 		this.orderPrefix = orderPrefix;
 	}
 	public Integer getMemberId() {
 		return memberId;
 	}
 	public void setMemberId(Integer memberId) {
 		this.memberId = memberId;
 	}
 	public Integer getActivityId() {
 		return activityId;
 	}
 	public void setActivityId(Integer activityId) {
 		this.activityId = activityId;
 	}
 	public Integer getQuantity() {
 		return quantity;
 	}
 	public void setQuantity(Integer quantity) {
 		this.quantity = quantity;
 	}
 	public String getOrderStatus() {
 		return orderStatus;
 	}
 	public void setOrderStatus(String orderStatus) {
 		this.orderStatus = orderStatus;
 	}
 	public String getPaymentMethod() {
 		return paymentMethod;
 	}
 	public void setPaymentMethod(String paymentMethod) {
 		this.paymentMethod = paymentMethod;
 	}
 	public String getMerchantTradeNo() {
 		return merchantTradeNo;
 	}
 	public void setMerchantTradeNo(String merchantTradeNo) {
 		this.merchantTradeNo = merchantTradeNo;
 	}
 	public String getTradeNo() {
 		return tradeNo;
 	}
 	public void setTradeNo(String tradeNo) {
 		this.tradeNo = tradeNo;
 	}
 	public Timestamp getOrderUpdateDatetime() {
 		return orderUpdateDatetime;
 	}
 	public void setOrderUpdateDatetime(Timestamp orderUpdateDatetime) {
 		this.orderUpdateDatetime = orderUpdateDatetime;
 	}
 	public Timestamp getOrderCreateDatetime() {
 		return orderCreateDatetime;
 	}
 	public void setOrderCreateDatetime(Timestamp orderCreateDatetime) {
 		this.orderCreateDatetime = orderCreateDatetime;
 	}
 	 public String getActivity_name() {
 			return activity_name;
 		}
 		public void setActivity_name(String activity_name) {
 			this.activity_name = activity_name;
 		}
 		public Integer getCount_number() {
 			return count_number;
 		}
 		public void setCount_number(Integer count_number) {
 			this.count_number = count_number;
 		}
 		public Integer getTotal_price() {
 			return total_price;
 		}
 		public void setTotal_price(Integer total_price) {
 			this.total_price = total_price;
 		}
	
}
