package web.myfavorites.service.impl;

import java.util.List;

import javax.naming.NamingException;

import web.myfavorites.dao.MyFavoritesDao;
import web.myfavorites.dao.impl.MyFavoritesDaoImpl;
import web.myfavorites.service.MyFavoritesService;
import web.myfavorites.vo.MyFavorites;

public class MyFavoritesServiceImpl implements MyFavoritesService{
	private MyFavoritesDao dao;
	
	public MyFavoritesServiceImpl() throws NamingException {
		dao = new MyFavoritesDaoImpl();
	}

	@Override
	public List<MyFavorites> AddToMyFavorites(MyFavorites myFavorites) {
		return dao.insertToFavorites(myFavorites);
	}

	@Override
	public List<MyFavorites> findAllFavorites() {
		return dao.selectAllFavorites();
	}
	
}
