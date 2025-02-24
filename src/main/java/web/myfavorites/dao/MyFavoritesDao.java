package web.myfavorites.dao;

import java.util.List;

import web.myfavorites.vo.MyFavorites;

public interface MyFavoritesDao {
	// 新增商品到購物車
	List<MyFavorites> insertToFavorites(MyFavorites myFavorites);
	// 選擇多個商品
	List<MyFavorites> selectAllFavorites();
	// 刪除單個珊品
	int deleteFavorites(Integer id);
}
