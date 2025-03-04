package web.myfavorites.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import web.activity.vo.Activities;
import web.myfavorites.dao.MyFavoritesDao;
import web.myfavorites.vo.MyFavorites;

@Repository
public class MyFavoritesDaoImpl implements MyFavoritesDao {
//	private HikariDataSource ds;
//
//	public MyFavoritesDaoImpl() throws NamingException {
//		ds = new HikariDataSource();
//		ds.setJdbcUrl(URL);
//		ds.setUsername(USER);
//		ds.setPassword(PASSWORD);
//		ds.addDataSourceProperty("cachePrepStmts", true);
//		ds.addDataSourceProperty("preStmtCacheSize", 250);
//		ds.addDataSourceProperty("preStmtCacheSqlLimit", 2048);
//	}
	
	@PersistenceContext
	private Session session;
	
	@Override
	public int insert(MyFavorites myFavorites) {
		session.persist(myFavorites);
		return 1;
	}

//	@Override
//	public int insertToFavorites(MyFavorites myFavorites) {
//		// SQL 語句：根據 activities 表格新增資料
//		final String SQL = "INSERT INTO MY_FAVORITES (ACTIVITY_ID, MEMBER_ID) " + "VALUES (?, ?)";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//
//			pstmt.setInt(1, myFavorites.getActivity_id());
//			pstmt.setInt(2, myFavorites.getMemberId());
//
//			int result = pstmt.executeUpdate();
//			List<MyFavorites> list = new ArrayList<>();
//			list.add(myFavorites);
//			if (result > 0) {
//
//				System.out.println(list);
//
//			}
//			return -1;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 1;
//	}

	@Override
	public List<MyFavorites> selectAll() {
		final String hql = "FROM MyFavorites ORDER BY myFavoriteId";
		return session.createQuery(hql, MyFavorites.class).getResultList();
	}

//	@Override
//	public int selectAllFavorites() {
//		final String SQL = "SELECT mf.my_favorite_id, mf.activity_id, mf.member_id, mf.added_time,"
//	               + " act.activity_prefix, act.activity_name, act.supplier_id, act.city_id, act.district_id, act.address,"
//	               + " act.unit_price, act.min_participants, act.max_participants, act.description, act.precaution, act.category,"
//	               + " act.start_date_time, act.end_date_time, act.status, act.note, act.approved, act.inventory_count,"
//	               + " act.latitude, act.longitude, act.tickets_activate_time, act.tickets_expired_time, act.inventory_update_time, act.create_time"
//	               + " FROM my_favorites AS mf"
//	               + " JOIN activities AS act ON mf.activity_id = act.activity_id";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//			ResultSet rs = pstmt.executeQuery();
//
//			List<MyFavorites> list = new ArrayList<>();
//			while (rs.next()) {
//				MyFavorites myFavorites = new MyFavorites();
//				Activities activity = new Activities();
//				
//				myFavorites.setActivity_id(rs.getInt("activity_id"));
//				myFavorites.setMemberId(rs.getInt("member_id"));
//				myFavorites.setAddedTime(rs.getTimestamp("added_time"));
//				
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
//				
//				myFavorites.setActivity(activity);
//				
//				list.add(myFavorites);
//			}
//			return -1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 1;
//	}

	@Override
	public int delete(Integer myFavoriteId) {
		MyFavorites myFavorites = session.getReference(MyFavorites.class, myFavoriteId);
		session.remove(myFavorites);
		return 1;
	}

	@Override
	public MyFavorites selectByMfId(Integer id) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<MyFavorites> cQuery = cBuilder.createQuery(MyFavorites.class);
		Root<MyFavorites> root = cQuery.from(MyFavorites.class);
		cQuery.where(cBuilder.equal(root.get("myFavoriteId"), id));
		return session.createQuery(cQuery).uniqueResult();
	}

//	@Override
//	public int deleteFavorites(Integer id) {
//		String SQL = "delete from my_favorites where my_favorite_id = ?";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//			pstmt.setInt(1, id);
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}

}
