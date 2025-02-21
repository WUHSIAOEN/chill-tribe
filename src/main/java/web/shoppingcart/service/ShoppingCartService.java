package web.shoppingcart.service;

import java.util.List;

import web.shoppingcart.vo.ShoppingCart;

public interface ShoppingCartService {
	// 加入購物車
	List<ShoppingCart> AddToCart(ShoppingCart shppingCart);
	// 找到所有產品
	List<ShoppingCart> findAllProducts();
}
