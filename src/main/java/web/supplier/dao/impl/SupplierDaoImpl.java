package web.supplier.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

import web.member.vo.Member;
import web.supplier.dao.SupplierDao;
import web.supplier.vo.Supplier;

@Repository
public class SupplierDaoImpl implements SupplierDao {
//	private DataSource ds;

//	public SupplierDaoImpl() throws NamingException {
//		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/chilltribe");
//	}
	
	@PersistenceContext
	private Session session;

	@Override
	public int insert(Supplier supplier) {
		
		try {
			session.persist(supplier);
			System.out.println("SupplierDaoImpl：新增一筆會員資料成功");
			return -1;
		} catch (Exception e) {
			System.out.println("SupplierDaoImpl：新增一筆會員資料失敗");
			return 1;
		}
		
//		String sql = "insert into SUPPLIERS(SUPPLIER_NAME, PHONE, EMAIL, PASSWORD, ID_NUMBER) values(?, ?, ?, ?, ?)";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, supplier.getSupplier_name());
//			pstmt.setString(2, supplier.getPhone());
//			pstmt.setString(3, supplier.getEmail());
//			pstmt.setString(4, supplier.getPassword());
//			pstmt.setString(5, supplier.getId_number());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
	}

	@Override
	public Supplier selectByUsername(String supplier_name) {
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Supplier> cQuery = cBuilder.createQuery(Supplier.class);
		Root<Supplier> root = cQuery.from(Supplier.class);
		cQuery.where(cBuilder.equal(root.get("supplier_name"), supplier_name));
		return session.createQuery(cQuery).uniqueResult();
		
		
//		String sql = "select * from SUPPLIERSS where SUPPLIER_NAME = ?";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, supplier_name);
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					Supplier supplier = new Supplier();
//					supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
//					supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
//					supplier.setPhone(rs.getString("PHONE"));
//					supplier.setEmail(rs.getString("EMAIL"));
//					supplier.setPassword(rs.getString("PASSWORD"));
//					supplier.setAbout(rs.getString("ABOUT"));
//					supplier.setId_number(rs.getString("ID_NUMBER"));
//					supplier.setAddress(rs.getString("ADDRESS"));
//					return supplier;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	@Override
	public int update(Supplier supplier) {
		
		final StringBuilder hql = new StringBuilder()
				.append("UPDATE Supplier SET ");
		
		hql.append("supplier_name = :supplier_name,")
		.append("contact_person = :contact_person,")
		.append("address = :address,")
		.append("email = :email,")
		.append("phone = :phone,")
		.append("id_number = :id_number,")
		.append("about = :about ")
		.append("WHERE supplier_id = :supplier_id");
		
		Query<?> query = session.createQuery(hql.toString());
		return query
			.setParameter("supplier_name", supplier.getSupplier_name())
			.setParameter("phone", supplier.getPhone())
			.setParameter("contact_person", supplier.getContact_person())
			.setParameter("address", supplier.getAddress())
			.setParameter("email", supplier.getEmail())
			.setParameter("id_number", supplier.getId_number())
			.setParameter("about", supplier.getAbout())
			.setParameter("supplier_id", supplier.getSupplier_id())
			.executeUpdate();
		
//		int offset = 1;
//		StringBuilder sql = new StringBuilder("UPDATE SUPPLIERS SET ");
//		String contact_person = supplier.getContact_person();
//		String supplier_name = supplier.getSupplier_name();
//		String id_number = supplier.getId_number();
//		String address = supplier.getAddress();
//		String phone = supplier.getPhone();
//		String email = supplier.getEmail();
//		String about = supplier.getAbout();
//		
//		boolean hasContact_person = contact_person != null && !contact_person.isEmpty();
//		boolean hasSupplier_name = supplier_name != null && !supplier_name.isEmpty();
//		boolean hasId_number = id_number != null && !id_number.isEmpty();
//		boolean hasAddress = address != null && !address.isEmpty();
//		boolean hasPhone = phone != null && !phone.isEmpty();
//		boolean hasEmail = email != null && !email.isEmpty();
//		boolean hasAbout = about != null && !about.isEmpty();
//		
//		if (hasContact_person) {
//			sql.append("CONTACT_PERSON = ?, ");
//		}
//		
//		if (hasSupplier_name) {
//			sql.append("SUPPLIER_NAME = ?, ");
//		}
//		
//		if (hasId_number) {
//			sql.append("ID_NUMBER = ?, ");
//		}
//		
//		if (hasAddress) {
//			sql.append("ADDRESS = ?, ");
//		}
//		
//		if (hasPhone) {
//			sql.append("PHONE = ?, ");
//		}
//		
//		if (hasEmail) {
//			sql.append("EMAIL = ?, ");
//		}
//		
//		if (hasAbout) {
//			sql.append("ABOUT = ?, ");
//		}
//		
//		if (sql.charAt(sql.length() - 2) == ',') {
//	        sql.delete(sql.length() - 2, sql.length());
//	    }
//		
//		sql.append(" WHERE SUPPLIER_ID = ?");
//		
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
//			if (hasContact_person) {
//				pstmt.setString(offset++, supplier.getContact_person());
//			}
//			
//			if (hasSupplier_name) {
//				pstmt.setString(offset++, supplier.getSupplier_name());
//			}
//			
//			if (hasId_number) {
//				pstmt.setString(offset++, supplier.getId_number());
//			}
//			
//			if (hasAddress) {
//				pstmt.setString(offset++, supplier.getAddress());
//			}
//			
//			if (hasPhone) {
//				pstmt.setString(offset++, supplier.getPhone());
//			}
//			
//			if (hasEmail) {
//				pstmt.setString(offset++, supplier.getEmail());
//			}
//			
//			if (hasAbout) {
//				pstmt.setString(offset++, supplier.getAbout());
//			}
//
//			pstmt.setInt(offset, supplier.getSupplier_id());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
	}

	@Override
	public Supplier selectByUsernameAndPassword(String email, String password) {
		String sql = "select * from SUPPLIERS where EMAIL = :email and PASSWORD = :password";
		return session.createNativeQuery(sql, Supplier.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.uniqueResult();
//		String sql = "select * from SUPPLIERS where EMAIL = ? and PASSWORD = ?";
//		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, supplier.getEmail());
//			pstmt.setString(2, supplier.getPassword());
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					supplier = new Supplier();
//					supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
//					supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
//					supplier.setEmail(rs.getString("EMAIL"));
//					supplier.setPassword(rs.getString("PASSWORD"));
//					supplier.setPhone(rs.getString("PHONE"));
//					supplier.setAbout(rs.getString("ABOUT"));
//					supplier.setId_number(rs.getString("ID_NUMBER"));
//					supplier.setAddress(rs.getString("ADDRESS"));
//					supplier.setContact_person(rs.getString("CONTACT_PERSON"));
//					supplier.setImage(rs.getString("IMAGE"));
//					return supplier;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	@Override
	public List<Supplier> selectAll() {
		final String sql = "FROM Supplier ORDER BY supplier_id";
		return session.createQuery(sql, Supplier.class).getResultList();
		
//		String sql = "select * from SUPPLIERS";
//		try (Connection conn = ds.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				ResultSet rs = pstmt.executeQuery()) {
//			List<Supplier> list = new ArrayList<>();
//
//			while (rs.next()) {
//				Supplier supplier = new Supplier();
//				supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
//				supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
//				supplier.setEmail(rs.getString("EMAIL"));
//				supplier.setPassword(rs.getString("PASSWORD"));
//				supplier.setId_number(rs.getString("ID_NUMBER"));
//				supplier.setPhone(rs.getString("PHONE"));
//				supplier.setAbout(rs.getString("ABOUT"));
//				supplier.setId_number(rs.getString("ID_NUMBER"));
//				supplier.setAddress(rs.getString("ADDRESS"));
//				supplier.setContact_person(rs.getString("CONTACT_PERSON"));
//				list.add(supplier);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}
	
		
		@Override
		public Integer deletById(Integer supplier_id) {
			Supplier supplier = session.load(Supplier.class, supplier_id);
			session.remove(supplier);
			System.out.println("刪除了一筆會員");
			return -1;
//			String sql = "delete from SUPPLIERS where  SUPPLIER_ID = ?"; 
//			try (
//				Connection conn = ds.getConnection(); 
//				PreparedStatement pstmt = conn.prepareStatement(sql)
//			) {
//			pstmt.setInt(1, supplier_id);
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
	}

		@Override
		public Supplier selectByEmail(String email) {
			CriteriaBuilder cBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Supplier> cQuery = cBuilder.createQuery(Supplier.class);
			Root<Supplier> root = cQuery.from(Supplier.class);
			cQuery.where(cBuilder.equal(root.get("email"), email));
			return session.createQuery(cQuery).uniqueResult();
			
//			String sql = "select * from SUPPLIERS where EMAIL = ?";
//			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setString(1, email);
//				try (ResultSet rs = pstmt.executeQuery()) {
//					if (rs.next()) {
//						Supplier supplier = new Supplier();
//						supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
//						supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
//						supplier.setPhone(rs.getString("PHONE"));
//						supplier.setEmail(rs.getString("EMAIL"));
//						supplier.setPassword(rs.getString("PASSWORD"));
//						return supplier;
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
		}

		@Override
		public Supplier selectByPhone(String phone) {
			CriteriaBuilder cBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Supplier> cQuery = cBuilder.createQuery(Supplier.class);
			Root<Supplier> root = cQuery.from(Supplier.class);
			cQuery.where(cBuilder.equal(root.get("phone"), phone));
			return session.createQuery(cQuery).uniqueResult();
			
//			String sql = "select * from SUPPLIERS where PHONE = ?";
//			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setString(1, phone);
//				try (ResultSet rs = pstmt.executeQuery()) {
//					if (rs.next()) {
//						Supplier supplier = new Supplier();
//						supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
//						supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
//						supplier.setPhone(rs.getString("PHONE"));
//						supplier.setEmail(rs.getString("EMAIL"));
//						supplier.setPassword(rs.getString("PASSWORD"));
//						return supplier;
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
		}
		
		
		// 大頭照
				@Override
				public int updateimg(Supplier supplier) {
					final StringBuilder hql = new StringBuilder()
							.append("UPDATE Supplier SET ");
					
					String image = supplier.getImage();
					
					hql.append("image = :image ")
					.append("WHERE supplier_id = :supplier_id");
					
					Query<?> query = session.createQuery(hql.toString());
					return query
						.setParameter("image", supplier.getImage())
						.setParameter("supplier_id", supplier.getSupplier_id())
						.executeUpdate();
					
//					int offset = 1;  
//				    StringBuilder sql = new StringBuilder("UPDATE SUPPLIERS SET ");
//				    String image = supplier.getImage();
//				    
//				    boolean hasImage = image != null && !image.isEmpty();
//				    
//				    if (hasImage) {
//				        sql.append("IMAGE = ? ");
//				    }
//				    
//				    
//				 // 如果沒有任何欄位需要更新，直接返回0
//				    if (sql.toString().equals("UPDATE SUPPLERS SET ")) {
//				    	System.out.println("沒有任何值" + image);
//				        return 0;  
//				    }
//				 
//
//				    // to do list 修改成supplier_id 因為使用supplier_name會導致你如果修改的是supplier_name會抓不到
//				    sql.append("WHERE SUPPLIER_ID = ?");
//
//				    try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
//				        if (hasImage) {
//				            pstmt.setString(offset++, supplier.getImage());
//				            System.out.println("要回傳" + image);
//				        }
//				        
//				       
//				        // to do list 修改成supplier_id 因為使用supplier_name會導致你如果修改的是supplier_name會抓不到
//				        pstmt.setInt(offset, supplier.getSupplier_id());
//				        
//				        return pstmt.executeUpdate();
//				    } catch (Exception e) {
//				        e.printStackTrace();
//				    }
//				    return -1;
				}
		

		@Override
		public Supplier selectimg(String image) {
//			String sql = "select * from SUPPLIERS where IMAGE = ?";
//			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setString(1, image);
//				try (ResultSet rs = pstmt.executeQuery()) {
//					if (rs.next()) {
//						Supplier supplier = new Supplier();
//						supplier.setSupplier_id(rs.getInt("SUPPLIER_ID"));
//						supplier.setSupplier_name(rs.getString("SUPPLIER_NAME"));
//						supplier.setEmail(rs.getString("EMAIL"));
//						supplier.setImage(rs.getString("IMAGE"));
//						return supplier;
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
			return session.get(Supplier.class, image);
		}

		@Override
		public Supplier selectBySupplierID(Integer supplier_id) {
			final String hql = "FROM Supplier WHERE supplier_id = :supplier_id";
			return session
					.createQuery(hql, Supplier.class)
					.setParameter("supplier_id", supplier_id) 
					.uniqueResult(); 
		}

		@Override
		public int forgetpassword(String email) {
			final String hql = "UPDATE Supplier SET PASSWORD = :password WHERE email = :email";
			String newPassword = "chilltribe202";  
			Query<?> query = session.createQuery(hql.toString());
			return query
		    	.setParameter("password", newPassword)
				.setParameter("email", email)
				.executeUpdate();
		}
}