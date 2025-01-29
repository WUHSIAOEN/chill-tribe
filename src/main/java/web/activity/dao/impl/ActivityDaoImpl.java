// 這裡我的connection 先用曉恩的，沒用講義上的。

package web.activity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.activity.dao.ActivityDao;
import web.activity.vo.Activity;

public class ActivityDaoImpl implements ActivityDao {
	private DataSource ds;

	public ActivityDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chill_project");
		if (ds != null) {
			System.out.println("DataSource found!");
		} else {
			System.out.println("DataSource not found.");
		}
	}

	// 新增活動
	@Override
	public int insert(Activity activity) {
		// SQL 語句：根據 activities 表格新增資料
		final String SQL = "INSERT INTO ACTIVITIES (ACTIVITY_PREFIX, ACTIVITY_NAME, SUPPLIER_ID, ADDRESS, UNIT_PRICE, "
				+ "MIN_PARTICIPANTS, MAX_PARTICIPANTS, DESCRIPTION, CATEGORY, START_DATE_TIME, END_DATE_TIME, "
				+ "STATUS, NOTE, CITY, DISTRICT, INVENTORY_COUNT, CREATED_TIME, LATITUDE, LONGITUDE, "
				+ "INVENTORY_UPDATE_TIME, TICKETS_ACTIVATE_TIME, TICKETS_EXPIRED_TIME) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, activity.getActivityPrefix());
			pstmt.setString(2, activity.getActivityName());
			pstmt.setInt(3, activity.getSupplierId());
			pstmt.setString(4, activity.getAddress());
			pstmt.setInt(5, activity.getUnitPrice());
			pstmt.setInt(6, activity.getMinParticipants());
			pstmt.setInt(7, activity.getMaxParticipants());
			pstmt.setString(8, activity.getDescription());
			pstmt.setString(9, activity.getCategory());
			pstmt.setTimestamp(10, activity.getStartDateTime());
			pstmt.setTimestamp(11, activity.getEndDateTime());
			pstmt.setInt(12, activity.getStatus());
			pstmt.setString(13, activity.getNote());
			pstmt.setString(14, activity.getCity());
			pstmt.setString(15, activity.getDistrict());
			pstmt.setInt(16, activity.getInventoryCount());
			pstmt.setTimestamp(17, activity.getCreatedTime());
			pstmt.setString(18, activity.getLatitude());
			pstmt.setString(19, activity.getLongitude());
			pstmt.setTimestamp(20, activity.getInventoryUpdateTime());
			pstmt.setTimestamp(21, activity.getTicketsActivateTime());
			pstmt.setTimestamp(22, activity.getTicketsExpiredTime());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Activity> selectAll() {
		final String SQL = "SELECT * FROM ACTIVITIES";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			ResultSet rs = pstmt.executeQuery();

			List<Activity> list = new ArrayList<>();
			while (rs.next()) {
				Activity activity = new Activity();
				activity.setActivityId(rs.getInt("activityId"));
				activity.setActivityPrefix(rs.getString("activityPrefix"));
				activity.setActivityName(rs.getString("activityName"));
				activity.setSupplierId(rs.getInt("supplierId"));
				activity.setAddress(rs.getString("address"));
				activity.setUnitPrice(rs.getInt("unitPrice"));
				activity.setMinParticipants(rs.getInt("minParticipants"));
				activity.setMaxParticipants(rs.getInt("maxParticipants"));
				activity.setDescription(rs.getString("description"));
				activity.setCategory(rs.getString("category"));
				activity.setStartDateTime(rs.getTimestamp("startDateTime"));
				activity.setEndDateTime(rs.getTimestamp("endDateTime"));
				activity.setStatus(rs.getInt("status"));
				activity.setNote(rs.getString("note"));
				activity.setApproved(rs.getInt("approved"));
				activity.setCity(rs.getString("city"));
				activity.setDistrict(rs.getString("district"));
				activity.setInventoryCount(rs.getInt("inventoryCount"));
				activity.setInventoryUpdateTime(rs.getTimestamp("inventoryUpdateTime"));
				activity.setCreatedTime(rs.getTimestamp("createdTime"));
				activity.setLatitude(rs.getString("latitude"));
				activity.setLongitude(rs.getString("longitude"));
				activity.setTicketsActivateTime(rs.getTimestamp("ticketsActivateTime"));
				activity.setTicketsExpiredTime(rs.getTimestamp("ticketsExpiredTime"));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
	
//  更新活動
	@Override
	public int update(Activity activity) {
		// TODO Auto-generated method stub
		return 0;
	}
	
//  刪除活動
	@Override
	public int deletById(Integer activityId) {
		String SQL = "delete from ACTIVITIES where activityId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, activityId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
