package web.activity.dao.impl;

//import static web.util.JdbcConstant.PASSWORD;
//import static web.util.JdbcConstant.URL;
//import static web.util.JdbcConstant.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.activity.dao.ActivitySearchDao;
import web.activity.vo.Activity2;
//import web.activity.vo.ActivityImage;

public class ActivitySearchDaoImpl implements ActivitySearchDao {
	private DataSource ds;
//	private HikariDataSource ds;

	public ActivitySearchDaoImpl() throws NamingException{
		// JNDI Tomcat 會報錯先註解
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
		if (ds != null) {
			System.out.println("DataSource found!");
		} else {
			System.out.println("DataSource not found.");
		}

//		ds = new HikariDataSource();
//		ds.setJdbcUrl(URL);
//		ds.setUsername(USER);
//		ds.setPassword(PASSWORD);
//		ds.addDataSourceProperty("cachePrepStmts", true);
//		ds.addDataSourceProperty("preStmtCacheSize", 250);
//		ds.addDataSourceProperty("preStmtCacheSqlLimit", 2048);
	}
	
	// 查詢所有的活動回來
	@Override
	public List<Activity2> selectAllActivities() {
		String sql = "select * from ACTIVITIES";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			List<Activity2> list = new ArrayList<>();
//				System.out.println("query initial");
			while (rs.next()) {
				Activity2 activiti = new Activity2();
				activiti.setActivityId(rs.getInt("activity_id"));
				activiti.setActivityName(rs.getString("activity_name"));
				activiti.setSupplierId(rs.getInt("supplier_id"));
				activiti.setCityId(rs.getInt("city_id"));
				activiti.setDistrictId(rs.getInt("district_id"));
				activiti.setAddress(rs.getString("address"));
				activiti.setUnitPrice(rs.getInt("unit_price"));
				activiti.setMinParticipants(rs.getInt("min_participants"));
				activiti.setMaxParticipants(rs.getInt("max_participants"));
				activiti.setDescription(rs.getString("description"));
				activiti.setPrecaution(rs.getString("precaution"));
				activiti.setCategory(rs.getString("category"));
				activiti.setStartDateTime(rs.getTimestamp("start_date_time"));
				activiti.setEndDateTime(rs.getTimestamp("end_date_time"));
				activiti.setStatus(rs.getInt("status"));
				activiti.setNote(rs.getString("note"));
				activiti.setApproved(rs.getBoolean("approved"));
				activiti.setInventoryCount(rs.getInt("inventory_count"));
				activiti.setStatus(rs.getInt("status"));
				activiti.setLatitude(rs.getString("latitude"));
				activiti.setLongitude(rs.getString("longitude"));
				activiti.setTicketsActivateTime(rs.getTimestamp("tickets_activate_time"));
				activiti.setTicketsExpiredTime(rs.getTimestamp("tickets_expired_time"));
				activiti.setInventoryUpdateTime(rs.getTimestamp("inventory_update_time"));
				activiti.setCreateTime(rs.getTimestamp("create_time"));
				list.add(activiti);
//				System.out.println(list);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 查詢所有的活動圖片回來
//	@Override
//	public List<ActivityImage> selectAllActivityImages() {
//		String sql = "select * from ACTIVITY_IMAGES";
//
//		try (Connection conn = ds.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				ResultSet rs = pstmt.executeQuery()) {
//
//			List<ActivityImage> list = new ArrayList<>();
//			while (rs.next()) {
//				ActivityImage activityImage = new ActivityImage();
//				activityImage.setActivityImageId(rs.getInt("activity_image_id"));
//				activityImage.setActivityId(rs.getInt("activity_id"));
//				activityImage.setImageName(rs.getString("image_name"));
//				activityImage.setImageBase64(rs.getString("image_base64"));
//				list.add(activityImage);
//				System.out.println(list);
//			}
//			return list;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
