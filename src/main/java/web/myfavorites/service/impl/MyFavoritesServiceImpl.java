package web.myfavorites.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.myfavorites.dao.MyFavoritesDao;
import web.myfavorites.service.MyFavoritesService;
import web.myfavorites.vo.MyFavorites;

@Service
@Transactional
public class MyFavoritesServiceImpl implements MyFavoritesService {
	@Autowired
	private MyFavoritesDao dao;

//	public MyFavoritesServiceImpl() throws NamingException {
//		dao = new MyFavoritesDaoImpl();
//	}

	@Override
	public MyFavorites addOrUpdate(MyFavorites myFavorites) {
		int resultCount = dao.update(myFavorites);
		if (resultCount > 0) {
			// 更新成功的情況
			myFavorites.setMessage("刪除商品成功");
			myFavorites.setSuccessful(true);
		} else {
			// 更新失敗的情況
			myFavorites.setMessage("刪除商品失敗");
			myFavorites.setSuccessful(false);
		}

		return myFavorites;
	}

//	@Override
//	public String AddToMyFavorites(MyFavorites myFavorites) {
//		int existingFavorites = dao.selectAllFavorites();
//		
//		if (existingFavorites > 0) {
//	        System.out.println("此活動已被收藏，不可重複新增！");
//	        return null; 
//	    }
//	    
//	    int resultCount = dao.insertToFavorites(myFavorites);
//	    
//	    return resultCount > 0 ? null : "發生錯誤，請聯絡客服";
//	}

	@Override
	public List<MyFavorites> findAll() {
		return dao.selectAll();
	}

	@Override
	public List<MyFavorites> findByMfId(Integer id) {
		return dao.selectByMfId(id);
	}

//	@Override
//	public String findAllFavorites() {
//		int resultCount = dao.selectAllFavorites();
//		
//		return resultCount > 0 ? null : "找不到活動，請聯絡客服";
//	}

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
//
//	@Override
//	public String deleteFavorites() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
