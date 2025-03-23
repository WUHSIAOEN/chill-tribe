package web.supplier.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.member.vo.Member;
import web.supplier.dao.SupplierDao;
import web.supplier.dao.impl.SupplierDaoImpl;
import web.supplier.vo.Supplier;
import web.supplier.service.SupplierService;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService{
	@Autowired
	private SupplierDao supplierDao;
	
	public SupplierServiceImpl() throws NamingException {
		supplierDao = new SupplierDaoImpl();
	}
	
	// 資料檢查
	@Override
	public Supplier register(Supplier supplier) {
		String supplier_name = supplier.getSupplier_name();
		if (supplierDao.selectByUsername(supplier_name) != null) {
			System.out.println("此供應商名稱已被註冊");
			return supplier;
		}
		
		String email = supplier.getEmail();
		if (supplierDao.selectByEmail(email) != null) {
			System.out.println("此Email已被註冊");
			return supplier;
		}
		
		String phone = supplier.getPhone();
		if (supplierDao.selectByUsername(phone) != null) {
			System.out.println("此手機號碼已被註冊");
			return supplier;
		}
		
		if (supplier_name == null || supplier_name.length() < 2 || supplier_name.length() > 50) {
			System.out.println(supplier_name);
			System.out.println("供應商名稱長度須介於2 ~ 50");
			return supplier;
		}
		
		String password = supplier.getPassword();
		if (password == null || password.length() < 4 || password.length() > 12) {
			System.out.println(password);
			System.out.println("密碼長度須介於4 ~ 12");
			return supplier;
		}
		
		if (!Objects.equals(password, supplier.getCpassword())) {
			System.out.println(supplier.getCpassword());
			System.out.println("密碼與確認密碼不符合");
			return supplier;
		}
		
		int resultCount =  supplierDao.insert(supplier);
		
		if (resultCount < 0) {
			System.out.println("註冊成功");
			supplier.setMessage("註冊成功");
			supplier.setSuccessful(true);
			System.out.println(supplier.getSupplier_name() + "  恭喜註冊成功");
			return supplier;
		} else {
			System.out.println("註冊發生錯誤");
			supplier.setMessage("註冊發生錯誤");
			supplier.setSuccessful(true);
			System.out.println("笑死有人註冊失敗");
			return supplier;
		}
//		return resultCount > 0 ? null : "發生錯誤，請聯繫專員";
	}

	@Override
	public Supplier edit(Supplier supplier) {
		final int resultCount = supplierDao.update(supplier);
		supplier.setSuccessful(resultCount > 0);
		supplier.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		System.out.println(supplier.getSupplier_name() + "  異動了資料");
		return supplier;
//		String password = supplier.getPassword();
//		if (password != null && !password.isEmpty()  && (password.length() < 6 || password.length() > 12)) {
//			supplier.setSuccessful(false);
//			supplier.setMessage("密碼長度須介於6 ~ 12");
//			System.out.println(password);
//		}
//		
//		if (!Objects.equals(password, supplier.getcPassword())) {
//			System.out.println(supplier.getcPassword());
//			supplier.setSuccessful(false);
//			supplier.setMessage("密碼與確認密碼不符合");
//		}
		
		
//		int resultCount =  supplierDao.update(supplier);
//		supplier.setSuccessful(resultCount > 0);
//		supplier.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
//		return supplier;
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
		
		supplier = supplierDao.selectByUsernameAndPassword(email, password);
		if (supplier != null) {
			supplier.setSuccessful(true);
		}
		System.out.println(supplier.getSupplier_name() + "  登入成功");
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
		System.out.println(id + " 被刪除了~掰掰");
		return supplierDao.deletById(id) > 0;
	}

	@Override
	public Supplier updateimg(Supplier supplier) {
		int resultCount =  supplierDao.updateimg(supplier);
		
		supplier.setSuccessful(resultCount > 0);
		supplier.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
		System.out.println(supplier.getSupplier_id() + "  異動了照片");
		return supplier;
		
	}

	@Override
	public Supplier selectBySupplierID(Integer supplier_id) {
		Supplier supplierdate = supplierDao.selectBySupplierID(supplier_id);
		return supplierdate;
	}

	@Override
	public Supplier forgetpassowrd(Supplier supplier) {
		String email = supplier.getEmail();
		if (supplierDao.selectByEmail(email) != null) {
			System.out.println("有這個Email");
			supplierDao.forgetpassword(email);
			return supplier;
		}
		return supplier;
	}
	}

