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
		List<MyFavorites> existingFavorites = dao.selectAllFavorites();
		// 檢查是否已經存在相同的 ACTIVITY_ID
	    boolean isDuplicate = existingFavorites.stream()
	        .anyMatch(fav -> fav.getActivity_id() == myFavorites.getActivity_id());

	    if (isDuplicate) {
	        System.out.println("此活動已被收藏，不可重複新增！");
	        return null;
	    }
	    
		return dao.insertToFavorites(myFavorites);
	}

	@Override
	public List<MyFavorites> findAllFavorites() {
		return dao.selectAllFavorites();
	}
	
}
