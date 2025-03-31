package web.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import web.order.service.OrderforpayService;


public class OrderforpayServiceImpl implements OrderforpayService{


	// 綠界付款方法
    @Override
    public String ecpay(String activityName, Integer countNumber, Integer totalPrice, Integer orderId) {
        // 設定訂單的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate = sdf.format(new Date());
        
        // AllInOne 物件
        AllInOne allInOne = new AllInOne("");
        
        // 自己新增 - 整理Order 序號
        String OrderNo = String.format("ORD%06d", orderId);
        
        // 自己新增 - 整理ClientBackURL
        String clientBackURL = "https://chilltribe.tia202g1.win/chill-tribe/order/order-details.html?orderId=" + orderId;
        

        // 訂單編號一定要英文+數字 不然會出錯~~~
        // 綠界要的資料
        AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
//        aioCheckOutALL.setMerchantTradeNo("Order" + "t010"); // 訂單ID
        aioCheckOutALL.setMerchantTradeNo(OrderNo + "chilltribe"); // 訂單ID
        aioCheckOutALL.setMerchantTradeDate(orderDate); // 訂單日期
        aioCheckOutALL.setTotalAmount(String.valueOf(totalPrice)); // 訂單總金額
        aioCheckOutALL.setTradeDesc("購物網站支付"); // 訂單描述
        aioCheckOutALL.setItemName(activityName); // 商品名稱
        aioCheckOutALL.setClientBackURL(clientBackURL); // 客戶端返回的 URL
        aioCheckOutALL.setReturnURL("https://chilltribe.tia202g1.win/chill-tribe/member/member-register.html"); // 返回的URL
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
