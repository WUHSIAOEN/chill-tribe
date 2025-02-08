package web.supplier.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.supplier.dao.SupplierDao;
import web.supplier.vo.Supplier;

public class SupplierDaoImpl implements SupplierDao {
	private DataSource ds;

	public SupplierDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
	}

	@Override
	public int insert(Supplier supplier) {
		String sql = "insert into SUPPLIERS(SUPPLIER_NAME, PHONE, EMAIL, PASSWORD, ID_NUMBER) values(?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, supplier.getSupplier_name());
			pstmt.setString(2, supplier.getPhone());
			pstmt.setString(3, supplier.getEmail());
			pstmt.setString(4, supplier.getPassword());
			pstmt.setString(5, supplier.getId_number());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Supplier selectByUsername(String supplier_name) {
		String sql = "select * from SUPPLIERSS where SUPPLIER_NAME = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, supplier_name);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Supplier supplier = new Supplier();
					supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
					supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
					supplier.setPhone(rs.getString("PHONE"));
					supplier.setEmail(rs.getString("EMAIL"));
					supplier.setPassword(rs.getString("PASSWORD"));
					return supplier;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(Supplier supplier) {
		int offset = 0;
		StringBuilder sql = new StringBuilder("update SUPPLIERS set ");
		String password = supplier.getPassword();
		boolean hasPassword = password != null && !password.isEmpty();
		if (hasPassword) {
			sql.append("PASSWORD = ?,");
		}
		sql.append("SUPPLIER_NAME = ? ");
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			if (hasPassword) {
				pstmt.setString(1, supplier.getPassword());
				offset++;
			}

			pstmt.setString(2 + offset, supplier.getSupplier_name());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Supplier selectByUsernameAndPassword(Supplier supplier) {
		String sql = "select * from SUPPLIERS where EMAIL = ? and PASSWORD = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, supplier.getEmail());
			pstmt.setString(2, supplier.getPassword());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					supplier = new Supplier();
					supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
					supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
					supplier.setEmail(rs.getString("EMAIL"));
					supplier.setPassword(rs.getString("PASSWORD"));
					return supplier;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Supplier> selectAll() {
		String sql = "select * from SUPPLIERS";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			List<Supplier> list = new ArrayList<>();

			while (rs.next()) {
				Supplier member = new Supplier();
				member.setSupplier_id(rs.getInt("SUPPLIER_ID"));
				member.setSupplier_name(rs.getString("SUPPLIER_NAME"));
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
		public Integer deletById(Integer supplier_id) {
			String sql = "delete from SUPPLIERS where  SUPPLIER_ID = ?"; 
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)
			) {
			pstmt.setInt(1, supplier_id);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

		@Override
		public Supplier selectByEmail(String email) {
			String sql = "select * from SUPPLIERS where EMAIL = ?";
			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, email);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Supplier supplier = new Supplier();
						supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
						supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
						supplier.setPhone(rs.getString("PHONE"));
						supplier.setEmail(rs.getString("EMAIL"));
						supplier.setPassword(rs.getString("PASSWORD"));
						return supplier;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public Supplier selectByPhone(String phone) {
			String sql = "select * from SUPPLIERS where PHONE = ?";
			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, phone);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Supplier supplier = new Supplier();
						supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
						supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
						supplier.setPhone(rs.getString("PHONE"));
						supplier.setEmail(rs.getString("EMAIL"));
						supplier.setPassword(rs.getString("PASSWORD"));
						return supplier;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}