package web.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.activity.service.ActivityService;
import web.activity.vo.Activities;

@RestController
@RequestMapping("supplier/activities/edit")
public class EditActivityController {

	@Autowired
	private ActivityService service;

	// 根據 ID 找到一個活動
	@GetMapping("{id}")
	public Activities findById(@PathVariable Integer id) {
		return service.findActivityById(id);

	}

	@PutMapping("{id}")
	public Activities edit(@PathVariable Integer id, @RequestBody Activities activities) {
		System.out.println("test" + activities);
		activities.setActivityId(id);
		return service.edit(activities);

	}

//	@GetMapping
//	public Activities getInfo(@SessionAttribute Activities activities) {
//		if (activities == null) {
//			activities = new Activities();
//			activities.setMessage("無活動資訊");
//			activities.setSuccessful(false);
//		} else {
//			activities.setSuccessful(true);
//		}
//		return activities;
//	}

//@WebServlet("/activity/updateActivity")
//public class EditActivityController extends HttpServlet {
//		private static final long serialVersionUID = 1L;

//		呼叫　Service 以便使用它的方法

//	@Override
//	public void init() throws ServletException {
//		System.out.println("Initializing EditActivityController...");
//		service = new ActivityServiceImpl(); // 初始化 service
//		System.out.println("Service initialized successfully: " + service);
//	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
//
//		Activities updatedActivity = gson.fromJson(req.getReader(), Activities.class);
//		service.update(updatedActivity);
//
//
//	}

}
