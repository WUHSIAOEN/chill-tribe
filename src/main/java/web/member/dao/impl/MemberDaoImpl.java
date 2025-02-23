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
import javax.sql.DataSource;

import web.member.dao.MemberDao;
import web.member.vo.Member;

public class MemberDaoImpl implements MemberDao {
	private DataSource ds;

	public MemberDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
	}

	// 註冊會員資料
	@Override
	public int insert(Member member) {
		String sql = "insert into MEMBERS(MEMBER_NAME, PHONE, EMAIL, PASSWORD) values(?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getMember_name());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPassword());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 使用member_name查詢相關資料
	@Override
	public Member selectByUsername(String member_name) {
		String sql = "select * from MEMBERS where MEMBER_NAME = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member_name);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Member member = new Member();
					member.setMember_id(rs.getInt("MEMBER_ID"));
					member.setMember_name(rs.getString("MEMBER_NAME"));
					member.setPhone(rs.getString("PHONE"));
					member.setEmail(rs.getString("EMAIL"));
					member.setPassword(rs.getString("PASSWORD"));
					return member;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 更新資料
	@Override
	public int update(Member member) {
	    int offset = 1;  
	    StringBuilder sql = new StringBuilder("UPDATE MEMBERS SET ");
	    String gender = member.getGender();
	    Date date_of_birth = member.getDate_of_birth();
	    String phone = member.getPhone();
	    String member_name = member.getMember_name();
	    
	    boolean hasMembername = member_name != null && !member_name.isEmpty();
	    boolean hasGender = gender != null && !gender.isEmpty();
	    boolean hasDateOfBirth = date_of_birth != null;
	    boolean hasPhone = phone != null && !phone.isEmpty();

	    if (hasGender) {
	        sql.append("GENDER = ?, ");
	    }
	    if (hasDateOfBirth) {
	        sql.append("DATE_OF_BIRTH = ?, ");
	    }
	    if (hasPhone) {
	        sql.append("PHONE = ?, ");
	    }
	    if (hasMembername) {
	        sql.append("MEMBER_NAME = ?, ");
	    }
	    
	    if (sql.charAt(sql.length() - 2) == ',') {
	        sql.delete(sql.length() - 2, sql.length());
	    }

	    // to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
	    sql.append("WHERE MEMBER_ID = ?");

	    try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
	        if (hasGender) {
	            pstmt.setString(offset++, member.getGender());
	        }
	        if (hasDateOfBirth) {
	        	pstmt.setDate(offset++, member.getDate_of_birth());
	        }
	        if (hasPhone) {
	            pstmt.setString(offset++, member.getPhone());
	        }
	        if (hasMembername) {
	            pstmt.setString(offset++, member.getMember_name());
	        }

	        // to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
	        pstmt.setInt(offset, member.getMember_id());
	        
	        return pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1;
	}

	// 查詢帳號 密碼
	@Override
	public Member selectByUsernameAndPassword(Member member) {
		String sql = "select * from MEMBERS where EMAIL = ? and PASSWORD = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					member = new Member();
					member.setMember_id(rs.getInt("MEMBER_ID"));
					member.setMember_name(rs.getString("MEMBER_NAME"));
					member.setEmail(rs.getString("EMAIL"));
					member.setPassword(rs.getString("PASSWORD"));
					member.setPhone(rs.getString("PHONE"));
					member.setDate_of_birth(rs.getDate("DATE_OF_BIRTH"));
					member.setGender(rs.getString("GENDER"));
					member.setId_card(rs.getString("ID_CARD"));
					member.setPhoto_base64(rs.getString("PHOTO_BASE64"));
					return member;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Member> selectAll() {
		String sql = "select * from MEMBERS";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			List<Member> list = new ArrayList<>();

			while (rs.next()) {
				Member member = new Member();
				member.setMember_id(rs.getInt("MEMBER_ID"));
				member.setMember_name(rs.getString("MEMBER_NAME"));
				member.setEmail(rs.getString("EMAIL"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setDate_of_birth(rs.getDate("DATE_OF_BIRTH"));
				member.setGender(rs.getString("GENDER"));
				member.setEmail(rs.getString("EMAIL"));
				member.setId_card(rs.getString("ID_CARD"));
				member.setPhone(rs.getString("PHONE"));
				list.add(member);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
		// 刪除使用者
		@Override
		public Integer deletById(Integer member_id) {
			String sql = "delete from MEMBERS where  MEMBER_ID = ?"; 
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)
			) {
			pstmt.setInt(1, member_id);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

		// 查詢Email
		@Override
		public Member selectByEmail(String email) {
			String sql = "select * from MEMBERS where EMAIL = ?";
			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, email);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Member member = new Member();
						member.setMember_id(rs.getInt("MEMBER_ID"));
						member.setMember_name(rs.getString("MEMBER_NAME"));
						member.setPhone(rs.getString("PHONE"));
						member.setEmail(rs.getString("EMAIL"));
						member.setPassword(rs.getString("PASSWORD"));
						member.setPhoto_base64(rs.getString("PHOTO_BASE64"));
						return member;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// 查詢手機
		@Override
		public Member selectByPhone(String phone) {
			String sql = "select * from MEMBERS where PHONE = ?";
			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, phone);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Member member = new Member();
						member.setMember_id(rs.getInt("MEMBER_ID"));
						member.setMember_name(rs.getString("MEMBER_NAME"));
						member.setPhone(rs.getString("PHONE"));
						member.setEmail(rs.getString("EMAIL"));
						member.setPassword(rs.getString("PASSWORD"));
						return member;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// 大頭照
		@Override
		public int updateimg(Member member) {
			int offset = 1;  
		    StringBuilder sql = new StringBuilder("UPDATE MEMBERS SET ");
		    String photo_base64 = member.getPhoto_base64();
		    
		    boolean hasPhoto_base64 = photo_base64 != null && !photo_base64.isEmpty();
		    
		    if (hasPhoto_base64) {
		        sql.append("PHOTO_BASE64 = ? ");
		    }
		    
		    
		 // 如果沒有任何欄位需要更新，直接返回0
		    if (sql.toString().equals("UPDATE MEMBERS SET ")) {
		    	System.out.println("沒有任何值" + photo_base64);
		        return 0;  
		    }
		 

		    // to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
		    sql.append("WHERE MEMBER_ID = ?");

		    try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
		        if (hasPhoto_base64) {
		            pstmt.setString(offset++, member.getPhoto_base64());
		            System.out.println("要回傳" + photo_base64);
		        }
		        
		       
		        // to do list 修改成member_id 因為使用member_name會導致你如果修改的是member_name會抓不到
		        pstmt.setInt(offset, member.getMember_id());
		        
		        return pstmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return -1;
		}

		
		// 查詢大頭照
		@Override
		public Member selectimg(String photo_base64) {
			String sql = "select * from MEMBERS where PHOTO_BASE64 = ?";
			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, photo_base64);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Member member = new Member();
						member.setMember_id(rs.getInt("MEMBER_ID"));
						member.setMember_name(rs.getString("MEMBER_NAME"));
						member.setEmail(rs.getString("EMAIL"));
						member.setPhoto_base64(rs.getString("PHOTO_BASE64"));
						return member;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// 地址查詢
		@Override
		public Member selectaddress(Member address) {
			String sql = "select * from ADDRESSES where MEMBER_ID = ?";
			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, address.getMember_id());
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Member member = new Member();
						member.setMember_id(rs.getInt("MEMBER_ID"));
						member.setMember_name(rs.getString("MEMBER_NAME"));
						member.setEmail(rs.getString("EMAIL"));
						member.setCity_id(rs.getString("CITY_ID"));
						member.setDistrict_id(rs.getString("DISTRICT_ID"));
						member.setAddress_id(rs.getString("ADDRESS_ID"));
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
}