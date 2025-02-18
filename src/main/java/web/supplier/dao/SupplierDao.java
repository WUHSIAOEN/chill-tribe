package web.supplier.dao;

import java.util.List;

import web.member.vo.Member;
import web.supplier.vo.Supplier;

public interface SupplierDao {

	int insert(Supplier supplier);
	
	// SQL:select * from MEMBER where USERNAME = ?
	Supplier selectByUsername(String username);
	
	Supplier selectByEmail(String email);
	
	Supplier selectByPhone(String phone);
	
	int update(Supplier supplier);
	
	Supplier selectByUsernameAndPassword(Supplier supplier);
	
	List<Supplier> selectAll();
	
	Integer deletById(Integer id);
	
	int updateimg(Supplier supplier);
	
	Supplier selectimg(String image);
}
