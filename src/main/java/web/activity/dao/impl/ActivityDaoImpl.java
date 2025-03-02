// 這裡我的connection 先用曉恩的，沒用講義上的。

package web.activity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;

import core.vo.City;
import core.vo.District;
import web.activity.dao.ActivityDao;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;
import web.activity.vo.Comment;
import web.supplier.vo.Supplier;

@Repository
public class ActivityDaoImpl implements ActivityDao {

//	================ 確定該DAO 都能從Spring 注入的Sesson 取的資料庫連線則不須以下屬性及建構子 ================
//	private DataSource ds;
	private HikariDataSource ds;

//	public ActivityDaoImpl() throws NamingException {
//		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
//		if (ds != null) {
//			System.out.println("DataSource found!");
//		} else {
//			System.out.println("DataSource not found.");
//		}
//		ds = new HikariDataSource();
//		ds.setJdbcUrl(URL);
//		ds.setUsername(USER);
//		ds.setPassword(PASSWORD);
//		ds.addDataSourceProperty("cachePrepStmts", true);
//		ds.addDataSourceProperty("preStmtCacheSize", 250);
//		ds.addDataSourceProperty("preStmtCacheSqlLimit", 2048);
//	}
//	================ 確定該DAO 都能從Spring 注入的Sesson 取的資料庫連線則不須以上屬性及建構子 ================

//	================ Spring 注入DataSource 連線 ================

	@PersistenceContext
	private Session session;

//	新增一筆活動資料Hibernate 寫法
	@Override
	public int insert(Activities activities) {
		session.persist(activities);
		return 1;
	}

//	新增活動 - 原生寫法，確定不用了則可刪掉
//	@Override
//	public int insert(Activities activity) {
//		// SQL 語句：根據 activities 表格新增資料
//		final String SQL = "INSERT INTO ACTIVITIES (SUPPLIER_ID, ACTIVITY_NAME, CITY_ID, DISTRICT_ID, ADDRESS, "
//				+ "START_DATE_TIME, END_DATE_TIME, UNIT_PRICE, MIN_PARTICIPANTS, MAX_PARTICIPANTS, "
//				+ "INVENTORY_COUNT, DESCRIPTION, CATEGORY, PRECAUTION) "
//				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//			pstmt.setInt(1, activity.getSupplierId());
//			pstmt.setString(2, activity.getActivityName());
//			pstmt.setInt(3, activity.getCity_id());
//			pstmt.setInt(4, activity.getDistrict_id());
//			pstmt.setString(5, activity.getAddress());
//			pstmt.setTimestamp(6, activity.getStartDateTime());
//			pstmt.setTimestamp(7, activity.getEndDateTime());
//			pstmt.setInt(8, activity.getUnitPrice());
//			pstmt.setInt(9, activity.getMinParticipants());
//			pstmt.setInt(10, activity.getMaxParticipants());
//			pstmt.setInt(11, activity.getInventoryCount());
//			pstmt.setString(12, activity.getDescription());
//			pstmt.setString(13, activity.getCategory());
//			pstmt.setString(14, activity.getPrecaution());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}

