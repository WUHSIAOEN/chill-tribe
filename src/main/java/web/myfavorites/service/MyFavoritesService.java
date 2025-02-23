package web.myfavorites.service;

import java.util.List;

import web.myfavorites.vo.MyFavorites;


public interface MyFavoritesService {
	// 加入購物車
	List<MyFavorites> AddToMyFavorites(MyFavorites myFavorites);
	// 找到所有產品
	List<MyFavorites> findAllFavorites();
}
