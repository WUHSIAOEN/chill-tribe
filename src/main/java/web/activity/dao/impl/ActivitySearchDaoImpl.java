package web.activity.dao.impl;

import static core.util.JdbcConstants.PASSWORD;
import static core.util.JdbcConstants.URL;
import static core.util.JdbcConstants.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import web.activity.dao.ActivitySearchDao;
import web.activity.vo.Activity2;
//import web.activity.vo.ActivityImage;
import web.activity.vo.ActivityImage;
import web.activity.vo.IndexActivityCard;

public class ActivitySearchDaoImpl implements ActivitySearchDao {
//	private DataSource ds;
	private HikariDataSource ds;

	public ActivitySearchDaoImpl() throws NamingException {
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
	@Override
	public List<ActivityImage> selectAllActivityImages() {
		String sql = "select * from ACTIVITY_IMAGES";

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			List<ActivityImage> list = new ArrayList<>();
			while (rs.next()) {
				ActivityImage activityImage = new ActivityImage();
				activityImage.setActivityImageId(rs.getInt("activity_image_id"));
				activityImage.setActivityId(rs.getInt("activity_id"));
				activityImage.setImageName(rs.getString("image_name"));
				activityImage.setImageBase64(rs.getString("image_base64"));
				list.add(activityImage);
//				System.out.println(list);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IndexActivityCard> selectActivityOrderByStart() {
		String sql = "SELECT activity_id, activity_name, act.supplier_id, supplier_name, act.city_id, city_name, act.district_id, district_name, act.address, unit_price, min_participants, max_participants, category, start_date_time, end_date_time, status, approved, inventory_count, create_time"
				+ " FROM activities AS act" + " JOIN suppliers AS spl" + " ON act.supplier_id = spl.supplier_id"
				+ " JOIN cities AS ct" + " ON act.city_id = ct.city_id" + " JOIN districts AS dstx"
				+ " ON act.district_id = dstx.district_id" + " WHERE approved = 1 AND status = 1"
				+ " ORDER BY start_date_time" + " LIMIT 6;";

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {

			List<IndexActivityCard> list = new ArrayList<>();
			while (rs.next()) {
				IndexActivityCard indexActivityCard = new IndexActivityCard();
				indexActivityCard.setActivityId(rs.getInt("activity_id"));
				indexActivityCard.setActivityName(rs.getString("activity_name"));
				indexActivityCard.setSupplierId(rs.getInt("supplier_id"));
				indexActivityCard.setSupplierName(rs.getString("supplier_name"));
				indexActivityCard.setCityId(rs.getInt("city_id"));
				indexActivityCard.setCityName(rs.getString("city_name"));
				indexActivityCard.setDistrictId(rs.getInt("district_id"));
				indexActivityCard.setDistrictName(rs.getString("district_name"));
				indexActivityCard.setAddress(rs.getString("address"));
				indexActivityCard.setUnitPrice(rs.getInt("unit_price"));
				indexActivityCard.setMinParticipants(rs.getInt("min_participants"));
				indexActivityCard.setMaxParticipants(rs.getInt("max_participants"));
				indexActivityCard.setCategory(rs.getString("category"));
				indexActivityCard.setStartDateTime(rs.getTimestamp("start_date_time"));
				indexActivityCard.setEndDateTime(rs.getTimestamp("end_date_time"));
				indexActivityCard.setStatus(rs.getInt("status"));
				indexActivityCard.setApproved(rs.getBoolean("approved"));
				indexActivityCard.setInventoryCount(rs.getInt("inventory_count"));
				indexActivityCard.setCreateTime(rs.getTimestamp("create_time"));
				list.add(indexActivityCard);
//				System.out.println(list);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ActivityImage> selectActivityImageById(Integer activityId) {
		String sql = "SELECT * FROM activity_images" + " WHERE activity_id = ?;";

		try (Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setInt(1, activityId);
			List<ActivityImage> list = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ActivityImage activityImage = new ActivityImage();
				activityImage.setActivityImageId(rs.getInt("activity_image_id"));
				activityImage.setActivityId(rs.getInt("activity_id"));
				activityImage.setImageName(rs.getString("image_name"));
				activityImage.setImageBase64(rs.getString("image_base64"));
				list.add(activityImage);
//				System.out.println(list);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IndexActivityCard> selectByNameCatgoryRegion(String actname, String catgory, String region) {
		// 要判斷有哪些查詢條件
		int offset = 0; // 用偏移量
		StringBuilder sql = new StringBuilder("SELECT * FROM activities AS act"
				+ " JOIN suppliers AS spl" + " ON act.supplier_id = spl.supplier_id"
				+ " JOIN cities AS ct ON act.city_id = ct.city_id"
				+ " JOIN districts AS dstx ON act.district_id = dstx.district_id"
				+ " WHERE 1=1"); 
		// 判斷是否每個參數都有值
		if (region != "") {
			sql.append(" AND ct.region = ?");
		}
		if (catgory != "") {
			sql.append(" AND act.category = ?");
		}
		if (actname != "") {
			String[] keywords = actname.split("");
			int index = 0;
			while (index < keywords.length) {
				sql.append(" AND act.activity_name LIKE ?");
				index ++;
			}
		}
		sql.append(";");
//		System.out.println(sql);
		
		try (Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			if (region != "") {
				pstmt.setString(1, region);
				offset++;
			}
			if (catgory != "") {
				pstmt.setString(1 + offset, catgory);
				offset++;
			}
			if (actname != "") {
				String[] keywords = actname.split("");
				for (String kw : keywords) {
					pstmt.setString(1 + offset, '%' + kw + '%');
					offset++;
				}
			}
//			System.out.println(pstmt);
			List<IndexActivityCard> list = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				IndexActivityCard indexActivityCard = new IndexActivityCard();
				indexActivityCard.setActivityId(rs.getInt("activity_id"));
				indexActivityCard.setActivityName(rs.getString("activity_name"));
				indexActivityCard.setSupplierId(rs.getInt("supplier_id"));
				indexActivityCard.setSupplierName(rs.getString("supplier_name"));
				indexActivityCard.setCityId(rs.getInt("city_id"));
				indexActivityCard.setCityName(rs.getString("city_name"));
				indexActivityCard.setDistrictId(rs.getInt("district_id"));
				indexActivityCard.setDistrictName(rs.getString("district_name"));
				indexActivityCard.setAddress(rs.getString("address"));
				indexActivityCard.setUnitPrice(rs.getInt("unit_price"));
				indexActivityCard.setMinParticipants(rs.getInt("min_participants"));
				indexActivityCard.setMaxParticipants(rs.getInt("max_participants"));
				indexActivityCard.setCategory(rs.getString("category"));
				indexActivityCard.setStartDateTime(rs.getTimestamp("start_date_time"));
				indexActivityCard.setEndDateTime(rs.getTimestamp("end_date_time"));
				indexActivityCard.setStatus(rs.getInt("status"));
				indexActivityCard.setApproved(rs.getBoolean("approved"));
				indexActivityCard.setInventoryCount(rs.getInt("inventory_count"));
				indexActivityCard.setCreateTime(rs.getTimestamp("create_time"));
				list.add(indexActivityCard);
			}
//			System.out.println(list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
