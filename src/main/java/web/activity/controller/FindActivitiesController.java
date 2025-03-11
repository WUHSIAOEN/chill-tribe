package web.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.activity.service.ActivityService;
import web.activity.vo.Activities;
import web.activity.vo.Comment;

@RestController
@RequestMapping("activities")
public class FindActivitiesController {

	@Autowired
	private ActivityService service;

	// 找到全部的活動
	@GetMapping
	public List<Activities> findAllActivities() {
		return service.findAll();

	}

	// 根據 ID 找到一個活動
	@GetMapping("{id}")
	public Activities findById(@PathVariable Integer id) {
		return service.findActivityById(id);

	}

	// 用 ID 去插入評論
	@PostMapping("{activityId}")
	public boolean activityComments(@PathVariable("activityId") Integer activityId,
			@RequestBody List<Comment> comments) {
		for (Comment comment : comments) {
			comment.setActivityId(activityId);
		}
		return service.addComments(comments, activityId);
	}

//@WebServlet("/activity/findAll")
//public class FindAllActivityController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private ActivityService service;
//	
//	@Override
//	public void init() throws ServletException {
//		System.out.println("Initializing ApplyActivityController...");
//	    service = new ActivityServiceImpl(); // 初始化 service
//		System.out.println("Service initialized successfully: " + service);
//	}
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy/MM/dd HH:mm:ss")
//				.create();
//		List<Activities> activities = service.findAllActivity();
//		System.out.println("findAll() " + activities);
//		resp.getWriter().write(gson.toJson(activities));
//	}

}
