package web.googlelogin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import core.vo.GoogleData;
import web.googlelogin.dao.GoogleDao;
import web.member.vo.Member;

public class GoogleDaoImpl implements GoogleDao {
	
//	@Autowired
	private DataSource ds;
	
	public GoogleDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
	}
	
//	public GoogleDaoImpl(DataSource ds) {
//        this.ds = ds;
//    }
	
	@PersistenceContext
	private Session session;

	@Override
	public Member selectBygoogleEmail(GoogleData gd) {
		
		String sql = "select * from MEMBERS where EMAIL = ?";
		System.out.println("SQL：" + sql);
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, gd.getEmail());
			System.out.println("1：" + gd.getEmail());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Member member = new Member();
					member.setEmail(rs.getString("EMAIL"));
					member.setMemberid(rs.getInt("MEMBER_ID"));
					System.out.println("DAO：" + gd);
					return member;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DAO：Oh發錯錯誤了");
		}
		return null;
	}

}
