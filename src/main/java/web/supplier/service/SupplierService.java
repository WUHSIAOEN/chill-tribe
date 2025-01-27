package web.supplier.service;

import java.util.List;

import web.supplier.vo.Supplier;

public interface SupplierService {

	String register(Supplier supplier);
	
	Supplier edit(Supplier supplier);
	
	Supplier login(Supplier supplier);
	
	List<Supplier> findAll();
	
	boolean removeById(Integer id);
}
