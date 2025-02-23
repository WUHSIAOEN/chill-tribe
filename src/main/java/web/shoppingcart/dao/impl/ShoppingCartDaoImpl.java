package web.shoppingcart.dao.impl;

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

import web.shoppingcart.dao.ShoppingCartDao;
import web.shoppingcart.vo.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private HikariDataSource ds;

	public ShoppingCartDaoImpl() throws NamingException {
		ds = new HikariDataSource();
		ds.setJdbcUrl(URL);
		ds.setUsername(USER);
		ds.setPassword(PASSWORD);
		ds.addDataSourceProperty("cachePrepStmts", true);
		ds.addDataSourceProperty("preStmtCacheSize", 250);
		ds.addDataSourceProperty("preStmtCacheSqlLimit", 2048);
	}

	@Override
	public List<ShoppingCart> insertToCart(ShoppingCart shoppingCart) {
		// SQL 語句：根據 activities 表格新增資料
		final String SQL = "INSERT INTO SHOPPING_CART_ITEMS (ACTIVITY_ID, QUANTITY, TOTAL_PRICE, MEMBER_ID) "
				+ "VALUES (?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setInt(1, shoppingCart.getActivity_id());
			pstmt.setInt(2, shoppingCart.getQuantity());
			pstmt.setInt(3, shoppingCart.getTotal_price());
			pstmt.setInt(4, shoppingCart.getMember_id());

			int result = pstmt.executeUpdate();
			List<ShoppingCart> list = new ArrayList<>();
			list.add(shoppingCart);
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
	public List<ShoppingCart> selectAllCart() {
		final String SQL = "SELECT * FROM SHOPPING_CART_ITEMS";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			ResultSet rs = pstmt.executeQuery();

			List<ShoppingCart> list = new ArrayList<>();
			while (rs.next()) {
				ShoppingCart prodcutItems = new ShoppingCart();
				prodcutItems.setActivity_id(rs.getInt("activity_id"));
				prodcutItems.setQuantity(rs.getInt("quantity"));
				prodcutItems.setTotal_price(rs.getInt("total_price"));
				prodcutItems.setMember_id(rs.getInt("member_id"));
				// prodcutItems.setActivityImages(null);
//				prodcutItems.setActivity_name(rs.getString("activity_name"));
//				prodcutItems.setUnit_price(rs.getInt("unit_price"));
				list.add(prodcutItems);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteCart(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
