// 這裡我的connection 先用曉恩的，沒用講義上的。

package web.activity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.activity.dao.ActivityDao;
import web.activity.vo.Activity;

public class ActivityDaoImpl implements ActivityDao {
	private DataSource ds;
	
	public ActivityDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/CHILL_PROJECT");
	}
	
	@Override
	public int apply(Activity activity) {
		// SQL 語句：根據 activities 表格新增資料
		final String SQL = "INSERT INTO ACTIVITIES (ACTIVITY_PREFIX, ACTIVITY_NAME, SUPPLIER_ID, ADDRESS, UNIT_PRICE, " +
		                   "MIN_PARTICIPANTS, MAX_PARTICIPANTS, DESCRIPTION, CATEGORY, START_DATE_TIME, END_DATE_TIME, " +
		                   "STATUS, NOTE, CITY, DISTRICT, INVENTORY_COUNT, CREATED_TIME, LATITUDE, LONGITUDE, " +
		                   "INVENTORY_UPDATE_TIME, TICKETS_ACTIVATE_TIME, TICKETS_EXPIRED_TIME) " +
		                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL)
		) {
			pstmt.setInt(1, activity.getActivityId());
			pstmt.setString(2, activity.getActivityPrefix());
			pstmt.setString(3, activity.getActivityName());
			pstmt.setInt(4, activity.getSupplierId());
			pstmt.setString(5, activity.getAddress());
			pstmt.setInt(6, activity.getUnitPrice());
			pstmt.setInt(7, activity.getMinParticipants());
			pstmt.setInt(8, activity.getMaxParticipants());
			pstmt.setString(9, activity.getDescription());
			pstmt.setString(10, activity.getCategory());
			pstmt.setTimestamp(11, activity.getStartDateTime());
			pstmt.setTimestamp(12, activity.getEndDateTime());
			pstmt.setInt(13, activity.getStatus());
			pstmt.setString(14, activity.getNote());
			pstmt.setString(15, activity.getCity());
			pstmt.setString(16, activity.getDistrict());
			pstmt.setInt(17, activity.getInventoryCount());
			pstmt.setTimestamp(18, activity.getCreatedTime());
			pstmt.setString(19, activity.getLatitude());
			pstmt.setString(20, activity.getLongitude());
			pstmt.setTimestamp(21, activity.getInventoryUpdateTime());
			pstmt.setTimestamp(22, activity.getTicketsActivateTime());
			pstmt.setTimestamp(23, activity.getTicketsExpiredTime());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Activity selectByActivityName(String activityName) {
		// TODO Auto-generated method stub
		return null;
	}
}
