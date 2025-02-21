package web.shoppingcart.service.impl;

import java.util.List;

import javax.naming.NamingException;

import web.shoppingcart.dao.ShoppingCartDao;
import web.shoppingcart.dao.impl.ShoppingCartDaoImpl;
import web.shoppingcart.service.ShoppingCartService;
import web.shoppingcart.vo.ShoppingCart;

public class ShoppingCartServiceImpl implements ShoppingCartService {
	private ShoppingCartDao dao;
	
	public ShoppingCartServiceImpl() throws NamingException {
		dao = new ShoppingCartDaoImpl();
	}

	@Override
	public List<ShoppingCart> AddToCart(ShoppingCart shppingCart) {
		// TODO Auto-generated method stub
		return dao.insertToCart(shppingCart);
	}

}
