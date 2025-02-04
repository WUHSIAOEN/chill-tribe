package web.activity.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.activity.vo.Activity;

@WebServlet("/activity/delete")
public class DeleteActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//		Activity activity = json2Pojo(req, Activityc.class);
//		writePojo2Json(resp, new Core(SERVICE.removeMemberById(member.getId())));
	}
}
