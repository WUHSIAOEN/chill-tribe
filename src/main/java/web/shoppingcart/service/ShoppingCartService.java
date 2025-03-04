package web.shoppingcart.service;

import java.util.List;

import web.shoppingcart.vo.ShoppingCart;

public interface ShoppingCartService {
	// 加入購物車
	ShoppingCart add(ShoppingCart shoppingCart);

	// 找到所有產品
	List<ShoppingCart> findAll();
	
	ShoppingCart findByScId(Integer id);

	boolean remove(Integer id);
}
