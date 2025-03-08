package web.shoppingcart.dao;

import java.util.List;

import web.shoppingcart.vo.ShoppingCart;

public interface MemberShoppingCartDao {
	
	List<ShoppingCart> selectByMerberId(Integer id);

}
