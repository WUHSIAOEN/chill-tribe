package web.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.shoppingcart.dao.MemberShoppingCartDao;
import web.shoppingcart.service.MemberShoppingCartService;
import web.shoppingcart.vo.ShoppingCart;

@Service
@Transactional
public class MemberShoppingCartServiceImpl implements MemberShoppingCartService{
	
	@Autowired
	private MemberShoppingCartDao dao;
	
//	拿會員的購物車列表
	@Override
	public List<ShoppingCart> getMemberShopppingCartItems(Integer memberId) {
		return dao.selectByMerberId(memberId);
	}

}
