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
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaxxer.hikari.HikariDataSource;

import core.vo.City;
import core.vo.District;
import web.activity.dao.ActivitySearchDao;
import web.activity.vo.Activities;
import web.activity.vo.Activity2;
import web.activity.vo.ActivityImage;
import web.supplier.vo.Supplier;


//@Repository
public class ActivitySearchDaoImpl implements ActivitySearchDao {
	
	private DataSource ds;

	public ActivitySearchDaoImpl() throws NamingException {

		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
		if (ds != null) {
			System.out.println("DataSource found!");
		} else {
			System.out.println("DataSource not found.");
		}

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
	public List<Activities> selectActivityOrderByStart() {
		String sql = "SELECT activity_id, activity_name, act.supplier_id, supplier_name, act.city_id, city_name, act.district_id, district_name, act.address, unit_price, min_participants, max_participants, category, start_date_time, end_date_time, status, approved, inventory_count, create_time"
				+ " FROM activities AS act" + " JOIN suppliers AS spl" + " ON act.supplier_id = spl.supplier_id"
				+ " JOIN cities AS ct" + " ON act.city_id = ct.city_id" + " JOIN districts AS dstx"
				+ " ON act.district_id = dstx.district_id" + " WHERE approved = 1 AND status = 1"
				+ " ORDER BY start_date_time" + " LIMIT 6;";

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {

			List<Activities> list = new ArrayList<>();
			while (rs.next()) {
				Activities activity = new Activities();
				Supplier supplier = new Supplier();
				City city = new City();
				District district = new District();
				activity.setActivityId(rs.getInt("activity_id"));
				activity.setActivityName(rs.getString("activity_name"));
				supplier.setSupplier_id(rs.getInt("supplier_id"));
				supplier.setSupplier_name(rs.getString("supplier_name"));
				activity.setSupplier(supplier);
				city.setCityId(rs.getInt("city_id"));
				city.setCityName(rs.getString("city_name"));
				activity.setCity(city);
				district.setDistrictId(rs.getInt("district_id"));
				district.setDistricName(rs.getString("district_name"));
				activity.setDistrict(district);
				activity.setAddress(rs.getString("address"));
				activity.setUnitPrice(rs.getInt("unit_price"));
				activity.setMinParticipants(rs.getInt("min_participants"));
				activity.setMaxParticipants(rs.getInt("max_participants"));
				activity.setCategory(rs.getString("category"));
				activity.setStartDateTime(rs.getTimestamp("start_date_time"));
				activity.setEndDateTime(rs.getTimestamp("end_date_time"));
				activity.setStatus(rs.getInt("status"));
				activity.setApproved(rs.getInt("approved"));
				activity.setInventoryCount(rs.getInt("inventory_count"));
				activity.setCreateTime(rs.getTimestamp("create_time"));
				list.add(activity);
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
	public List<Activities> selectByNameCatgoryRegion(String actname, String catgory, String region) {
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
			List<Activities> list = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Activities activity = new Activities();
				Supplier supplier = new Supplier();
				City city = new City();
				District district = new District();
				activity.setActivityId(rs.getInt("activity_id"));
				activity.setActivityName(rs.getString("activity_name"));
				supplier.setSupplier_id(rs.getInt("supplier_id"));
				supplier.setSupplier_name(rs.getString("supplier_name"));
				activity.setSupplier(supplier);
				city.setCityId(rs.getInt("city_id"));
				city.setCityName(rs.getString("city_name"));
				activity.setCity(city);
				district.setDistrictId(rs.getInt("district_id"));
				district.setDistricName(rs.getString("district_name"));
				activity.setDistrict(district);
				activity.setAddress(rs.getString("address"));
				activity.setUnitPrice(rs.getInt("unit_price"));
				activity.setMinParticipants(rs.getInt("min_participants"));
				activity.setMaxParticipants(rs.getInt("max_participants"));
				activity.setCategory(rs.getString("category"));
				activity.setStartDateTime(rs.getTimestamp("start_date_time"));
				activity.setEndDateTime(rs.getTimestamp("end_date_time"));
				activity.setStatus(rs.getInt("status"));
				activity.setApproved(rs.getInt("approved"));
				activity.setInventoryCount(rs.getInt("inventory_count"));
				activity.setCreateTime(rs.getTimestamp("create_time"));
				list.add(activity);
			}
//			System.out.println(list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Activities> selectByNameCatgoryCity(String actname, String catgory, String cityName) {
		int offset = 0; // 用偏移量
		StringBuilder sql = new StringBuilder("SELECT * FROM activities AS act"
				+ " JOIN suppliers AS spl" + " ON act.supplier_id = spl.supplier_id"
				+ " JOIN cities AS ct ON act.city_id = ct.city_id"
				+ " JOIN districts AS dstx ON act.district_id = dstx.district_id"
				+ " WHERE 1=1"); 
		// 判斷是否每個參數都有值
		if (cityName != "") {
			sql.append(" AND ct.city_name = ?");
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
			if (cityName != "") {
				pstmt.setString(1, cityName);
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
			List<Activities> list = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Activities activity = new Activities();
				Supplier supplier = new Supplier();
				City city = new City();
				District district = new District();
				activity.setActivityId(rs.getInt("activity_id"));
				activity.setActivityName(rs.getString("activity_name"));
				supplier.setSupplier_id(rs.getInt("supplier_id"));
				supplier.setSupplier_name(rs.getString("supplier_name"));
				activity.setSupplier(supplier);
				city.setCityId(rs.getInt("city_id"));
				city.setCityName(rs.getString("city_name"));
				activity.setCity(city);
				district.setDistrictId(rs.getInt("district_id"));
				district.setDistricName(rs.getString("district_name"));
				activity.setDistrict(district);
				activity.setAddress(rs.getString("address"));
				activity.setUnitPrice(rs.getInt("unit_price"));
				activity.setMinParticipants(rs.getInt("min_participants"));
				activity.setMaxParticipants(rs.getInt("max_participants"));
				activity.setCategory(rs.getString("category"));
				activity.setStartDateTime(rs.getTimestamp("start_date_time"));
				activity.setEndDateTime(rs.getTimestamp("end_date_time"));
				activity.setStatus(rs.getInt("status"));
				activity.setApproved(rs.getInt("approved"));
				activity.setInventoryCount(rs.getInt("inventory_count"));
				activity.setCreateTime(rs.getTimestamp("create_time"));
				list.add(activity);
			}
//			System.out.println(list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
