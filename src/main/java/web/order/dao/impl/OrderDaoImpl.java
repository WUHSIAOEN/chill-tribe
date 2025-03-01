package web.order.dao.impl;

import static core.util.JdbcConstants.PASSWORD;
import static core.util.JdbcConstants.URL;
import static core.util.JdbcConstants.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.NamingException;

import com.zaxxer.hikari.HikariDataSource;

import web.order.dao.OrderDao;
import web.order.vo.Orders;

public class OrderDaoImpl implements OrderDao{
	
	private HikariDataSource ds;
	
	public OrderDaoImpl() throws NamingException {
		// JNDI Tomcat 會報錯先註解
//		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
//		if (ds != null) {
//			System.out.println("DataSource found!");
//		} else {
//			System.out.println("DataSource not found.");
//		}

		ds = new HikariDataSource();
		ds.setJdbcUrl(URL);
		ds.setUsername(USER);
		ds.setPassword(PASSWORD);
		ds.addDataSourceProperty("cachePrepStmts", true);
		ds.addDataSourceProperty("preStmtCacheSize", 250);
		ds.addDataSourceProperty("preStmtCacheSqlLimit", 2048);
	}

	
	// 不須付款的新增訂單
	@Override
	public int insert(Orders order) {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO orders"
				+ "(activity_id, member_id, quantity, order_status, payment_method, order_contact, contact_mail, contact_phone, requirement)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
				pstmt.setInt(1, order.getActivityId());
				pstmt.setInt(2, order.getMemberId());
				pstmt.setInt(3, order.getQuantity());
				pstmt.setString(4, order.getOrderStatus());
				pstmt.setString(5, order.getPaymentMethod());
				pstmt.setString(6, order.getOrderContact());
				pstmt.setString(7, order.getContactMail());
				pstmt.setString(8, order.getContactPhone());
				pstmt.setString(9, order.getRequirement());
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
	}
	

	

	
	
}
