package web.myfavorites.dao;

import java.util.List;

import web.myfavorites.vo.MyFavorites;

public interface MyFavoritesDao {
	// 新增
	int insert(MyFavorites myFavorites);

	// 選擇多個商品
	List<MyFavorites> selectAll();
	
	MyFavorites selectByMfId(Integer id);

	// 刪除
	int delete(Integer id);
}
