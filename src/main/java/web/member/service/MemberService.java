package web.member.service;

import java.util.List;

import web.member.vo.Member;

public interface MemberService {

	String register(Member member);
	
	Member edit(Member member);
	
	Member login(Member member);
	
	List<Member> findAll();
	
	boolean removeById(Integer id);
}
