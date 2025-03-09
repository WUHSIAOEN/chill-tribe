package web.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.shoppingcart.dao.ShoppingCartDao;
import web.shoppingcart.service.ShoppingCartService;
import web.shoppingcart.vo.ShoppingCart;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartDao dao;

	@Override
	public ShoppingCart addOrUpdate(ShoppingCart shoppingCart) {
		int resultCount = dao.update(shoppingCart);

        if (resultCount > 0) {
            shoppingCart.setMessage("操作成功");
            shoppingCart.setSuccessful(true);
        } else {
            shoppingCart.setMessage("操作失敗");
            shoppingCart.setSuccessful(false);
        }

        return shoppingCart;
    }

	@Override
	public List<ShoppingCart> findAll() {
		return dao.selectAll();
	}

	@Override
	public ShoppingCart findByScId(Integer id) {
		return dao.selectByScId(id);
	}

	@Override
	public boolean remove(Integer id) {
		try {
			boolean result = dao.delete(id) > 0;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


//	public ShoppingCartServiceImpl() throws NamingException {
//		dao = new ShoppingCartDaoImpl();
//	}
//
//	@Override
//	public List<ShoppingCart> AddToCart(ShoppingCart shoppingCart) {
//		// TODO Auto-generated method stub
//		return dao.insertToCart(shoppingCart);
//	}
//
//	@Override
//	public List<ShoppingCart> findAllProducts() {
//		return dao.selectAllCart();
//	}

}
