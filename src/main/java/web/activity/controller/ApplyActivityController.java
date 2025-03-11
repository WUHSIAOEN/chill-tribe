package web.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.activity.service.ActivityService;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;
import web.activity.vo.Comment;

@RestController
@RequestMapping("supplier/applyAct")
public class ApplyActivityController {

	@Autowired
	private ActivityService service;

	@PostMapping
	public Activities apply(@RequestBody Activities activities) {
		if (activities == null) {
			activities = new Activities();
			activities.setMessage("無活動資訊");
			activities.setSuccessful(false);
			return activities;
		}
		activities = service.apply(activities);
		return activities;
	}

	// 根據 ID 找到一個活動
	@GetMapping("{activityId}")
	public Activities findById(@PathVariable Integer activityId) {
		return service.findActivityById(activityId);

	}

	// 用 ID 去插入活動
	@PostMapping("{activityId}")
	public boolean activityImages(@PathVariable("activityId") Integer activityId,
			@RequestBody List<ActivityImage> images) {
		for (ActivityImage image : images) {
			image.setActivityId(activityId);
		}
		return service.addImages(images, activityId);
	}


//@WebServlet("/activity/apply")

//	因為Controller 這層不能直接用@Autowired 注入IoC 容器，還是要透過DL 取得，故未導入Spring MVC 前先將getBean 包在 init()
//	@Override
//	public void init() throws ServletException {
//		service = CommonUtil.getBean(getServletContext(), ActivityService.class);
//	}

//	================== 基本上以下這段不用了 ==================
//	@Override
//	public void init() throws ServletException {
//	    System.out.println("Initializing ApplyActivityController...");
//	    try {
//	        service = new ActivityServiceImpl(); // 初始化 service
//	        System.out.println("Service initialized successfully: " + service);
//	    } catch (NamingException e) {
//	        e.printStackTrace();
//	        throw new ServletException("Service initialization failed", e);
//	    }
//	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy/MM/dd HH:mm:ss")
//				.create();
//
//		Activities activity = gson.fromJson(req.getReader(), Activities.class);
//			
//		String errMsg = service.apply(activity);
//		System.out.println(errMsg);
//		System.out.println(activity);
//		
//		JsonObject respBody = new JsonObject();
//		respBody.addProperty("successful", errMsg == null);
//		respBody.addProperty("errMsg", errMsg);
//		
//		resp.setContentType("application/json");
//			
//	}
}
