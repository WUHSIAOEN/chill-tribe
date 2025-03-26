package web.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.activity.service.ActivitySearchService;
import web.activity.vo.Activities;

// 轉Spring 


@RestController
@RequestMapping("activity/search")
public class SearchActivityController {
	
	@Autowired
	private ActivitySearchService service;
	
	// 返回最近開始活動
		@GetMapping("/latest")
		public List<Activities> showOrderInfo() {
			return service.GetLatestActivities();
		}

}
