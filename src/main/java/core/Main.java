package core;

import java.util.List;

import javax.naming.NamingException;

import web.activity.dao.ActivitySearchDao;
import web.activity.dao.impl.ActivitySearchDaoImpl;
import web.activity.service.impl.ActivityServiceImpl2;
import web.activity.vo.Activities;
import web.activity.vo.Activity;
import web.activity.vo.ActivityImage;
import web.order.dao.OrderDao;
import web.order.dao.impl.OrderDaoImpl;
import web.order.vo.Order;
import web.order.vo.Orders;

public class Main {

	public static void main(String[] args) throws NamingException {
		// 測試回傳
//		List<ActivityImage> activityImages = new ActivitySearchDaoImpl().selectActivityImageById(1);
//		for(ActivityImage activityImage : activityImages) {
//			System.out.println(activityImage.getImageBase64());
//		}
//		ActivityServiceImpl2 as2 = new ActivityServiceImpl2();
//		as2.searchActivityByStart();
		
		
//		List<IndexActivityCard> indexActivityCards = new ActivitySearchDaoImpl().selectByNameCatgoryRegion("", "運動", "北部");
//		for(IndexActivityCard indexActivityCard : indexActivityCards) {
//			System.out.println(indexActivityCard.getActivityName());
//		}
		
//		ActivityServiceImpl2 as2 = new ActivityServiceImpl2();
//		List<Activities> cards = as2.searchActivityByFilter("健行", "all", "all");
//		for(Activities card : cards) {
//			System.out.println(card.getActivityName());
//		}
		
		OrderDao Od = new OrderDaoImpl();
		Orders order = new Orders();
		order.setActivityId(1);
		order.setMemberId(1);
		order.setQuantity(4);
		order.setOrderStatus("no_payment_required");
		order.setPaymentMethod("none");
		order.setOrderContact("小名");
		order.setContactMail("aaabbbcc@gmail.com");
		order.setContactPhone("456196582");
		order.setRequirement("無");
		Od.insert(order);
		
		
	}	
}


