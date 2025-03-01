package web.myfavorites.service.impl;

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
	
	// 加入我的最愛
	@Override
	public String AddToMyFavorites(MyFavorites myFavorites) {
		int existingFavorites = dao.selectAllFavorites();
		
		if (existingFavorites > 0) {
	        System.out.println("此活動已被收藏，不可重複新增！");
	        return null; 
	    }
	    
	    int resultCount = dao.insertToFavorites(myFavorites);
	    
	    return resultCount > 0 ? null : "發生錯誤，請聯絡客服";
	}
	
	// 找回所有活動
	@Override
	public String findAllFavorites() {
		int resultCount = dao.selectAllFavorites();
		
		return resultCount > 0 ? null : "找不到活動，請聯絡客服";
	}

	@Override
	public String deleteFavorites() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
