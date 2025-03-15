package web.order.dao.impl;

// 曉恩的檔案

import static core.util.JdbcConstants.PASSWORD;
import static core.util.JdbcConstants.URL;
import static core.util.JdbcConstants.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.NamingException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;

import web.order.dao.OrderDao;
import web.order.vo.Orders;

@Repository
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
	
	
	@PersistenceContext
	private Session session;

	
	// 不須付款的新增訂單
	@Override
	public int insert(Orders order) {
		session.persist(order);
		return 1;
	}


	@Override
	public Orders selectOrderbyId(Integer orderId) {
		Orders order = session.get(Orders.class, orderId);
		return order;
	}
	
	
	
	

	

	
	
}
