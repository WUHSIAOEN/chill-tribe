package web.supplier.dao;

import java.util.List;

import web.member.vo.Member;
import web.supplier.vo.Supplier;

public interface SupplierDao {

	int insert(Supplier supplier);
	
	Supplier selectByUsername(String username);
	
	Supplier selectByEmail(String email);
	
	Supplier selectBySupplierID(Integer supplier_id);
	
	Supplier selectByPhone(String phone);
	
	int update(Supplier supplier);
	
	Supplier selectByUsernameAndPassword(String email, String password);
	
	List<Supplier> selectAll();
	
	Integer deletById(Integer id);
	
	int updateimg(Supplier supplier);
	
	Supplier selectimg(String image);
}
