package web.shoppingcart.service;

import java.util.List;

import web.shoppingcart.vo.ShoppingCart;

public interface MemberShoppingCartService {
	
	// 取得某會員的購物車清單
	List<ShoppingCart> getMemberShopppingCartItems(Integer memberId);
	
}
