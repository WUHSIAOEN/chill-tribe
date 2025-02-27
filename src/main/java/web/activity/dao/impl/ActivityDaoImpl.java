// 這裡我的connection 先用曉恩的，沒用講義上的。

package web.activity.dao.impl;

import static core.util.JdbcConstants.PASSWORD;

import static core.util.JdbcConstants.URL;
import static core.util.JdbcConstants.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;

import web.activity.dao.ActivityDao;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

public class ActivityDaoImpl implements ActivityDao {
	
//	private DataSource ds;
	private HikariDataSource ds;

	public ActivityDaoImpl() throws NamingException {
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

	// 新增活動
	@Override
	public int insert(Activities activity) {
		// SQL 語句：根據 activities 表格新增資料
		final String SQL = "INSERT INTO ACTIVITIES (SUPPLIER_ID, ACTIVITY_NAME, CITY_ID, DISTRICT_ID, ADDRESS, "
				+ "START_DATE_TIME, END_DATE_TIME, UNIT_PRICE, MIN_PARTICIPANTS, MAX_PARTICIPANTS, "
				+ "INVENTORY_COUNT, DESCRIPTION, CATEGORY, PRECAUTION) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, activity.getSupplierId());
			pstmt.setString(2, activity.getActivityName());
			pstmt.setInt(3, activity.getCity_id());
			pstmt.setInt(4, activity.getDistrict_id());
			pstmt.setString(5, activity.getAddress());
			pstmt.setTimestamp(6, activity.getStartDateTime());
			pstmt.setTimestamp(7, activity.getEndDateTime());
			pstmt.setInt(8, activity.getUnitPrice());
			pstmt.setInt(9, activity.getMinParticipants());
			pstmt.setInt(10, activity.getMaxParticipants());
			pstmt.setInt(11, activity.getInventoryCount());
			pstmt.setString(12, activity.getDescription());
			pstmt.setString(13, activity.getCategory());
			pstmt.setString(14, activity.getPrecaution());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 新增多張活動圖片
	@Override
	public int insertActivityImage(ActivityImage activityImage) {
		final String SQL = "INSERT INTO activity_images (activity_id, image_name, image_base64) VALUES (?, ?, ?)";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL)
		) {
			pstmt.setInt(1, activityImage.getActivityId());
			pstmt.setString(2, activityImage.getImageName());
			pstmt.setString(3, activityImage.getImageBase64());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 更新活動
	@Override
	public int update(Activities activity) {

		StringBuilder sql = new StringBuilder("UPDATE Activities SET ");

		if (activity.getActivityName() != null && !activity.getActivityName().isEmpty()) {
			sql.append("ACTIVITY_NAME = ?, ");
		}
		if (activity.getSupplierId() != null) {
			sql.append("SUPPLIER_ID = ?, ");
		}
		if (activity.getAddress() != null && !activity.getAddress().isEmpty()) {
			sql.append("ADDRESS = ?, ");
		}
		if (activity.getUnitPrice() != null) {
			sql.append("UNIT_PRICE = ?, ");
		}
		if (activity.getMinParticipants() != null) {
			sql.append("MIN_PARTICIPANTS = ?, ");
		}
		if (activity.getMaxParticipants() != null) {
			sql.append("MAX_PARTICIPANTS = ?, ");
		}
		if (activity.getDescription() != null && !activity.getDescription().isEmpty()) {
			sql.append("DESCRIPTION = ?, ");
		}
		if (activity.getPrecaution() != null && !activity.getPrecaution().isEmpty()) {
			sql.append("PRECAUTION = ?, ");
		}
		if (activity.getCategory() != null && !activity.getCategory().isEmpty()) {
			sql.append("CATEGORY = ?, ");
		}
		if (activity.getStartDateTime() != null) {
			sql.append("START_DATE_TIME = ?, ");
		}
		if (activity.getEndDateTime() != null) {
			sql.append("END_DATE_TIME = ?, ");
		}
		if (activity.getCity_id() != null) {
			sql.append("CITY_ID = ?, ");
		}
		if (activity.getDistrict_id() != null) {
			sql.append("DISTRICT_ID = ?, ");
		}
		if (activity.getInventoryCount() != null) {
			sql.append("INVENTORY_COUNT = ?, ");
		}
		if (activity.getNote() != null && !activity.getNote().isEmpty()) {
			sql.append("NOTE = ?, ");
		}
//		if (activity.getStatus() != null) {
//			sql.append("STATUS = ?, ");
//		}

//		if (activity.getApproved() != null) {
//			sql.append("APPROVED = ?, ");
//		}

//		if (activity.getInventoryUpdateTime() != null) {
//			sql.append("INVENTORY_UPDATE_TIME = ?, ");
//		}
//		if (activity.getCreateTime() != null) {
//			sql.append("CREATE_TIME = ?, ");
//		}
//		if (activity.getLatitude() != null && !activity.getLatitude().isEmpty()) {
//			sql.append("LATITUDE = ?, ");
//		}
//		if (activity.getLongitude() != null && !activity.getLongitude().isEmpty()) {
//			sql.append("LONGITUDE = ?, ");
//		}
//		if (activity.getTicketsActivateTime() != null) {
//			sql.append("TICKETS_ACTIVATE_TIME = ?, ");
//		}
//		if (activity.getTicketsExpiredTime() != null) {
//			sql.append("TICKETS_EXPIRED_TIME = ?, ");
//		}

		String sqlQuery = sql.toString();
		if (sqlQuery.endsWith(", ")) {
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
		}

		// 確保在更新語句中有要更新的欄位
		if (sqlQuery.equals("UPDATE ACTIVITIES SET")) {
			return 0; // 如果沒有任何欄位需要更新，則返回 0
		}

		// 加上 WHERE 條件來指定更新的會員 (假設使用 ACTIVITY_ID 或其他識別欄位)
		sqlQuery += " WHERE ACTIVITY_ID = ?"; // 假設 ACTIVITY_ID 是更新條件

		int parameterIndex = 1; // 偏移量

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sqlQuery);) {

			if (activity.getActivityName() != null && !activity.getActivityName().isEmpty()) {
				pstmt.setString(parameterIndex, activity.getActivityName());
				parameterIndex++;
			}
			if (activity.getSupplierId() != null) {
				pstmt.setInt(parameterIndex, activity.getSupplierId());
				parameterIndex++;
			}
			if (activity.getAddress() != null && !activity.getAddress().isEmpty()) {
				pstmt.setString(parameterIndex, activity.getAddress());
				parameterIndex++;
			}
			if (activity.getUnitPrice() != null) {
				pstmt.setInt(parameterIndex, activity.getUnitPrice());
				parameterIndex++;
			}
			if (activity.getMinParticipants() != null) {
				pstmt.setInt(parameterIndex, activity.getMinParticipants());
				parameterIndex++;
			}
			if (activity.getMaxParticipants() != null) {
				pstmt.setInt(parameterIndex, activity.getMaxParticipants());
				parameterIndex++;
			}
			if (activity.getDescription() != null && !activity.getDescription().isEmpty()) {
				pstmt.setString(parameterIndex, activity.getDescription());
				parameterIndex++;
			}
			if (activity.getPrecaution() != null && !activity.getPrecaution().isEmpty()) {
				pstmt.setString(parameterIndex, activity.getPrecaution());
				parameterIndex++;
			}
			if (activity.getCategory() != null && !activity.getCategory().isEmpty()) {
				pstmt.setString(parameterIndex, activity.getCategory());
				parameterIndex++;
			}
			if (activity.getStartDateTime() != null) {
				pstmt.setTimestamp(parameterIndex, activity.getStartDateTime());
				parameterIndex++;
			}
			if (activity.getEndDateTime() != null) {
				pstmt.setTimestamp(parameterIndex, activity.getEndDateTime());
				parameterIndex++;
			}
			if (activity.getNote() != null && !activity.getNote().isEmpty()) {
				pstmt.setString(parameterIndex, activity.getNote());
				parameterIndex++;
			}
			if (activity.getApproved() != null) {
				pstmt.setInt(parameterIndex, activity.getApproved());
				parameterIndex++;
			}
			if (activity.getCity_id() != null) {
				pstmt.setInt(parameterIndex, activity.getCity_id());
				parameterIndex++;
			}
			if (activity.getInventoryCount() != null) {
				pstmt.setInt(parameterIndex, activity.getInventoryCount());
				parameterIndex++;
			}
//			if (activity.getStatus() != null) {
//				pstmt.setInt(parameterIndex++, activity.getStatus());
//			}
//			if (activity.getDistrictId() != null) {
//				pstmt.setInt(parameterIndex++, activity.getDistrictId());
//			}

//			if (activity.getInventoryUpdateTime() != null) {
//				pstmt.setTimestamp(parameterIndex++, activity.getInventoryUpdateTime());
//			}
//			if (activity.getCreateTime() != null) {
//				pstmt.setTimestamp(parameterIndex++, activity.getCreateTime());
//			}
//			if (activity.getLatitude() != null && !activity.getLatitude().isEmpty()) {
//				pstmt.setString(parameterIndex++, activity.getLatitude());
//			}
//			if (activity.getLongitude() != null && !activity.getLongitude().isEmpty()) {
//				pstmt.setString(parameterIndex++, activity.getLongitude());
//			}
//			if (activity.getTicketsActivateTime() != null) {
//				pstmt.setTimestamp(parameterIndex++, activity.getTicketsActivateTime());
//			}
//			if (activity.getTicketsExpiredTime() != null) {
//				pstmt.setTimestamp(parameterIndex++, activity.getTicketsExpiredTime());
//			}

			pstmt.setInt(parameterIndex, activity.getActivityId());
			parameterIndex++;

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return -1;
	}

	// 將活動取消
	@Override
	public int updateteCancel(Activities activity) {
		String SQL = "UPDATE ACTIVITIES SET STATUS = 0 WHERE ACTIVITY_ID = ?";

	    try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	    	pstmt.setInt(1, activity.getActivityId());
	        return pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1; // 代表更新失敗
	}

	// 刪除活動
	@Override
	public int deletActivityById(Integer activityId) {
		String SQL = "delete from ACTIVITIES where ACTIVITY_ID = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, activityId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 全選活動
	@Override
	public List<Activities> selectAllActivity() {
		final String SQL = "SELECT * FROM ACTIVITIES";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			ResultSet rs = pstmt.executeQuery();

			List<Activities> list = new ArrayList<>();
			while (rs.next()) {
				Activities activity = new Activities();
				activity.setActivityId(rs.getInt("activity_id"));
				activity.setActivityPrefix(rs.getString("activity_prefix"));
				activity.setActivityName(rs.getString("activity_name"));
				activity.setSupplierId(rs.getInt("supplier_id"));
				activity.setAddress(rs.getString("address"));
				activity.setUnitPrice(rs.getInt("unit_price"));
				activity.setMinParticipants(rs.getInt("min_participants"));
				activity.setMaxParticipants(rs.getInt("max_participants"));
				activity.setDescription(rs.getString("description"));
				activity.setCategory(rs.getString("category"));
				activity.setPrecaution(rs.getString("precaution"));
				activity.setStartDateTime(rs.getTimestamp("start_date_time"));
				activity.setEndDateTime(rs.getTimestamp("end_date_time"));
				activity.setStatus(rs.getInt("status"));
				activity.setNote(rs.getString("note"));
				activity.setApproved(rs.getInt("approved"));
				activity.setCity_id(rs.getInt("city_id"));
				activity.setDistrict_id(rs.getInt("district_id"));
				activity.setInventoryCount(rs.getInt("inventory_count"));
				activity.setInventoryUpdateTime(rs.getTimestamp("inventory_update_time"));
				activity.setCreateTime(rs.getTimestamp("create_time"));
				activity.setLatitude(rs.getString("latitude"));
				activity.setLongitude(rs.getString("longitude"));
				activity.setTicketsActivateTime(rs.getTimestamp("tickets_activate_time"));
				activity.setTicketsExpiredTime(rs.getTimestamp("tickets_expired_time"));
//				System.out.println(list);
				list.add(activity);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 只用活動ID搜尋，傳回其他活動內容
	@Override
	public Activities selectByActivityId(Integer activityId) {
		final String SQL = "SELECT * FROM ACTIVITIES WHERE ACTIVITY_ID = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL);) {
			pstmt.setInt(1, activityId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Activities activity = new Activities();
				activity.setActivityId(rs.getInt("activity_id"));
				activity.setActivityPrefix(rs.getString("activity_prefix"));
				activity.setActivityName(rs.getString("activity_name"));
				activity.setSupplierId(rs.getInt("supplier_id"));
				activity.setAddress(rs.getString("address"));
				activity.setUnitPrice(rs.getInt("unit_price"));
				activity.setMinParticipants(rs.getInt("min_participants"));
				activity.setMaxParticipants(rs.getInt("max_participants"));
				activity.setDescription(rs.getString("description"));
				activity.setCategory(rs.getString("category"));
				activity.setPrecaution(rs.getString("precaution"));
				activity.setStartDateTime(rs.getTimestamp("start_date_time"));
				activity.setEndDateTime(rs.getTimestamp("end_date_time"));
				activity.setStatus(rs.getInt("status"));
				activity.setNote(rs.getString("note"));
				activity.setApproved(rs.getInt("approved"));
				activity.setCity_id(rs.getInt("city_id"));
				activity.setDistrict_id(rs.getInt("district_id"));
				activity.setInventoryCount(rs.getInt("inventory_count"));
				activity.setInventoryUpdateTime(rs.getTimestamp("inventory_update_time"));
				activity.setCreateTime(rs.getTimestamp("create_time"));
				activity.setLatitude(rs.getString("latitude"));
				activity.setLongitude(rs.getString("longitude"));
				activity.setTicketsActivateTime(rs.getTimestamp("tickets_activate_time"));
				activity.setTicketsExpiredTime(rs.getTimestamp("tickets_expired_time"));
				return activity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
