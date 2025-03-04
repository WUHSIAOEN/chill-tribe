package web.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.shoppingcart.service.ShoppingCartService;
import web.shoppingcart.vo.ShoppingCart;

@RestController
@RequestMapping("cart")
public class FindScController{
	
	@Autowired
	private ShoppingCartService service;
	
	// 找到全部的活動
	@GetMapping
	public List<ShoppingCart> findAll() {
		return service.findAll();
		
	}
	
	// 根據 ID 找到一個活動
	@GetMapping("{id}")
	public ShoppingCart findById(@PathVariable Integer id) {
		return service.findByScId(id);
		
	}

//@WebServlet("/myfavorites/findallmyfavorites")
//public class FindAllController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private MyFavoritesService service;
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
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy/MM/dd HH:mm:ss")
//				.create();
//		
//		String myFavorites = service.findAllFavorites();
//		resp.getWriter().write(gson.toJson(myFavorites));
//	}
	
}
