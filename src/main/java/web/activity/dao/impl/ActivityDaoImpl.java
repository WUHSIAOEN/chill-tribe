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
	
	// 全選活動
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
		int offset = 0;  // 用來計算偏移量，根據欄位是否有設置進行增減
		// SET 用來指定要更新哪些欄位的值。
	    StringBuilder sql = new StringBuilder("UPDATE Activity SET ");

	    // 構建 SQL 語句，根據每個欄位是否為 null 動態添加對應的 UPDATE 項
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
	    if (activity.getCategory() != null && !activity.getCategory().isEmpty()) {
	        sql.append("CATEGORY = ?, ");
	    }
	    if (activity.getStartDateTime() != null) {
	        sql.append("START_DATETIME = ?, ");
	    }
	    if (activity.getEndDateTime() != null) {
	        sql.append("END_DATETIME = ?, ");
	    }
	    if (activity.getStatus() != null) {
	        sql.append("STATUS = ?, ");
	    }
	    if (activity.getNote() != null && !activity.getNote().isEmpty()) {
	        sql.append("NOTE = ?, ");
	    }
	    if (activity.getApproved() != null) {
	        sql.append("APPROVED = ?, ");
	    }
	    if (activity.getCity() != null && !activity.getCity().isEmpty()) {
	        sql.append("CITY = ?, ");
	    }
	    if (activity.getDistrict() != null && !activity.getDistrict().isEmpty()) {
	        sql.append("DISTRICT = ?, ");
	    }
	    if (activity.getInventoryCount() != null) {
	        sql.append("INVENTORY_COUNT = ?, ");
	    }
	    if (activity.getInventoryUpdateTime() != null) {
	        sql.append("INVENTORY_UPDATE_TIME = ?, ");
	    }
	    if (activity.getCreatedTime() != null) {
	        sql.append("CREATED_TIME = ?, ");
	    }
	    if (activity.getLatitude() != null && !activity.getLatitude().isEmpty()) {
	        sql.append("LATITUDE = ?, ");
	    }
	    if (activity.getLongitude() != null && !activity.getLongitude().isEmpty()) {
	        sql.append("LONGITUDE = ?, ");
	    }
	    if (activity.getTicketsActivateTime() != null) {
	        sql.append("TICKETS_ACTIVATE_TIME = ?, ");
	    }
	    if (activity.getTicketsExpiredTime() != null) {
	        sql.append("TICKETS_EXPIRED_TIME = ?, ");
	    }

	    // 刪除最後一個多餘的逗號
	    sql.deleteCharAt(sql.length() - 2);

	    // SUPPLIER_ID 作為識別條件
	    sql.append(" WHERE SUPPLIER_ID = ?");

	    try (
	        Connection conn = ds.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	    ) {
	        // 設置參數
	        int parameterIndex = 1;

	        // 根據條件設置參數的值
	        if (activity.getActivityName() != null && !activity.getActivityName().isEmpty()) {
	            pstmt.setString(parameterIndex++, activity.getActivityName());
	        }
	        if (activity.getSupplierId() != null) {
	            pstmt.setInt(parameterIndex++, activity.getSupplierId());
	        }
	        if (activity.getAddress() != null && !activity.getAddress().isEmpty()) {
	            pstmt.setString(parameterIndex++, activity.getAddress());
	        }
	        if (activity.getUnitPrice() != null) {
	            pstmt.setInt(parameterIndex++, activity.getUnitPrice());
	        }
	        if (activity.getMinParticipants() != null) {
	            pstmt.setInt(parameterIndex++, activity.getMinParticipants());
	        }
	        if (activity.getMaxParticipants() != null) {
	            pstmt.setInt(parameterIndex++, activity.getMaxParticipants());
	        }
	        if (activity.getDescription() != null && !activity.getDescription().isEmpty()) {
	            pstmt.setString(parameterIndex++, activity.getDescription());
	        }
	        if (activity.getCategory() != null && !activity.getCategory().isEmpty()) {
	            pstmt.setString(parameterIndex++, activity.getCategory());
	        }
	        if (activity.getStartDateTime() != null) {
	            pstmt.setTimestamp(parameterIndex++, activity.getStartDateTime());
	        }
	        if (activity.getEndDateTime() != null) {
	            pstmt.setTimestamp(parameterIndex++, activity.getEndDateTime());
	        }
	        if (activity.getStatus() != null) {
	            pstmt.setInt(parameterIndex++, activity.getStatus());
	        }
	        if (activity.getNote() != null && !activity.getNote().isEmpty()) {
	            pstmt.setString(parameterIndex++, activity.getNote());
	        }
	        if (activity.getApproved() != null) {
	            pstmt.setInt(parameterIndex++, activity.getApproved());
	        }
	        if (activity.getCity() != null && !activity.getCity().isEmpty()) {
	            pstmt.setString(parameterIndex++, activity.getCity());
	        }
	        if (activity.getDistrict() != null && !activity.getDistrict().isEmpty()) {
	            pstmt.setString(parameterIndex++, activity.getDistrict());
	        }
	        if (activity.getInventoryCount() != null) {
	            pstmt.setInt(parameterIndex++, activity.getInventoryCount());
	        }
	        if (activity.getInventoryUpdateTime() != null) {
	            pstmt.setTimestamp(parameterIndex++, activity.getInventoryUpdateTime());
	        }
	        if (activity.getCreatedTime() != null) {
	            pstmt.setTimestamp(parameterIndex++, activity.getCreatedTime());
	        }
	        if (activity.getLatitude() != null && !activity.getLatitude().isEmpty()) {
	            pstmt.setString(parameterIndex++, activity.getLatitude());
	        }
	        if (activity.getLongitude() != null && !activity.getLongitude().isEmpty()) {
	            pstmt.setString(parameterIndex++, activity.getLongitude());
	        }
	        if (activity.getTicketsActivateTime() != null) {
	            pstmt.setTimestamp(parameterIndex++, activity.getTicketsActivateTime());
	        }
	        if (activity.getTicketsExpiredTime() != null) {
	            pstmt.setTimestamp(parameterIndex++, activity.getTicketsExpiredTime());
	        }

	        // 設定 WHERE 條件的參數，使用 offset
	        pstmt.setInt(parameterIndex + 1 + offset, activity.getSupplierId());

	        // 執行更新
	        return pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
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
