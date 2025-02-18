package web.supplier.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.member.vo.Member;
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
					supplier.setAbout(rs.getString("ABOUT"));
					supplier.setId_number(rs.getString("ID_NUMBER"));
					supplier.setAddress(rs.getString("ADDRESS"));
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
		int offset = 1;
		StringBuilder sql = new StringBuilder("UPDATE SUPPLIERS SET ");
		String contact_person = supplier.getContact_person();
		String supplier_name = supplier.getSupplier_name();
		String id_number = supplier.getId_number();
		String address = supplier.getAddress();
		String phone = supplier.getPhone();
		String email = supplier.getEmail();
		String about = supplier.getAbout();
		
		boolean hasContact_person = contact_person != null && !contact_person.isEmpty();
		boolean hasSupplier_name = supplier_name != null && !supplier_name.isEmpty();
		boolean hasId_number = id_number != null && !id_number.isEmpty();
		boolean hasAddress = address != null && !address.isEmpty();
		boolean hasPhone = phone != null && !phone.isEmpty();
		boolean hasEmail = email != null && !email.isEmpty();
		boolean hasAbout = about != null && !about.isEmpty();
		
		if (hasContact_person) {
			sql.append("CONTACT_PERSON = ?, ");
		}
		
		if (hasSupplier_name) {
			sql.append("SUPPLIER_NAME = ?, ");
		}
		
		if (hasId_number) {
			sql.append("ID_NUMBER = ?, ");
		}
		
		if (hasAddress) {
			sql.append("ADDRESS = ?, ");
		}
		
		if (hasPhone) {
			sql.append("PHONE = ?, ");
		}
		
		if (hasEmail) {
			sql.append("EMAIL = ?, ");
		}
		
		if (hasAbout) {
			sql.append("ABOUT = ?, ");
		}
		
		if (sql.charAt(sql.length() - 2) == ',') {
	        sql.delete(sql.length() - 2, sql.length());
	    }
		
		sql.append(" WHERE SUPPLIER_ID = ?");
		
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			if (hasContact_person) {
				pstmt.setString(offset++, supplier.getContact_person());
			}
			
			if (hasSupplier_name) {
				pstmt.setString(offset++, supplier.getSupplier_name());
			}
			
			if (hasId_number) {
				pstmt.setString(offset++, supplier.getId_number());
			}
			
			if (hasAddress) {
				pstmt.setString(offset++, supplier.getAddress());
			}
			
			if (hasPhone) {
				pstmt.setString(offset++, supplier.getPhone());
			}
			
			if (hasEmail) {
				pstmt.setString(offset++, supplier.getEmail());
			}
			
			if (hasAbout) {
				pstmt.setString(offset++, supplier.getAbout());
			}

			pstmt.setInt(offset, supplier.getSupplier_id());
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
					supplier.setPhone(rs.getString("PHONE"));
					supplier.setAbout(rs.getString("ABOUT"));
					supplier.setId_number(rs.getString("ID_NUMBER"));
					supplier.setAddress(rs.getString("ADDRESS"));
					supplier.setContact_person(rs.getString("CONTACT_PERSON"));
					supplier.setImage(rs.getString("IMAGE"));
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
				Supplier supplier = new Supplier();
				supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
				supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
				supplier.setEmail(rs.getString("EMAIL"));
				supplier.setPassword(rs.getString("PASSWORD"));
				supplier.setId_number(rs.getString("ID_NUMBER"));
				supplier.setPhone(rs.getString("PHONE"));
				supplier.setAbout(rs.getString("ABOUT"));
				supplier.setId_number(rs.getString("ID_NUMBER"));
				supplier.setAddress(rs.getString("ADDRESS"));
				supplier.setContact_person(rs.getString("CONTACT_PERSON"));
				list.add(supplier);
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
		
		
		// 大頭照
				@Override
				public int updateimg(Supplier supplier) {
					int offset = 1;  
				    StringBuilder sql = new StringBuilder("UPDATE SUPPLIERS SET ");
				    String image = supplier.getImage();
				    
				    boolean hasImage = image != null && !image.isEmpty();
				    
				    if (hasImage) {
				        sql.append("IMAGE = ? ");
				    }
				    
				    
				 // 如果沒有任何欄位需要更新，直接返回0
				    if (sql.toString().equals("UPDATE SUPPLERS SET ")) {
				    	System.out.println("沒有任何值" + image);
				        return 0;  
				    }
				 

				    // to do list 修改成supplier_id 因為使用supplier_name會導致你如果修改的是supplier_name會抓不到
				    sql.append("WHERE SUPPLIER_ID = ?");

				    try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
				        if (hasImage) {
				            pstmt.setString(offset++, supplier.getImage());
				            System.out.println("要回傳" + image);
				        }
				        
				       
				        // to do list 修改成supplier_id 因為使用supplier_name會導致你如果修改的是supplier_name會抓不到
				        pstmt.setInt(offset, supplier.getSupplier_id());
				        
				        return pstmt.executeUpdate();
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				    return -1;
				}
		

		@Override
		public Supplier selectimg(String image) {
			String sql = "select * from SUPPLIERS where IMAGE = ?";
			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, image);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Supplier supplier = new Supplier();
						supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
						supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
						supplier.setEmail(rs.getString("EMAIL"));
						supplier.setImage(rs.getString("IMAGE"));
						return supplier;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}