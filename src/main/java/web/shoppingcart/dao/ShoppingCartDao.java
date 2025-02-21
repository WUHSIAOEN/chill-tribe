package web.shoppingcart.dao;

import java.util.List;

import web.shoppingcart.vo.ShoppingCart;

public interface ShoppingCartDao {
	// 新增商品到購物車
	List<ShoppingCart> insertToCart(ShoppingCart shoppingCart);
	// 選擇多個商品
	List<ShoppingCart> selectAllCart();
	// 刪除單個珊品
	int deleteCart(Integer id);
}
