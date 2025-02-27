package web.myfavorites.service;

import web.myfavorites.vo.MyFavorites;


public interface MyFavoritesService {
	// 加入購物車
	String AddToMyFavorites(MyFavorites myFavorites);
	// 找到所有產品
	String findAllFavorites();
	
	String deleteFavorites();
}
