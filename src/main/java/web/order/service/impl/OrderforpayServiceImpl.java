package web.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import web.order.service.OrderService;
import web.order.service.OrderforpayService;
import web.order.vo.Orders;

public class OrderforpayServiceImpl implements OrderforpayService{

	@Override
	public String placeOrder(Orders order) {
		
		return null;
	}

	
	// 綠界
    @Override
    public String ecpay(String activity_name, Integer count_number, Integer total_price) {
        // 設定訂單的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate = sdf.format(new Date());
        
        // AllInOne 物件
        AllInOne allInOne = new AllInOne("");

        // 訂單編號一定要英文+數字 不然會出錯~~~
        // 綠界要的資料
        AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
        aioCheckOutALL.setMerchantTradeNo("Order" + "t010"); // 訂單ID
        aioCheckOutALL.setMerchantTradeDate(orderDate); // 訂單日期
        aioCheckOutALL.setTotalAmount(String.valueOf(total_price)); // 訂單總金額
        aioCheckOutALL.setTradeDesc("購物網站支付"); // 訂單描述
        aioCheckOutALL.setItemName(activity_name); // 商品名稱
        aioCheckOutALL.setClientBackURL("http://localhost:8080/chill-tribe/chilltribe.html"); // 客戶端返回的 URL
        aioCheckOutALL.setReturnURL("http://localhost:8080/chill-tribe/member/member-register.html"); // 返回的URL
        aioCheckOutALL.setNeedExtraPaidInfo("N"); // 不需要額外支付資訊

        try {
            // 綠界叫API
            String form = allInOne.aioCheckOut(aioCheckOutALL, null);
            return form;
        } catch (Exception e) {
            e.printStackTrace();
            return "處理失敗，請稍後再試";
        }
    }
}
