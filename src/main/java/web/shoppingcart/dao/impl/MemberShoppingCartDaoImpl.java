package web.shoppingcart.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import web.shoppingcart.dao.MemberShoppingCartDao;
import web.shoppingcart.vo.ShoppingCart;


@Repository
public class MemberShoppingCartDaoImpl implements MemberShoppingCartDao {

	@PersistenceContext
	private Session session;
	
//	查詢某個Member 的購物車items
	@Override
	public List<ShoppingCart> selectByMerberId(Integer id) {
		String hql1 = "FROM ShoppingCart WHERE memberId = ?1";
		Query<ShoppingCart> query1 = session.createQuery(hql1, ShoppingCart.class)
				.setParameter(1, id);
		return query1.getResultList();
	}

}