	// 新增多張活動圖片
	@Override
	public int insertActivityImage(ActivityImage activityImage) {
		final String SQL = "INSERT INTO activity_images (activity_id, image_base64) VALUES (?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, activityImage.getActivityId());
			pstmt.setString(2, activityImage.getImageBase64());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 更新活動
	@Override
	public int update(Activities activities) {

		final StringBuilder sql = new StringBuilder()
				.append("UPDATE Activities SET ");
		
		String activityName = activities.getActivityName();
		Integer cityId = activities.getCityId();
		City city = activities.getCity();
		Integer districtId = activities.getDistrictId();
		District district = activities.getDistrict();
		String address = activities.getAddress();
		Integer unitPrice = activities.getUnitPrice();
		Integer minParticipants = activities.getMinParticipants();
		Integer maxParticipants = activities.getMaxParticipants();
		String description = activities.getDescription();
		String category = activities.getCategory();
		List<ActivityImage> activityImages = activities.getActivityImages();
		List<Comment> comments = activities.getComments();
		Timestamp startDateTime = activities.getStartDateTime();
		Timestamp endDateTime = activities.getEndDateTime();
		Integer status = activities.getStatus();
		String note = activities.getNote();
		String precaution = activities.getPrecaution();
		Integer approved = activities.getApproved();
		Integer inventoryCount = activities.getInventoryCount();
		Timestamp inventoryUpdateTime = activities.getInventoryUpdateTime();
		Timestamp createTime = activities.getCreateTime();
		String latitude = activities.getLatitude();
		String longitude = activities.getLongitude();
		Timestamp ticketsActivateTime = activities.getTicketsActivateTime();
		Timestamp ticketsExpiredTime = activities.getTicketsExpiredTime();
//		Integer supplierId = activities.getSupplierId();
//		Supplier supplier = activities.getSupplier();
		
		if (activityName != null && !activityName.isEmpty()) {
			sql.append("activity_name = :activity_name, ");
		}
		if (cityId != null) {
			sql.append("city_id = :city_id, ");
		}
		if (city != null) {
			sql.append("city = :city, ");
		}
		if (districtId != null) {
			sql.append("district_id = :district_id, ");
		}
		if (district != null) {
			sql.append("district = :district, ");
		}
		if (address != null && !address.isEmpty()) {
			sql.append("address = :address, ");
		}
		if (unitPrice != null) {
			sql.append("unit_price = :unit_price, ");
		}
		if (minParticipants != null) {
			sql.append("min_participants = :min_participants, ");
		}
		if (maxParticipants != null) {
			sql.append("max_participants = :max_participants, ");
		}
		if (description != null && !description.isEmpty()) {
			sql.append("description = :description, ");
		}
		if (category != null && !category.isEmpty()) {
			sql.append("category = :category, ");
		}
		if (activityImages != null && !activityImages.isEmpty()) {
			sql.append("activity_images = :activity_images, ");
		}
		if (comments != null && !comments.isEmpty()) {
			sql.append("comments = :comments, ");
		}
		if (startDateTime != null) {
			sql.append("start_date_time = :start_date_time, ");
		}
		if (endDateTime != null) {
			sql.append("end_date_time = :end_date_time, ");
		}
		if (status != null) {
			sql.append("status = :status, ");
		}
		if (note != null && !note.isEmpty()) {
			sql.append("note = :note, ");
		}
		if (precaution != null && !precaution.isEmpty()) {
			sql.append("precaution = :precaution, ");
		}
		if (approved != null) {
			sql.append("approved = :approved, ");
		}
		if (inventoryCount != null) {
			sql.append("inventory_count = :inventory_count, ");
		}
		if (inventoryUpdateTime != null) {
			sql.append("inventory_update_time = :inventory_update_time, ");
		}
		if (createTime != null) {
			sql.append("create_time = :create_time, ");
		}
		if (latitude != null && !latitude.isEmpty()) {
			sql.append("latitude = :latitude, ");
		}
		if (longitude != null && !longitude.isEmpty()) {
			sql.append("longitude = :longitude, ");
		}
		if (ticketsActivateTime != null) {
			sql.append("tickets_activate_time = :tickets_activate_time, ");
		}
		if (ticketsExpiredTime != null) {
			sql.append("tickets_expired_time = :tickets_expired_time, ");
		}
		
		if (sql.charAt(sql.length() - 2) == ',') {
			sql.delete(sql.length() - 2, sql.length());
		}
//		if (supplierId != null) {
//			sql.append("supplier_id = :supplier_id, ");
//		}
//		if (supplier != null) {
//			sql.append("supplier = :supplier, ");
//		}
		
		sql.append("WHERE activity_id = :activity_id");
		
		Query<?> query = session.createQuery(sql.toString());
		
		if (activityName != null && !activityName.isEmpty()) {
			query.setParameter("activityName", activities.getActivityName());
		}
		if (cityId != null) {
			query.setParameter("activityName", activities.getCityId());
		}
		if (city != null) {
			query.setParameter("activityName", activities.getCity());
		}
		if (districtId != null) {
			query.setParameter("activityName", activities.getDistrictId());
		}
		if (district != null) {
			query.setParameter("activityName", activities.getDistrict());
		}
		if (address != null && !address.isEmpty()) {
			query.setParameter("activityName", activities.getAddress());
		}
		if (unitPrice != null) {
			query.setParameter("activityName", activities.getUnitPrice());
		}
		if (minParticipants != null) {
			query.setParameter("activityName", activities.getMinParticipants());
		}
		if (maxParticipants != null) {
			query.setParameter("activityName", activities.getMaxParticipants());
		}
		if (description != null && !description.isEmpty()) {
			query.setParameter("activityName", activities.getDescription());
		}
		if (category != null && !category.isEmpty()) {
			query.setParameter("activityName", activities.getCategory());
		}
		if (activityImages != null && !activityImages.isEmpty()) {
			query.setParameter("activityName", activities.getActivityImages());
		}
		if (comments != null && !comments.isEmpty()) {
			query.setParameter("activityName", activities.getComments());
		}
		if (startDateTime != null) {
			query.setParameter("activityName", activities.getStartDateTime());
		}
		if (endDateTime != null) {
			query.setParameter("activityName", activities.getEndDateTime());
		}
		if (status != null) {
			query.setParameter("activityName", activities.getStatus());
		}
		if (note != null && !note.isEmpty()) {
			query.setParameter("activityName", activities.getNote());
		}
		if (precaution != null && !precaution.isEmpty()) {
			query.setParameter("activityName", activities.getPrecaution());
		}
		if (approved != null) {
			query.setParameter("activityName", activities.getApproved());
		}
		if (inventoryCount != null) {
			query.setParameter("activityName", activities.getInventoryCount());
		}
		if (inventoryUpdateTime != null) {
			query.setParameter("activityName", activities.getInventoryUpdateTime());
		}
		if (createTime != null) {
			query.setParameter("activityName", activities.getCreateTime());
		}
		if (latitude != null && !latitude.isEmpty()) {
			query.setParameter("activityName", activities.getLatitude());
		}
		if (longitude != null && !longitude.isEmpty()) {
			query.setParameter("activityName", activities.getLongitude());
		}
		if (ticketsActivateTime != null) {
			query.setParameter("activityName", activities.getTicketsActivateTime());
		}
		if (ticketsExpiredTime != null) {
			query.setParameter("activityName", activities.getTicketsExpiredTime());
		}
//		if (supplierId != null) {
//			query.setParameter("activityName", activities.getSupplierId());
//		}
//		if (supplier != null) {
//			query.setParameter("activityName", activities.getSupplier());
//		}
		
		query.setParameter("activity_id", activities.getActivityId());
		
		return query.executeUpdate();

//		if (activity.getActivityName() != null && !activity.getActivityName().isEmpty()) {
//			sql.append("ACTIVITY_NAME = ?, ");
//		}
//		if (activity.getSupplierId() != null) {
//			sql.append("SUPPLIER_ID = ?, ");
//		}
//		if (activity.getAddress() != null && !activity.getAddress().isEmpty()) {
//			sql.append("ADDRESS = ?, ");
//		}
//		if (activity.getUnitPrice() != null) {
//			sql.append("UNIT_PRICE = ?, ");
//		}
//		if (activity.getMinParticipants() != null) {
//			sql.append("MIN_PARTICIPANTS = ?, ");
//		}
//		if (activity.getMaxParticipants() != null) {
//			sql.append("MAX_PARTICIPANTS = ?, ");
//		}
//		if (activity.getDescription() != null && !activity.getDescription().isEmpty()) {
//			sql.append("DESCRIPTION = ?, ");
//		}
//		if (activity.getPrecaution() != null && !activity.getPrecaution().isEmpty()) {
//			sql.append("PRECAUTION = ?, ");
//		}
//		if (activity.getCategory() != null && !activity.getCategory().isEmpty()) {
//			sql.append("CATEGORY = ?, ");
//		}
//		if (activity.getStartDateTime() != null) {
//			sql.append("START_DATE_TIME = ?, ");
//		}
//		if (activity.getEndDateTime() != null) {
//			sql.append("END_DATE_TIME = ?, ");
//		}
//		if (activity.getCityId() != null) {
//			sql.append("CITY_ID = ?, ");
//		}
//		if (activity.getDistrictId() != null) {
//			sql.append("DISTRICT_ID = ?, ");
//		}
//		if (activity.getInventoryCount() != null) {
//			sql.append("INVENTORY_COUNT = ?, ");
//		}
//		if (activity.getNote() != null && !activity.getNote().isEmpty()) {
//			sql.append("NOTE = ?, ");
//		}
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

//		String sqlQuery = sql.toString();
//		if (sqlQuery.endsWith(", ")) {
//			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
//		}
//
//		// 確保在更新語句中有要更新的欄位
//		if (sqlQuery.equals("UPDATE ACTIVITIES SET")) {
//			return 0; // 如果沒有任何欄位需要更新，則返回 0
//		}
//
//		// 加上 WHERE 條件來指定更新的會員 (假設使用 ACTIVITY_ID 或其他識別欄位)
//		sqlQuery += " WHERE ACTIVITY_ID = ?"; // 假設 ACTIVITY_ID 是更新條件
//
//		int parameterIndex = 1; // 偏移量
//
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sqlQuery);) {
//
//			if (activity.getActivityName() != null && !activity.getActivityName().isEmpty()) {
//				pstmt.setString(parameterIndex, activity.getActivityName());
//				parameterIndex++;
//			}
//			if (activity.getSupplierId() != null) {
//				pstmt.setInt(parameterIndex, activity.getSupplierId());
//				parameterIndex++;
//			}
//			if (activity.getAddress() != null && !activity.getAddress().isEmpty()) {
//				pstmt.setString(parameterIndex, activity.getAddress());
//				parameterIndex++;
//			}
//			if (activity.getUnitPrice() != null) {
//				pstmt.setInt(parameterIndex, activity.getUnitPrice());
//				parameterIndex++;
//			}
//			if (activity.getMinParticipants() != null) {
//				pstmt.setInt(parameterIndex, activity.getMinParticipants());
//				parameterIndex++;
//			}
//			if (activity.getMaxParticipants() != null) {
//				pstmt.setInt(parameterIndex, activity.getMaxParticipants());
//				parameterIndex++;
//			}
//			if (activity.getDescription() != null && !activity.getDescription().isEmpty()) {
//				pstmt.setString(parameterIndex, activity.getDescription());
//				parameterIndex++;
//			}
//			if (activity.getPrecaution() != null && !activity.getPrecaution().isEmpty()) {
//				pstmt.setString(parameterIndex, activity.getPrecaution());
//				parameterIndex++;
//			}
//			if (activity.getCategory() != null && !activity.getCategory().isEmpty()) {
//				pstmt.setString(parameterIndex, activity.getCategory());
//				parameterIndex++;
//			}
//			if (activity.getStartDateTime() != null) {
//				pstmt.setTimestamp(parameterIndex, activity.getStartDateTime());
//				parameterIndex++;
//			}
//			if (activity.getEndDateTime() != null) {
//				pstmt.setTimestamp(parameterIndex, activity.getEndDateTime());
//				parameterIndex++;
//			}
//			if (activity.getNote() != null && !activity.getNote().isEmpty()) {
//				pstmt.setString(parameterIndex, activity.getNote());
//				parameterIndex++;
//			}
//			if (activity.getApproved() != null) {
//				pstmt.setInt(parameterIndex, activity.getApproved());
//				parameterIndex++;
//			}
//			if (activity.getCityId() != null) {
//				pstmt.setInt(parameterIndex, activity.getCityId());
//				parameterIndex++;
//			}
//			if (activity.getInventoryCount() != null) {
//				pstmt.setInt(parameterIndex, activity.getInventoryCount());
//				parameterIndex++;
//			}
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

//			pstmt.setInt(parameterIndex, activity.getActivityId());
//			parameterIndex++;
//
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		return -1;
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
				activity.setCityId(rs.getInt("city_id"));
				activity.setDistrictId(rs.getInt("district_id"));
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
	public Activities selectByActivityId(Integer id) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Activities> cQuery = cBuilder.createQuery(Activities.class);
		Root<Activities> root = cQuery.from(Activities.class);
		cQuery.where(cBuilder.equal(root.get("activity_id"), id));
		return session.createQuery(cQuery).uniqueResult();
		
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL);) {
//			pstmt.setInt(1, activityId);
//			ResultSet rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				Activities activity = new Activities();
//				activity.setActivityId(rs.getInt("activity_id"));
//				activity.setActivityPrefix(rs.getString("activity_prefix"));
//				activity.setActivityName(rs.getString("activity_name"));
//				activity.setSupplierId(rs.getInt("supplier_id"));
//				activity.setAddress(rs.getString("address"));
//				activity.setUnitPrice(rs.getInt("unit_price"));
//				activity.setMinParticipants(rs.getInt("min_participants"));
//				activity.setMaxParticipants(rs.getInt("max_participants"));
//				activity.setDescription(rs.getString("description"));
//				activity.setCategory(rs.getString("category"));
//				activity.setPrecaution(rs.getString("precaution"));
//				activity.setStartDateTime(rs.getTimestamp("start_date_time"));
//				activity.setEndDateTime(rs.getTimestamp("end_date_time"));
//				activity.setStatus(rs.getInt("status"));
//				activity.setNote(rs.getString("note"));
//				activity.setApproved(rs.getInt("approved"));
//				activity.setCityId(rs.getInt("city_id"));
//				activity.setDistrictId(rs.getInt("district_id"));
//				activity.setInventoryCount(rs.getInt("inventory_count"));
//				activity.setInventoryUpdateTime(rs.getTimestamp("inventory_update_time"));
//				activity.setCreateTime(rs.getTimestamp("create_time"));
//				activity.setLatitude(rs.getString("latitude"));
//				activity.setLongitude(rs.getString("longitude"));
//				activity.setTicketsActivateTime(rs.getTimestamp("tickets_activate_time"));
//				activity.setTicketsExpiredTime(rs.getTimestamp("tickets_expired_time"));
//				return activity;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
	}

}
