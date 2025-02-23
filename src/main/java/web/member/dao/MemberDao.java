package web.member.dao;

import java.util.List;

import web.member.vo.Member;

public interface MemberDao {

	int insert(Member member);
	
	// SQL:select * from MEMBER where USERNAME = ?
	Member selectByUsername(String username);
	
	Member selectByEmail(String email);
	
	Member selectByPhone(String phone);
	
	int update(Member member);
	
	Member selectByUsernameAndPassword(Member member);
	
	List<Member> selectAll();
	
	Integer deletById(Integer id);
	
	int updateimg(Member member);
	
	Member selectimg(String photo_base64);
	
	Member selectaddress(Member member);
}
