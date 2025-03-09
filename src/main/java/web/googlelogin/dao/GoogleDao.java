package web.googlelogin.dao;

import core.vo.GoogleData;
import web.member.vo.Member;

public interface GoogleDao {

	
	Member selectBygoogleEmail(GoogleData gd);
	
	
}
