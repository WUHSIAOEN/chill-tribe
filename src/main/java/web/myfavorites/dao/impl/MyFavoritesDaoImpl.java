package web.myfavorites.dao.impl;

import static core.util.JdbcConstants.PASSWORD;
import static core.util.JdbcConstants.URL;
import static core.util.JdbcConstants.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.zaxxer.hikari.HikariDataSource;

import web.myfavorites.dao.MyFavoritesDao;
import web.myfavorites.vo.MyFavorites;
import web.shoppingcart.vo.ShoppingCart;

public class MyFavoritesDaoImpl implements MyFavoritesDao {
	private HikariDataSource ds;

	public MyFavoritesDaoImpl() throws NamingException {
		ds = new HikariDataSource();
		ds.setJdbcUrl(URL);
		ds.setUsername(USER);
		ds.setPassword(PASSWORD);
		ds.addDataSourceProperty("cachePrepStmts", true);
		ds.addDataSourceProperty("preStmtCacheSize", 250);
		ds.addDataSourceProperty("preStmtCacheSqlLimit", 2048);
	}

	@Override
	public List<MyFavorites> insertToFavorites(MyFavorites myFavorites) {
		// SQL 語句：根據 activities 表格新增資料
		final String SQL = "INSERT INTO MY_FAVORITES (ACTIVITY_ID, MEMBER_ID) " + "VALUES (?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setInt(1, myFavorites.getActivity_id());
			pstmt.setInt(2, myFavorites.getMember_id());

			int result = pstmt.executeUpdate();
			List<MyFavorites> list = new ArrayList<>();
			list.add(myFavorites);
			if (result > 0) {

				System.out.println(list);

			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MyFavorites> selectAllFavorites() {
		final String SQL = "SELECT * FROM MY_FAVORITES";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			ResultSet rs = pstmt.executeQuery();

			List<MyFavorites> list = new ArrayList<>();
			while (rs.next()) {
				MyFavorites myFavorites = new MyFavorites();
				myFavorites.setActivity_id(rs.getInt("activity_id"));
				myFavorites.setMember_id(rs.getInt("member_id"));
				list.add(myFavorites);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteFavorites(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
