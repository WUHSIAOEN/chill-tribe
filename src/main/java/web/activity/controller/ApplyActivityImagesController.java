package web.activity.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.activity.service.ActivityService;
import web.activity.service.impl.ActivityServiceImpl;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

@WebServlet("/activity/applyimages")
public class ApplyActivityImagesController extends HttpServlet{
private static final long serialVersionUID = 1L;
	
//	呼叫　Service
	private ActivityService service;
	
	@Override
	public void init() throws ServletException {
	    System.out.println("Initializing ApplyActivityImagesController...");
	    try {
	        service = new ActivityServiceImpl(); // 初始化 service
	        System.out.println("Service initialized successfully: " + service);
	    } catch (NamingException e) {
	        e.printStackTrace();
	        throw new ServletException("Service initialization failed", e);
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("----------------------------------------------");
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy/MM/dd HH:mm:ss")
//				.create();
//		
//		Activities activity = gson.fromJson(req.getReader(), Activities.class);
//		List<ActivityImage> imageList = new ArrayList<>();
		
		BufferedReader reader = req.getReader();
		System.out.println("reader " + reader);
		Gson gson = new Gson();
		RequestData requestData = gson.fromJson(reader, RequestData.class);
		System.out.println("requestData"+ requestData);
		List<String> images = requestData.getImages();
		System.out.println(" length : " + images.size());
		for (String str: images) {
			System.out.println("image-----------------");
			System.out.println(str.length());
			System.out.println("image: " + str);
		}
        
		
		resp.setContentType("application/json");
			
	}
}

class RequestData {
	private List<String> images = new ArrayList<>();
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
}
