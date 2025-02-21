package web.shoppingcart.dao;

import java.util.List;

import web.shoppingcart.vo.ShoppingCart;

public interface ShoppingCartDao {
	// 新增商品到購物車，回傳整數
	List<ShoppingCart> insertToCart(ShoppingCart shoppingCart);
}
