package web.member.dao.impl;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import core.vo.City;
import core.vo.District;
import web.activity.vo.Activities;
import web.member.dao.MemberDao;
import web.member.vo.Addresses;
import web.member.vo.Member;
import web.shoppingcart.vo.ShoppingCart;

@Repository
public class MemberDaoImpl implements MemberDao {
	private DataSource ds;

	public MemberDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
	}

	@PersistenceContext
	private Session session;

	// 註冊會員資料
	@Override
	public int insert(Member member) {
		try {
			session.persist(member);
			System.out.println("MemberDaoImpl：新增一筆會員資料成功");
			return -1;
		} catch (Exception e) {
			System.out.println("MemberDaoImpl：新增一筆會員資料失敗");
			return 1;
		}
	}

	// 使用member_name查詢相關資料
	@Override
	public Member selectByUsername(String membername) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> cQuery = cBuilder.createQuery(Member.class);
		Root<Member> root = cQuery.from(Member.class);
		cQuery.where(cBuilder.equal(root.get("membername"), membername));
		return session.createQuery(cQuery).uniqueResult();
	}

	// 更新資料
	@Override
	public int update(Member member) {
		final StringBuilder hql = new StringBuilder()
				.append("UPDATE Member SET ");
		
		hql.append("membername = :membername,")
		.append("phone = :phone,")
		.append("gender = :gender,")
		.append("dateofbirth = :dateofbirth ")
		.append("WHERE memberid = :memberid");
		
		Query<?> query = session.createQuery(hql.toString());
		return query
			.setParameter("membername", member.getMembername())
			.setParameter("phone", member.getPhone())
			.setParameter("gender", member.getGender())
			.setParameter("dateofbirth", member.getDateofbirth())
			.setParameter("memberid", member.getMemberid())
			.executeUpdate();
	}

	// 查詢帳號 密碼
	@Override
	public Member selectByUsernameAndPassword(String email, String password) {
		String sql = "select * from MEMBERS where EMAIL = :email and PASSWORD = :password";
		return session.createNativeQuery(sql, Member.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.uniqueResult();
	}

	@Override
	public List<Member> selectAll() {
		final String sql = "FROM Member ORDER BY memberid";
		return session.createQuery(sql, Member.class).getResultList();
	}

	// 刪除使用者
	@Override
	public Integer deletById(Integer member_id) {
		Member member = session.load(Member.class, member_id);
		session.remove(member);
		System.out.println("刪除了一筆會員");
		return -1;
	}

	// 查詢Email
	@Override
	public Member selectByEmail(String email) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> cQuery = cBuilder.createQuery(Member.class);
		Root<Member> root = cQuery.from(Member.class);
		cQuery.where(cBuilder.equal(root.get("email"), email));
		return session.createQuery(cQuery).uniqueResult();
	}

	// 查詢手機
	@Override
	public Member selectByPhone(String phone) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> cQuery = cBuilder.createQuery(Member.class);
		Root<Member> root = cQuery.from(Member.class);
		cQuery.where(cBuilder.equal(root.get("phone"), phone));
		return session.createQuery(cQuery).uniqueResult();
	}

	// 大頭照
	@Override
	public int updateimg(Member member) {
		
		final StringBuilder hql = new StringBuilder()
				.append("UPDATE Member SET ");
		
		String photo_base64 = member.getPhotobase64();
		
		hql.append("photobase64 = :photobase64 ")
		.append("WHERE memberid = :memberid");
		
		Query<?> query = session.createQuery(hql.toString());
		return query
			.setParameter("photobase64", member.getPhotobase64())
			.setParameter("memberid", member.getMemberid())
			.executeUpdate();
	}

	// 查詢大頭照
	@Override
	public Member selectimg(String photo_base64) {
		return session.get(Member.class, photo_base64);
	}

	@Override
	public Member selectByMemberID(Integer memberid) {
		final String hql = "FROM Member WHERE memberid = :memberId"; 
		return session
				.createQuery(hql, Member.class)
				.setParameter("memberId", memberid) 
				.uniqueResult(); 
	}

	@Override
	public int forgetpassword(String email, String newPassword) {
		final String hql = "UPDATE Member SET PASSWORD = :password WHERE email = :email";
		Query<?> query = session.createQuery(hql.toString());
		return query
	    	.setParameter("password", newPassword)
			.setParameter("email", email)
			.executeUpdate();
	}
	
	

	@Override
	public int updatepassword(Member member) {
		final StringBuilder hql = new StringBuilder()
				.append("UPDATE Member SET ");
		
		hql.append("password = :password ")
		.append("WHERE memberid = :memberid");
		
		Query<?> query = session.createQuery(hql.toString());
		return query
			.setParameter("password", member.getPassword())
			.setParameter("memberid", member.getMemberid())
			.executeUpdate();
	}

	// 地址查詢
		@Override
		public Addresses selectaddress(Integer member_id) {
//			CriteriaBuilder cBuilder = session.getCriteriaBuilder();
//			CriteriaQuery<Addresses> cQuery = cBuilder.createQuery(Addresses.class);
//			Root<Addresses> root = cQuery.from(Addresses.class);
//			cQuery.where(cBuilder.equal(root.get("memberid"), member_id));
//			return session.createQuery(cQuery).uniqueResult();
			
//			SELECT * FROM ADDRESSES JOIN CITIES ON ADDRESSES.CITY_ID = CITIES.CITY_ID JOIN DISTRICTS ON ADDRESSES.DISTRICT_ID = DISTRICTS.DISTRICT_ID WHERE MEMBER_ID = ?
//			String hql = "SELECT a FROM Addresses a "
//		               + "JOIN a.city c "
//		               + "JOIN a.district d "
//		               + "WHERE a.memberId = :memberId";
//
//			 Query<Member> query = session.createQuery(hql, Member.class);
//		    query.setParameter("memberId", memberid);
//
//		    Member member = query.uniqueResult();
//		    
//		    return member;
			
//			--------------------------------下面是傳統寫法
			String sql = "SELECT * FROM ADDRESSES JOIN CITIES ON ADDRESSES.CITY_ID = CITIES.CITY_ID JOIN DISTRICTS ON ADDRESSES.DISTRICT_ID = DISTRICTS.ZIP_CODE WHERE MEMBER_ID = ?";
			try (Connection conn = ds.getConnection(); 
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, member_id);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Addresses member = new Addresses();
//						member.setMemberid(rs.getInt("MEMBER_ID"));
						member.setZip_code(rs.getInt("ZIP_CODE"));
						member.setCity_id(rs.getInt("CITY_ID"));
						member.setCity_name(rs.getString("CITY_NAME"));
						member.setDistrict_id(rs.getInt("DISTRICT_ID"));
						member.setDistrict_name(rs.getString("DISTRICT_NAME"));
						member.setAddress_id(rs.getInt("ADDRESS_ID"));
						member.setAddress(rs.getString("ADDRESS"));
						member.setTag(rs.getString("TAG"));
						member.setAddress_default(rs.getInt("ADDRESS_DEFAULT"));
						return member;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public Member selectaddressAll(Integer member_id) {
			String sql = "SELECT * FROM ADDRESSES JOIN CITIES ON ADDRESSES.CITY_ID = CITIES.CITY_ID JOIN DISTRICTS ON ADDRESSES.DISTRICT_ID = DISTRICTS.DISTRICT_ID WHERE MEMBER_ID = ?";
			try (Connection conn = ds.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setInt(1, member_id);
					ResultSet rs = pstmt.executeQuery(); {
				List<Member> list = new ArrayList<>();

				Member member = new Member();
				while (rs.next()) {
					member.setMemberid(rs.getInt("MEMBER_ID"));
					member.setZip_code(rs.getInt("ZIP_CODE"));
					member.setCity_id(rs.getInt("CITY_ID"));
					member.setCity_name(rs.getString("CITY_NAME"));
					member.setDistrict_id(rs.getInt("DISTRICT_ID"));
					member.setDistrict_name(rs.getString("DISTRICT_NAME"));
					member.setAddress_id(rs.getInt("ADDRESS_ID"));
					member.setAddress(rs.getString("ADDRESS"));
					member.setTag(rs.getString("TAG"));
					member.setAddress_default(rs.getInt("ADDRESS_DEFAULT"));
					list.add(member);
				}
				System.out.println(list);
				System.out.println(list.size());
				return member;
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public int upaddress(Addresses addresses) {
			try {
				session.persist(addresses);
				System.out.println("MemberDaoImpl：新增一筆會員地址成功");
				return 1;
			} catch (Exception e) {
				System.out.println("MemberDaoImpl：新增一筆會員地址失敗");
				return -1;
			}
			
			
//			--------------------------------下面是傳統寫法
//			String sql = "insert into ADDRESSES(MEMBER_ID, CITY_ID, DISTRICT_ID, ADDRESS, TAG) values(?, ?, ?, ?, ?)";
//			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setInt(1, member.getMemberid());
//				pstmt.setInt(2, member.getCity_id());
//				pstmt.setInt(3, member.getDistrict_id());
//				pstmt.setString(4, member.getAddress());
////				pstmt.setInt(5, member.getAddress_default());
//				pstmt.setString(5, member.getTag());
//				return pstmt.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return -1;
		}

		@Override
		public int updateaddress(Member member) {
			final StringBuilder hql = new StringBuilder()
					.append("UPDATE Addresses SET ");
			
			hql.append("city_id = :city_id,")
			.append("district_id = :district_id,")
			.append("tag = :tag,")
			.append("address = :address ")
			.append("WHERE memberid = :memberid");
			
			Query<?> query = session.createQuery(hql.toString());
			return query
				.setParameter("city_id", member.getCity_id())
				.setParameter("district_id", member.getDistrict_id())
				.setParameter("tag", member.getTag())
				.setParameter("address", member.getAddress())
				.setParameter("memberid", member.getMemberid())
				.executeUpdate();
		}

//		@Override
//		public int updateaddress(Member member) {
//			int offset = 1;  
//		    StringBuilder sql = new StringBuilder("UPDATE ADDRESSES SET ");
//		    Integer city_id = member.getCity_id();
//		    Integer district_id = member.getDistrict_id();
//		    String address = member.getAddress();
//		    String tag = member.getTag();
//		    
//		    boolean hasCity_id = city_id != null;
//		    boolean hasDistrict_id = district_id != null;
//		    boolean hasAddress = address != null && !address.isEmpty();
//		    boolean hasTag = tag != null && !tag.isEmpty();
//
//		    if (hasCity_id) {
//		        sql.append("CITY_ID = ?, ");
//		    }
//		    if (hasDistrict_id) {
//		        sql.append("DISTRICT_ID = ?, ");
//		    }
//		    if (hasAddress) {
//		        sql.append("ADDRESS = ?, ");
//		    }
//		    if (hasTag) {
//		        sql.append("TAG = ?, ");
//		    }
//		    
//		    if (sql.charAt(sql.length() - 2) == ',') {
//		        sql.delete(sql.length() - 2, sql.length());
//		    }
//
//		    // to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
//		    sql.append("WHERE MEMBER_ID = ?");
//
//		    try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
//		        if (hasCity_id) {
//		            pstmt.setInt(offset++, member.getCity_id());
//		        }
//		        if (hasDistrict_id) {
//		        	pstmt.setInt(offset++, member.getDistrict_id());
//		        }
//		        if (hasAddress) {
//		            pstmt.setString(offset++, member.getAddress());
//		        }
//		        if (hasTag) {
//		            pstmt.setString(offset++, member.getTag());
//		        }
//
//		        // to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
//		        pstmt.setInt(offset, member.getMember_id());
//		        
//		        return pstmt.executeUpdate();
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }
//		    return -1;
//		}

//		@Override
//		public List<Member> selectcityAll() {
//			String sql = "SELECT * FROM DISTRICTS JOIN CITIES ON DISTRICTS.CITY_ID = CITIES.CITY_ID";
//			try (Connection conn = ds.getConnection();
//					PreparedStatement pstmt = conn.prepareStatement(sql)) {
//					ResultSet rs = pstmt.executeQuery(); {
//				List<Member> list = new ArrayList<>();
//
//				while (rs.next()) {
//					Member member = new Member();
//					member.setZip_code(rs.getInt("ZIP_CODE"));
//					member.setCity_id(rs.getInt("CITY_ID"));
//					member.setCity_name(rs.getString("CITY_NAME"));
//					member.setDistrict_id(rs.getInt("DISTRICT_ID"));
//					member.setDistrict_name(rs.getString("DISTRICT_NAME"));
//					list.add(member);
//				}
//				System.out.println(list);
//				System.out.println(list.size());
//				return list;
//					}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
}