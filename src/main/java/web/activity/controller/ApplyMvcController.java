package web.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import web.activity.service.ActivityService;
import web.activity.vo.Activities;

@Controller
@RequestMapping("activity")
public class ApplyMvcController {
	@Autowired
	private ActivityService service;
	
	@PostMapping("apply")
	@ResponseBody
	public Activities apply(@RequestBody Activities activities) {
		
		String errMsg = service.apply(activities);
		System.out.println(errMsg);
		return activities;
	}
}
