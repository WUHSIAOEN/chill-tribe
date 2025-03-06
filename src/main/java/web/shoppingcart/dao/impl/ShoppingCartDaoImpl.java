package web.shoppingcart.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import web.activity.vo.Activities;
import web.shoppingcart.dao.ShoppingCartDao;
import web.shoppingcart.vo.ShoppingCart;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
	
	@PersistenceContext
	private Session session;
	
//	private HikariDataSource ds;
//
//	public ShoppingCartDaoImpl() throws NamingException {
//		ds = new HikariDataSource();
//		ds.setJdbcUrl(URL);
//		ds.setUsername(USER);
//		ds.setPassword(PASSWORD);
//		ds.addDataSourceProperty("cachePrepStmts", true);
//		ds.addDataSourceProperty("preStmtCacheSize", 250);
//		ds.addDataSourceProperty("preStmtCacheSqlLimit", 2048);
//	}

	@Override
	public int insert(ShoppingCart shoppingCart) {
		session.persist(shoppingCart);
		return 1;
	}
	
//	@Override
//	public List<ShoppingCart> insertToCart(ShoppingCart shoppingCart) {
		// SQL 語句：根據 activities 表格新增資料
//		final String SQL = "INSERT INTO SHOPPING_CART_ITEMS (ACTIVITY_ID, QUANTITY, TOTAL_PRICE, MEMBER_ID) "
//				+ "VALUES (?, ?, ?, ?)";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//
//			pstmt.setInt(1, shoppingCart.getActivityId());
//			pstmt.setInt(2, shoppingCart.getQuantity());
//			pstmt.setInt(3, shoppingCart.getTotalPrice());
//			pstmt.setInt(4, shoppingCart.getMemberId());
//
//			int result = pstmt.executeUpdate();
//			List<ShoppingCart> list = new ArrayList<>();
//			list.add(shoppingCart);
//			if (result > 0) {
//
//				System.out.println(list);
//
//			}
//			return list;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}
	
	@Override
	public List<ShoppingCart> selectAll() {
		final String hql = "FROM ShoppingCart ORDER BY shoppingCartItemId";
		return session.createQuery(hql, ShoppingCart.class).getResultList();
	}
	
	@Override
	public ShoppingCart selectByScId(Integer id) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<ShoppingCart> cQuery = cBuilder.createQuery(ShoppingCart.class);
		Root<ShoppingCart> root = cQuery.from(ShoppingCart.class);
		cQuery.where(cBuilder.equal(root.get("shoppingCartItemId"), id));
		return session.createQuery(cQuery).uniqueResult();
	}
	
	@Override
	public int delete(Integer shoppingCartItemId) {
		ShoppingCart shoppingCart = session.getReference(ShoppingCart.class, shoppingCartItemId);
		session.remove(shoppingCart);
		return 1;
	}

	@Override
	public int update(ShoppingCart shoppingCart) {
		String hql = "FROM ShoppingCart WHERE memberId = :memberId AND activityId = :activityId";
	    Query<ShoppingCart> query = session.createQuery(hql, ShoppingCart.class);
	    query.setParameter("memberId", shoppingCart.getMemberId());
	    query.setParameter("activityId", shoppingCart.getActivityId());
	    
	    ShoppingCart existingCart = query.uniqueResult();

	    if (existingCart != null) {
	        if (shoppingCart.getQuantity() != null) {
	            existingCart.setQuantity(existingCart.getQuantity() + shoppingCart.getQuantity());
	        }
	        if (shoppingCart.getAddedTime() != null) {
	            existingCart.setAddedTime(shoppingCart.getAddedTime());
	        }

	        session.update(existingCart);
	        return 1;
	    } else {
	        session.save(shoppingCart);
	        return 1;
	    }
	}

//	@Override
//	public List<ShoppingCart> selectAllCart() {
//		final String SQL = "SELECT sci.shopping_cart_item_id, sci.activity_id, sci.quantity, sci.total_price, sci.member_id, sci.added_time,"
//	               + " act.activity_prefix, act.activity_name, act.supplier_id, act.city_id, act.district_id, act.address,"
//	               + " act.unit_price, act.min_participants, act.max_participants, act.description, act.precaution, act.category,"
//	               + " act.start_date_time, act.end_date_time, act.status, act.note, act.approved, act.inventory_count,"
//	               + " act.latitude, act.longitude, act.tickets_activate_time, act.tickets_expired_time, act.inventory_update_time, act.create_time"
//	               + " FROM shopping_cart_items AS sci"
//	               + " JOIN activities AS act ON sci.activity_id = act.activity_id";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//			ResultSet rs = pstmt.executeQuery();
//
//			List<ShoppingCart> list = new ArrayList<>();
//			while (rs.next()) {
//				ShoppingCart cartItem = new ShoppingCart();
//				Activities activity = new Activities();
//				// Shopiing Cart
//				cartItem.setActivityId(rs.getInt("activity_id"));
//				cartItem.setQuantity(rs.getInt("quantity"));
//				cartItem.setTotalPrice(rs.getInt("total_price"));
//				cartItem.setMemberId(rs.getInt("member_id"));
//				cartItem.setAddedTime(rs.getTimestamp("added_time"));
//				// Activity
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
//				cartItem.setActivity(activity);
//				
//				list.add(cartItem);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}


}
