package web.shoppingcart.dao;

import java.util.List;

import web.shoppingcart.vo.ShoppingCart;

public interface MemberShoppingCartDao {
//	查詢某個會員的購物車列表
	List<ShoppingCart> selectByMerberId(Integer id);
	
//	會員刪除某個購物車Item
	int deleteScItembyId(Integer id);

}
