package web.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.util.Core;
import web.activity.service.ActivityService;

@RestController
@RequestMapping("activities")
public class CancelActivityByIdController {
	
	@Autowired
	private ActivityService service;
	
	@PutMapping("{id}")
	public boolean cancel(@PathVariable Integer  id) {
		final Core core = new Core();
		
		if (id == null) {
			core.setSuccessful(false);
		} else {
			core.setSuccessful(true);
			return service.cancel(id);
		}
		return true;
	}	

// @WebServlet("/activity/cancelActivity")
// public class CancelActivityByIdController extends HttpServlet {
//	private static final long serialVersionUID = 1L;

//	呼叫　Service 以便使用它的方法
//	private ActivityService service;

//	@Override
//	public void init() throws ServletException {
//		System.out.println("Initializing UpdateActivityController...");
//		service = new ActivityServiceImpl(); // 初始化 service
//		System.out.println("Service initialized successfully: " + service);
//	}

//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
//
//		Activities cancelledActivity = gson.fromJson(req.getReader(), Activities.class);
//		service.cancelById(cancelledActivity);
//		
//
//	}
}
