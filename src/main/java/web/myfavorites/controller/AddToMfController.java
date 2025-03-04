package web.myfavorites.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.myfavorites.service.MyFavoritesService;
import web.myfavorites.vo.MyFavorites;

@RestController
@RequestMapping("myfavorites")
public class AddToMfController {

	@Autowired
	private MyFavoritesService service;

	@PostMapping("{id}")
	public MyFavorites add(@RequestBody MyFavorites myFavorites) {
		if (myFavorites == null) {
			myFavorites = new MyFavorites();
			myFavorites.setMessage("無活動資訊");
			myFavorites.setSuccessful(false);
			return myFavorites;
		}
		myFavorites = service.add(myFavorites);
		return myFavorites;
	}

//@WebServlet("/myfavorites/addtomyfavorites")
//public class AddController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	private MyFavoritesServiceImpl service;
//	
//	@Override
//	public void init() throws ServletException {
//		System.out.println("Initializing AddToCartController...");
//		try {
//			service = new MyFavoritesServiceImpl(); // 初始化 service
//			System.out.println("Service initialized successfully: " + service);
//		} catch (NamingException e) {
//			e.printStackTrace();
//			throw new ServletException("Service initialization failed", e);
//		}
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//		Gson gson = new Gson();
//		
//		MyFavorites myFavorites = gson.fromJson(req.getReader(), MyFavorites.class);
//		service.AddToMyFavorites(myFavorites);
//	}
}