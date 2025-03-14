package web.member.dao.impl;

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
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import web.member.dao.MemberDao;
import web.member.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
//	private DataSource ds;
//
//	public MemberDaoImpl() throws NamingException {
//		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
//	}

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
//		String sql = "insert into MEMBERS(MEMBER_NAME, PHONE, EMAIL, PASSWORD) values(?, ?, ?, ?)";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, member.getMember_name());
//			pstmt.setString(2, member.getPhone());
//			pstmt.setString(3, member.getEmail());
//			pstmt.setString(4, member.getPassword());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	// 使用member_name查詢相關資料
	@Override
	public Member selectByUsername(String membername) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> cQuery = cBuilder.createQuery(Member.class);
		Root<Member> root = cQuery.from(Member.class);
		cQuery.where(cBuilder.equal(root.get("membername"), membername));
		return session.createQuery(cQuery).uniqueResult();
//		String sql = "select * from MEMBERS where MEMBER_NAME = ?";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, member_name);
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					Member member = new Member();
//					member.setMember_id(rs.getInt("MEMBER_ID"));
//					member.setMember_name(rs.getString("MEMBER_NAME"));
//					member.setPhone(rs.getString("PHONE"));
//					member.setEmail(rs.getString("EMAIL"));
//					member.setPassword(rs.getString("PASSWORD"));
//					return member;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return session.get(Member.class, member_name);
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
		
//		final StringBuilder sql = new StringBuilder().append("UPDATE MEMBERS SET ");

//		int offset = 1;
//		StringBuilder sql = new StringBuilder("UPDATE MEMBERS SET ");
//		String gender = member.getGender();
//		Date date_of_birth = member.getDateofbirth();
//		String phone = member.getPhone();
//		String member_name = member.getMembername();
//
//		boolean hasMembername = member_name != null && !member_name.isEmpty();
//		boolean hasGender = gender != null && !gender.isEmpty();
//		boolean hasDateOfBirth = date_of_birth != null;
//		boolean hasPhone = phone != null && !phone.isEmpty();
//
//		if (hasGender) {
//			sql.append("GENDER = :gender, ");
//		}
//		if (hasDateOfBirth) {
//			sql.append("DATE_OF_BIRTH = :date_of_birth, ");
//		}
//		if (hasPhone) {
//			sql.append("PHONE = :phone, ");
//		}
//		if (hasMembername) {
//			sql.append("MEMBER_NAME = :member_name, ");
//		}
//
//		if (sql.charAt(sql.length() - 2) == ',') {
//			sql.delete(sql.length() - 2, sql.length());
//		}
//
//		// to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
//		sql.append("WHERE MEMBER_ID = :member_id");
//
//		Query<?> query = session.createQuery(sql.toString());
////		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
//		if (hasGender) {
//			query.setParameter("gender", member.getGender());
////				pstmt.setString(offset++, member.getGender());
//		}
//		if (hasDateOfBirth) {
//			query.setParameter("dateOfBirth", member.getDateofbirth());
////				pstmt.setDate(offset++, member.getDate_of_birth());
//		}
//		if (hasPhone) {
//			query.setParameter("phone", member.getPhone());
////				pstmt.setString(offset++, member.getPhone());
//		}
//		if (hasMembername) {
//			query.setParameter("memberName", member.getMembername());
////				pstmt.setString(offset++, member.getMember_name());
//		}
//
//		query.setParameter("memberId", member.getMemberid());
//		return query.executeUpdate();
		// to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
//			pstmt.setInt(offset, member.getMember_id());

