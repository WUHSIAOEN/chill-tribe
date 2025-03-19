// 這裡我的connection 先用曉恩的，沒用講義上的。

package web.activity.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;

import web.activity.dao.ActivityDao;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;
import web.activity.vo.Comment;

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
	public int insertImages(List<ActivityImage> images, int activityId) {
		for (ActivityImage image : images) {
	        image.setActivityId(activityId); // 設置每張圖片的 activityId
	        session.persist(image); // 插入圖片資料
	    }
		return 1;
	}
	
	// 新增留言
	@Override
	public int insertComments(List<Comment> comments, int activityId) {
		for (Comment comment : comments) {
			comment.setActivityId(activityId);
			session.persist(comment);
		}
		return 1;
	}

	// 更新活動
	@Override
	public int update(Activities activities) {
		
		Activities existingActivity = session.get(Activities.class, activities.getActivityId());
	    if (existingActivity != null) {
	        // 只更新傳入的欄位，其他欄位保持原有值
	    	if (activities.getActivityName() != null) {
	            existingActivity.setActivityName(activities.getActivityName());
	        }
	        if (activities.getActivityPrefix() != null) {
	            existingActivity.setActivityPrefix(activities.getActivityPrefix());
	        }
	        if (activities.getSupplierId() != null) {
	            existingActivity.setSupplierId(activities.getSupplierId());
	        }
	        if (activities.getSupplier() != null) {
	            existingActivity.setSupplier(activities.getSupplier());
	        }
	        if (activities.getCityId() != null) {
	            existingActivity.setCityId(activities.getCityId());
	        }
	        if (activities.getCity() != null) {
	            existingActivity.setCity(activities.getCity());
	        }
	        if (activities.getDistrictId() != null) {
	            existingActivity.setDistrictId(activities.getDistrictId());
	        }
	        if (activities.getDistrict() != null) {
	            existingActivity.setDistrict(activities.getDistrict());
	        }
	        if (activities.getAddress() != null) {
	            existingActivity.setAddress(activities.getAddress());
	        }
	        if (activities.getUnitPrice() != null) {
	            existingActivity.setUnitPrice(activities.getUnitPrice());
	        }
	        if (activities.getMinParticipants() != null) {
	            existingActivity.setMinParticipants(activities.getMinParticipants());
	        }
	        if (activities.getMaxParticipants() != null) {
	            existingActivity.setMaxParticipants(activities.getMaxParticipants());
	        }
	        if (activities.getDescription() != null) {
	            existingActivity.setDescription(activities.getDescription());
	        }
	        if (activities.getCategory() != null) {
	            existingActivity.setCategory(activities.getCategory());
	        }
	        if (activities.getComments() != null) {
	            existingActivity.setComments(activities.getComments());
	        }
	        if (activities.getStartDateTime() != null) {
	            existingActivity.setStartDateTime(activities.getStartDateTime());
	        }
	        if (activities.getEndDateTime() != null) {
	            existingActivity.setEndDateTime(activities.getEndDateTime());
	        }
	        if (activities.getStatus() != null) {
	            existingActivity.setStatus(activities.getStatus());
	        }
	        if (activities.getNote() != null) {
	            existingActivity.setNote(activities.getNote());
	        }
	        if (activities.getPrecaution() != null) {
	            existingActivity.setPrecaution(activities.getPrecaution());
	        }
	        if (activities.getApproved() != null) {
	            existingActivity.setApproved(activities.getApproved());
	        }
	        if (activities.getInventoryCount() != null) {
	            existingActivity.setInventoryCount(activities.getInventoryCount());
	        }
	        if (activities.getInventoryUpdateTime() != null) {
	            existingActivity.setInventoryUpdateTime(activities.getInventoryUpdateTime());
	        }
	        if (activities.getCreateTime() != null) {
	            existingActivity.setCreateTime(activities.getCreateTime());
	        }
	        if (activities.getLatitude() != null) {
	            existingActivity.setLatitude(activities.getLatitude());
	        }
	        if (activities.getLongitude() != null) {
	            existingActivity.setLongitude(activities.getLongitude());
	        }
	        if (activities.getTicketsActivateTime() != null) {
	            existingActivity.setTicketsActivateTime(activities.getTicketsActivateTime());
	        }
	        if (activities.getTicketsExpiredTime() != null) {
	            existingActivity.setTicketsExpiredTime(activities.getTicketsExpiredTime());
	        }
	        
//	        // 更新活動圖片（如果有傳入）
//	        if (images != null && !images.isEmpty()) {
//	            // 刪除舊的圖片記錄
//	            for (ActivityImage image : existingActivity.getActivityImages()) {
//	                session.delete(image);
//	            }
//	            // 插入新的圖片記錄
//	            for (ActivityImage newImage : images) {
//	                newImage.setActivityId(activities.getActivityId()); // 確保每個圖片的 activityId 連結到正確的活動
//	                session.save(newImage); // 插入新圖片
//	            }
//	        }
	        session.update(existingActivity);
	        return 1;
	    }
	    return -1;


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
	
	// 更新圖片
	@Override
	public int updateImages(List<ActivityImage> images, int activityId) {
	    // 查詢現有圖片
	    String hql = "FROM ActivityImage WHERE activityId = :activityId";
	    List<ActivityImage> existingImages = session.createQuery(hql, ActivityImage.class)
	                                                 .setParameter("activityId", activityId)
	                                                 .getResultList();

	    // 刪除舊圖片
	    for (ActivityImage image : existingImages) {
	        session.remove(image);
	    }

	    // 插入新圖片
	    for (ActivityImage image : images) {
	    	image.setActivityId(activityId); // 設定 activityId
	        session.persist(image);
	    }

	    return 1;
	}

	// 將活動取消
	@Override
	public int statusCancel(Integer id) {
		final String hql = "UPDATE Activities a SET a.status = 0 WHERE a.activityId = :activityId";
	    Query<?> query = session.createQuery(hql);
	    query.setParameter("activityId", id);
	    return query.executeUpdate();
	    
		//return query.executeUpdate();
//		String SQL = "UPDATE ACTIVITIES SET STATUS = 0 WHERE ACTIVITY_ID = ?";
//
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//			pstmt.setInt(1, activity.getActivityId());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1; // 代表更新失敗
	}

	// 刪除活動
	@Override
	public int deleteActivityById(Integer activityId) {
		Activities activities = session.getReference(Activities.class, activityId);
		session.remove(activities);
		return 1;
		
//		String SQL = "delete from ACTIVITIES where ACTIVITY_ID = ?";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//			pstmt.setInt(1, activityId);
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
	}

	// 全選活動
	@Override
	public List<Activities> selectAll() {
		final String hql = "FROM Activities ORDER BY activity_id";
		return session.createQuery(hql, Activities.class).getResultList();
		
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//			ResultSet rs = pstmt.executeQuery();
//
//			List<Activities> list = new ArrayList<>();
//			while (rs.next()) {
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
////				System.out.println(list);
//				list.add(activity);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	// 只用活動ID搜尋，傳回其他活動內容
	@Override
	public Activities selectByActivityId(Integer id) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Activities> cQuery = cBuilder.createQuery(Activities.class);
		Root<Activities> root = cQuery.from(Activities.class);
		cQuery.where(cBuilder.equal(root.get("activityId"), id));
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
