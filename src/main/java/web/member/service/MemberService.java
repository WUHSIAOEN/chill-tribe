package web.member.service;

import java.util.List;

import web.member.vo.Addresses;
import web.member.vo.Member;

public interface MemberService {

	Member register(Member member);
	
	Member edit(Member member);
	
	Member login(Member member);
	
	List<Member> findAll();
	
	boolean removeById(Integer id);
	
	Member selectByMemberID(Integer memberid);
	
	Member updateimg(Member member);
	
	Member forgetpassowrd(Member member);
	
	Member editpassword(Member member);
	
	Addresses selectaddress(Integer member_id);
	
//	List<Member> selectaddressAll(Integer member_id);
	
	Member selectaddressAll(Integer member_id);
	
//	String upaddress(Member member);
	
//	Member upaddress(Member member);
	
	Addresses upaddress(Addresses addresses);
	
//	Member addressedit(Member sessionMember, Member updateMember);
	
	Member addressedit(Member member);
	
//	List<Member> selectcityAll();
	
}
