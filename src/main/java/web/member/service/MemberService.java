package web.member.service;

import java.util.List;

import web.member.vo.Member;

public interface MemberService {

	String register(Member member);
	
	Member edit(Member sessionMember, Member updateMember);
	
	Member login(Member member);
	
	List<Member> findAll();
	
	boolean removeById(Integer id);
	
	Member updateimg(Member sessionMember, Member updateMember);
	
	Member selectaddress(Member member);
	
	List<Member> selectaddressAll(Integer member_id);
	
	String upaddress(Member member);
	
}
