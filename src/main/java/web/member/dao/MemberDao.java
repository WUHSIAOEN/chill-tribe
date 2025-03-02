package web.member.dao;

import java.util.List;

import web.member.vo.Member;

public interface MemberDao {

	int insert(Member member);
	
	Member selectByUsername(String username);
	
	Member selectByEmail(String email);
	
	Member selectByMemberID(Integer memberid);
	
	Member selectByPhone(String phone);
	
	int update(Member member);
	
	Member selectByUsernameAndPassword(String email, String password);
	
	List<Member> selectAll();
	
	Integer deletById(Integer id);
	
	int updateimg(Member member);
	
	Member selectimg(String photo_base64);
	
//	Member selectaddress(Member member);
	
//	List<Member> selectaddressAll(Integer member_id);
	
//	int upaddress(Member member);
	
//	int updateaddress(Member member);
	
//	List<Member> selectcityAll();
}
