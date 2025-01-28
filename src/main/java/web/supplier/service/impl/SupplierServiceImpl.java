package web.supplier.service.impl;

import java.util.List;
import java.util.Objects;

import javax.naming.NamingException;

import web.supplier.dao.SupplierDao;
import web.supplier.dao.impl.SupplierDaoImpl;
import web.supplier.vo.Supplier;
import web.supplier.service.SupplierService;

public class SupplierServiceImpl implements SupplierService{
	private SupplierDao supplierDao;
	
	public SupplierServiceImpl() throws NamingException {
		supplierDao = new SupplierDaoImpl();
	}
	
	// 資料檢查
	@Override
	public String register(Supplier supplier) {
		String supplier_name = supplier.getSupplier_name();
		if (supplierDao.selectByUsername(supplier_name) != null) {
			return "此供應商名稱已被註冊";
		}
		
		String email = supplier.getEmail();
		if (supplierDao.selectByEmail(email) != null) {
			return "此Email已被註冊";
		}
		
		String phone = supplier.getPhone();
		if (supplierDao.selectByUsername(phone) != null) {
			return "此手機號碼已被註冊";
		}
		
		if (supplier_name == null || supplier_name.length() < 2 || supplier_name.length() > 50) {
			System.out.println(supplier_name);
			return "供應商名稱長度須介於2 ~ 50";
		}
		
		String password = supplier.getPassword();
		if (password == null || password.length() < 4 || password.length() > 12) {
			System.out.println(password);
			return "密碼長度須介於4 ~ 12";
		}
		
		if (!Objects.equals(password, supplier.getcPassword())) {
			System.out.println(supplier.getcPassword());
			return "密碼與確認密碼不符合";
		}
		
		int resultCount =  supplierDao.insert(supplier);
		
		return resultCount > 0 ? null : "發生錯誤，請聯繫專員";
	}

	@Override
	public Supplier edit(Supplier supplier) {
		String password = supplier.getPassword();
		if (password != null && !password.isEmpty()  && (password.length() < 6 || password.length() > 12)) {
			supplier.setSuccessful(false);
			supplier.setMessage("密碼長度須介於6 ~ 12");
			System.out.println(password);
		}
		
		if (!Objects.equals(password, supplier.getcPassword())) {
			System.out.println(supplier.getcPassword());
			supplier.setSuccessful(false);
			supplier.setMessage("密碼與確認密碼不符合");
		}
		
		
		int resultCount =  supplierDao.update(supplier);
		supplier.setSuccessful(resultCount > 0);
		supplier.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
		return supplier;
	}

	@Override
	public Supplier login(Supplier supplier) {
		String email = supplier.getEmail();
		if (email == null || email.isEmpty()) {
			supplier.setSuccessful(false);
			supplier.setMessage("Email必須輸入");
			return supplier;
		}
		
		String password = supplier.getPassword();
		if (password == null || password.isEmpty()) {
			supplier.setSuccessful(false);
			supplier.setMessage("密碼必須輸入");
			return supplier;
		}
		
		supplier = supplierDao.selectByUsernameAndPassword(supplier);
		if (supplier != null) {
			supplier.setSuccessful(true);
		}
		return supplier;
//		return memberDao.selectByUsernameAndPassword(member);			
	}
	
	
		

	@Override
	public List<Supplier> findAll() {
		return supplierDao.selectAll();
	}

	@Override
	public boolean removeById(Integer id) {
		if (id == null) {
			return false;
		}
		return supplierDao.deletById(id) > 0;
	}
}
