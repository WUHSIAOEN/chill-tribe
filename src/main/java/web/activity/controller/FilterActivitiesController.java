package web.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.activity.service.ActivitySearchService;
import web.activity.vo.Activities;
import web.order.vo.Ticket;

@RestController
@RequestMapping("activity/filter")
public class FilterActivitiesController {

	@Autowired
	private ActivitySearchService service;

	// 返回最近開始活動
	@GetMapping("/city/{cityId}")
	public List<Activities> showOrderInfo(@PathVariable Integer cityId) {
		System.out.println(cityId);
		return service.FilterByActivityCity(cityId);
	}

}