//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
	}

	// 查詢帳號 密碼
	@Override
	public Member selectByUsernameAndPassword(String email, String password) {
		String sql = "select * from MEMBERS where EMAIL = :email and PASSWORD = :password";
		return session.createNativeQuery(sql, Member.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.uniqueResult();
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, member.getEmail());
//			pstmt.setString(2, member.getPassword());
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					member = new Member();
//					member.setMember_id(rs.getInt("MEMBER_ID"));
//					member.setMember_name(rs.getString("MEMBER_NAME"));
//					member.setEmail(rs.getString("EMAIL"));
//					member.setPassword(rs.getString("PASSWORD"));
//					member.setPhone(rs.getString("PHONE"));
//					member.setDate_of_birth(rs.getDate("DATE_OF_BIRTH"));
//					member.setGender(rs.getString("GENDER"));
//					member.setId_card(rs.getString("ID_CARD"));
//					member.setPhoto_base64(rs.getString("PHOTO_BASE64"));
//					return member;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	@Override
	public List<Member> selectAll() {
		final String sql = "FROM Member ORDER BY memberid";
		return session.createQuery(sql, Member.class).getResultList();
//		String sql = "select * from MEMBERS";
//		try (Connection conn = ds.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				ResultSet rs = pstmt.executeQuery()) {
//			List<Member> list = new ArrayList<>();
//
//			while (rs.next()) {
//				Member member = new Member();
//				member.setMember_id(rs.getInt("MEMBER_ID"));
//				member.setMember_name(rs.getString("MEMBER_NAME"));
//				member.setEmail(rs.getString("EMAIL"));
//				member.setPassword(rs.getString("PASSWORD"));
//				member.setDate_of_birth(rs.getDate("DATE_OF_BIRTH"));
//				member.setGender(rs.getString("GENDER"));
//				member.setEmail(rs.getString("EMAIL"));
//				member.setId_card(rs.getString("ID_CARD"));
//				member.setPhone(rs.getString("PHONE"));
//				list.add(member);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	// 刪除使用者
	@Override
	public Integer deletById(Integer member_id) {
//			String sql = "delete from MEMBERS where  MEMBER_ID = ?"; 
//			try (
//				Connection conn = ds.getConnection(); 
//				PreparedStatement pstmt = conn.prepareStatement(sql)
//			) {
//			pstmt.setInt(1, member_id);
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
//		String sql = "select * from MEMBERS where EMAIL = ?";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, email);
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					Member member = new Member();
//					member.setMember_id(rs.getInt("MEMBER_ID"));
//					member.setMember_name(rs.getString("MEMBER_NAME"));
//					member.setPhone(rs.getString("PHONE"));
//					member.setEmail(rs.getString("EMAIL"));
//					member.setPassword(rs.getString("PASSWORD"));
//					member.setPhoto_base64(rs.getString("PHOTO_BASE64"));
//					return member;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return session.get(Member.class, email);
	}

	// 查詢手機
	@Override
	public Member selectByPhone(String phone) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> cQuery = cBuilder.createQuery(Member.class);
		Root<Member> root = cQuery.from(Member.class);
		cQuery.where(cBuilder.equal(root.get("phone"), phone));
		return session.createQuery(cQuery).uniqueResult();
//		String sql = "select * from MEMBERS where PHONE = ?";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, phone);
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					Member member = new Member();
//					member.setMember_id(rs.getInt("MEMBER_ID"));
//					member.setMember_name(rs.getString("MEMBER_NAME"));
//					member.setPhone(rs.getString("PHONE"));
//					member.setEmail(rs.getString("EMAIL"));
//					member.setPassword(rs.getString("PASSWORD"));
//					return member;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return session.get(Member.class, phone);
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
		
		
//		final StringBuilder sql = new StringBuilder().append("UPDATE MEMBERS SET ");

//		int offset = 1;
//		StringBuilder sql = new StringBuilder("UPDATE MEMBERS SET ");
//		String photo_base64 = member.getPhotobase64();

//		boolean hasPhoto_base64 = photo_base64 != null && !photo_base64.isEmpty();

//		if (hasPhoto_base64) {
//			sql.append("PHOTOBASE64 = :photoBase64, ");
//		}

		// 如果沒有任何欄位需要更新
//		if (sql.toString().equals("UPDATE MEMBERS SET ")) {
//			System.out.println("沒有任何值" + photo_base64);
//			return 0;
//		}

		// to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
//		sql.append("WHERE MEMBER_ID = :memberId");

//		Query<?> query = session.createQuery(sql.toString());
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
//		if (hasPhoto_base64) {
////				pstmt.setString(offset++, member.getPhoto_base64());
//			query.setParameter("photoBase64", member.getPhotobase64());
//			System.out.println("要回傳" + photo_base64);
//		}
//
//		query.setParameter("memberId", member.getMemberid());
//		// to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
////			pstmt.setInt(offset, member.getMember_id());
//
////			return pstmt.executeUpdate();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//		return query.executeUpdate();
	}

	// 查詢大頭照
	@Override
	public Member selectimg(String photo_base64) {
//		String sql = "select * from MEMBERS where PHOTO_BASE64 = ?";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, photo_base64);
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					Member member = new Member();
//					member.setMember_id(rs.getInt("MEMBER_ID"));
//					member.setMember_name(rs.getString("MEMBER_NAME"));
//					member.setEmail(rs.getString("EMAIL"));
//					member.setPhoto_base64(rs.getString("PHOTO_BASE64"));
//					return member;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
	public int forgetpassword(String email) {
		final String hql = "UPDATE Member SET PASSWORD = :password WHERE email = :email";
		String newPassword = "chilltribe202";  
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
//		@Override
//		public Member selectaddress(Member address) {
//			String sql = "SELECT * FROM ADDRESSES JOIN CITIES ON ADDRESSES.CITY_ID = CITIES.CITY_ID JOIN DISTRICTS ON ADDRESSES.DISTRICT_ID = DISTRICTS.DISTRICT_ID WHERE MEMBER_ID = ?";
//			try (Connection conn = ds.getConnection(); 
//					PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setInt(1, address.getMember_id());
//				try (ResultSet rs = pstmt.executeQuery()) {
//					if (rs.next()) {
//						Member member = new Member();
//						member.setMember_id(rs.getInt("MEMBER_ID"));
//						member.setZip_code(rs.getInt("ZIP_CODE"));
//						member.setCity_id(rs.getInt("CITY_ID"));
//						member.setCity_name(rs.getString("CITY_NAME"));
//						member.setDistrict_id(rs.getInt("DISTRICT_ID"));
//						member.setDistrict_name(rs.getString("DISTRICT_NAME"));
//						member.setAddress_id(rs.getInt("ADDRESS_ID"));
//						member.setAddress(rs.getString("ADDRESS"));
//						member.setTag(rs.getString("TAG"));
//						member.setAddress_default(rs.getInt("ADDRESS_DEFAULT"));
//						return member;
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
//		}

//		@Override
//		public List<Member> selectaddressAll(Integer member_id) {
//			String sql = "SELECT * FROM ADDRESSES JOIN CITIES ON ADDRESSES.CITY_ID = CITIES.CITY_ID JOIN DISTRICTS ON ADDRESSES.DISTRICT_ID = DISTRICTS.DISTRICT_ID WHERE MEMBER_ID = ?";
//			try (Connection conn = ds.getConnection();
//					PreparedStatement pstmt = conn.prepareStatement(sql)) {
//					pstmt.setInt(1, member_id);
//					ResultSet rs = pstmt.executeQuery(); {
//				List<Member> list = new ArrayList<>();
//
//				while (rs.next()) {
//					Member member = new Member();
//					member.setMember_id(rs.getInt("MEMBER_ID"));
//					member.setZip_code(rs.getInt("ZIP_CODE"));
//					member.setCity_id(rs.getInt("CITY_ID"));
//					member.setCity_name(rs.getString("CITY_NAME"));
//					member.setDistrict_id(rs.getInt("DISTRICT_ID"));
//					member.setDistrict_name(rs.getString("DISTRICT_NAME"));
//					member.setAddress_id(rs.getInt("ADDRESS_ID"));
//					member.setAddress(rs.getString("ADDRESS"));
//					member.setTag(rs.getString("TAG"));
//					member.setAddress_default(rs.getInt("ADDRESS_DEFAULT"));
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

//		@Override
//		public int upaddress(Member member) {
////			String sql = "insert into ADDRESSES(MEMBER_ID, CITY_ID, DISTRICT_ID, ADDRESS, ADDRESS_DEFAULT, TAG) values(?, ?, ?, ?, ?, ?)";
//			String sql = "insert into ADDRESSES(MEMBER_ID, CITY_ID, DISTRICT_ID, ADDRESS, TAG) values(?, ?, ?, ?, ?)";
//			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setInt(1, member.getMember_id());
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
//		}

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