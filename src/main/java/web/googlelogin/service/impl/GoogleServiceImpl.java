package web.googlelogin.service.impl;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;

import core.vo.GoogleData;
import web.googlelogin.dao.GoogleDao;
import web.googlelogin.dao.impl.GoogleDaoImpl;
import web.googlelogin.service.GoogleService;
import web.member.vo.Member;

public class GoogleServiceImpl implements GoogleService {
	@Autowired
	private GoogleDao googleDao;

	public GoogleServiceImpl() throws NamingException {
		googleDao = new GoogleDaoImpl();
	}
	

	@Override
	public Member googlelogin(GoogleData gd) {
	    String googleloginemail = gd.getEmail();
		Member googleloginmember = googleDao.selectBygoogleEmail(gd);
		System.out.println("googleloginmember："+googleloginmember);
		System.out.println("googleloginemail："+googleloginemail);
		System.out.println("googlelogin");
	    
	    if (googleloginmember != null) {
	    	System.out.println(gd.getEmail() + "這個Google帳號可以登入");
	        return googleloginmember;
	    }else {
	    	System.out.println(gd.getEmail() +"這個Google帳號不能登入");
	        return null;
	    }
	}

}
