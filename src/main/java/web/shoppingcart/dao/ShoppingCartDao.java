package web.shoppingcart.dao;

import java.util.List;

import web.shoppingcart.vo.ShoppingCart;

public interface ShoppingCartDao {

	int insert(ShoppingCart shoppingCart);

	List<ShoppingCart> selectAll();
	
	ShoppingCart selectByScId(Integer id);
	
	int update(ShoppingCart shoppingCart);

	int delete(Integer id);
}
