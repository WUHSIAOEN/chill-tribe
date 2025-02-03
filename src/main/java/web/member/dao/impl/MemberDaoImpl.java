package web.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

	@Override
	public int update(Member member) {
		int offset = 1;  // 修改為 1，因為 PreparedStatement 參數索引從 1 開始
	    StringBuilder sql = new StringBuilder("update MEMBERS set ");
	    String gender = member.getGender();
	    String date_of_birth = member.getDate_of_birth();
	    String phone = member.getPhone();
	    String member_name = member.getMember_name();
	    boolean hasMembername = member_name != null && !member_name.isEmpty();
	    boolean hasGender = gender != null && !gender.isEmpty();
	    boolean hasDateOfBirth = date_of_birth != null && !date_of_birth.isEmpty();
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
	        sql.append("MEMBER_NAME = ? ");
	    }

	    // 去除 SQL 語句末尾的多餘逗號
	    String sqlQuery = sql.toString();
	    if (sqlQuery.endsWith(", ")) {
	        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
	    }

	    // 確保在更新語句中有要更新的欄位
	    if (sqlQuery.equals("update MEMBERS set")) {
	        return 0;  // 如果沒有任何欄位需要更新，則返回 0
	    }

	    // 加上 WHERE 條件來指定更新的會員 (假設使用 member_id 或其他識別欄位)
	    sqlQuery += " WHERE EMAIL = ?";  // 假設 EMAIL 是更新條件

	    try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
	        // 動態設置參數
	        if (hasGender) {
	            pstmt.setString(offset++, member.getGender());
	        }
	        if (hasDateOfBirth) {
	            pstmt.setString(offset++, member.getDate_of_birth());
	        }
	        if (hasPhone) {
	            pstmt.setString(offset++, member.getPhone());
	        }
	        if (hasMembername) {
	            pstmt.setString(offset++, member.getMember_name());
	        }

	        // 假設需要使用 email 作為條件進行更新
	        pstmt.setString(offset, member.getEmail());  // 假設 Member 類別有 getEmail() 方法

	        return pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1;
	}

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
					member.setDate_of_birth(rs.getString("DATE_OF_BIRTH"));
					member.setGender(rs.getString("GENDER"));
					member.setId_card(rs.getString("ID_CARD"));
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
				member.setDate_of_birth(rs.getString("DATE_OF_BIRTH"));
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
						return member;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

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
}