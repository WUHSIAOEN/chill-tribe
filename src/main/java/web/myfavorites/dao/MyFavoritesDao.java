package web.myfavorites.dao;

import web.myfavorites.vo.MyFavorites;

public interface MyFavoritesDao {
	// 新增商品到購物車
	int insertToFavorites(MyFavorites myFavorites);
	// 選擇多個商品
	int selectAllFavorites();
	// 刪除單個珊品
	int deleteFavorites(Integer id);
}
