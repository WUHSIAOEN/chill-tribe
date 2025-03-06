package web.supplier.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import core.util.SendEmail;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import web.member.vo.Member;
import web.supplier.service.SupplierService;
import web.supplier.service.impl.SupplierServiceImpl;
import web.supplier.vo.Supplier;

// 供應商註冊
@RestController
@RequestMapping("/supplier/register")
//@WebServlet("/supplier/register")
public class SupplierRegisterController {
//	private static final long serialVersionUID = 1L;
	@Autowired
	private SupplierService service;
	
//	@Override
//	public void init() throws ServletException {
//		 try {
//			service = new SupplierServiceImpl();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
    
	@PostMapping
//	@Override
	public Supplier supplierregister(@RequestBody Supplier supplier) {
		
		if (supplier == null) {
			supplier = new Supplier();
			supplier.setMessage("無會員資訊");
			supplier.setSuccessful(false);
			return supplier;
		}

		
		supplier = service.register(supplier);
		
		SendEmail sendEmail = new SendEmail();
		
		Region region = Region.AP_NORTHEAST_1;
		SesV2Client sesv2Client = SesV2Client.builder().region(region).build();
		// 發信者
		String sender = "crystalwu@metaage.com.tw";
		// 收件者
		String recipient = supplier.getEmail();
		String subject = "註冊成功通知";
		String bodyHTML =
				"<html>" + "<head></head>" + "<body>" + "<h1>歡迎註冊Chill Tribe!</h1>"
				+ "<p><a href='http://localhost:8080/chill-tribe/supplier/supplierlogin.html'> 請點擊此連結已進行供應商登入 </a></p>" + "</body>" + "</html>";
		sendEmail.send(sesv2Client, sender, recipient, subject, bodyHTML);
		return supplier;
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		Gson gson = new Gson();
//		Supplier supplier = gson.fromJson(req.getReader(), Supplier.class);
//		
//	
//		String errMsg = service.register(supplier);
//		
//		JsonObject respBody = new JsonObject();
//		respBody.addProperty("successful", errMsg == null);
//		respBody.addProperty("errMsg", errMsg);
//		resp.getWriter().write(respBody.toString());
	}
}
