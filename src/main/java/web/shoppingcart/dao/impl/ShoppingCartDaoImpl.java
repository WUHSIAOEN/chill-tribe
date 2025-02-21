package web.shoppingcart.dao.impl;

import static core.util.JdbcConstants.PASSWORD;
import static core.util.JdbcConstants.URL;
import static core.util.JdbcConstants.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			
			pstmt.setInt(1, shoppingCart.getActivity_Id());
	        pstmt.setInt(2, shoppingCart.getQuantity());
	        pstmt.setInt(3, shoppingCart.getTotal_Price());
	        pstmt.setInt(4, shoppingCart.getMember_Id());
	        
	        int result = pstmt.executeUpdate();
	        if (result > 0) {
	        	List<ShoppingCart> list = new ArrayList<>();
				list.add(shoppingCart);
				return list;
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
