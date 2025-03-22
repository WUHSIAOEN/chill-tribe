package web.supplier.service;

import java.util.List;

import web.member.vo.Member;
import web.supplier.vo.Supplier;

public interface SupplierService {

	Supplier register(Supplier supplier);
	
	Supplier edit(Supplier supplier);
	
	Supplier login(Supplier supplier);
	
	List<Supplier> findAll();
	
	boolean removeById(Integer id);
	
	Supplier updateimg(Supplier supplier);
	
	Supplier selectBySupplierID(Integer supplier_id);
	
	Supplier forgetpassowrd(Supplier supplier);
}
