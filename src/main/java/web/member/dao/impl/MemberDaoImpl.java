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
		int offset = 0;
		StringBuilder sql = new StringBuilder("update MEMBERS set ");
		String password = member.getPassword();
		boolean hasPassword = password != null && !password.isEmpty();
		if (hasPassword) {
			sql.append("PASSWORD = ?,");
		}
		sql.append("MEMBER_NAME = ? ");
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			if (hasPassword) {
				pstmt.setString(1, member.getPassword());
				offset++;
			}

			pstmt.setString(2 + offset, member.getMember_name());
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