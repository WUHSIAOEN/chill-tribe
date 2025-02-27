package web.member.service.impl;

import java.util.List;
import java.util.Objects;

import javax.naming.NamingException;

import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;
import web.member.service.MemberService;
import web.member.vo.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDao memberDao;
	
	public MemberServiceImpl() throws NamingException {
		memberDao = new MemberDaoImpl();
	}
	
	// 資料檢查
	@Override
	public String register(Member member) {
		String member_name = member.getMember_name();
		if (memberDao.selectByUsername(member_name) != null) {
			return "此使用者名稱已被註冊";
		}
		
		String email = member.getEmail();
		if (memberDao.selectByEmail(email) != null) {
			return "此Email已被註冊";
		}
		
		String phone = member.getPhone();
		if (memberDao.selectByUsername(phone) != null) {
			return "此手機號碼已被註冊";
		}
		
		if (member_name == null || member_name.length() < 2 || member_name.length() > 50) {
			System.out.println(member_name);
			return "使用者名稱長度須介於2 ~ 50";
		}
		
		String password = member.getPassword();
		if (password == null || password.length() < 4 || password.length() > 12) {
			System.out.println(password);
			return "密碼長度須介於4 ~ 12";
		}
		
		if (!Objects.equals(password, member.getcPassword())) {
			System.out.println(member.getcPassword());
			return "密碼與確認密碼不符合";
		}
		
		int resultCount =  memberDao.insert(member);
		
		return resultCount > 0 ? null : "發生錯誤，請聯繫專員";
	}

	@Override
	public Member edit(Member sessionMember, Member updateMember) {
		
		boolean isUpdated = false;
		// 判斷欄位有沒有被異動到&檢查欄位狀態值
        if (updateMember.getMember_name() != null && !updateMember.getMember_name().equals(sessionMember.getMember_name())) {
            sessionMember.setMember_name(updateMember.getMember_name());
            isUpdated = true;
        }
        if (updateMember.getEmail() != null && !updateMember.getEmail().equals(sessionMember.getEmail())) {
            sessionMember.setEmail(updateMember.getEmail());
            isUpdated = true;
        }
        if (updateMember.getPhone() != null && !updateMember.getPhone().equals(sessionMember.getPhone())) {
            sessionMember.setPhone(updateMember.getPhone());
            isUpdated = true;
        }
        if (updateMember.getDate_of_birth() != null && !updateMember.getDate_of_birth().equals(sessionMember.getDate_of_birth())) {
            sessionMember.setDate_of_birth(updateMember.getDate_of_birth());
            isUpdated = true;
        }
        if (updateMember.getGender() != null && !updateMember.getGender().equals(sessionMember.getGender())) {
            sessionMember.setGender(updateMember.getGender());
            isUpdated = true;
        }
        if (updateMember.getPhoto_base64() != null && !updateMember.getPhoto_base64().equals(sessionMember.getPhoto_base64())) {
            sessionMember.setPhoto_base64(updateMember.getPhoto_base64());
            isUpdated = true;
        }
		
//		String password = member.getPassword();
//		if (password != null && !password.isEmpty()  && (password.length() < 6 || password.length() > 12)) {
//			member.setSuccessful(false);
//			member.setMessage("密碼長度須介於6 ~ 12");
//			System.out.println(password);
//		}
//		
//		if (!Objects.equals(password, member.getcPassword())) {
//			System.out.println(member.getcPassword());
//			member.setSuccessful(false);
//			member.setMessage("密碼與確認密碼不符合");
//		}
		
//		String nicfkname = member.getNickname();
//		if (nicfkname == null || nicfkname.length() < 1 || nicfkname.length() > 20) {
//			System.out.println(nicfkname);
//			member.setSuccessful(false);
//			member.setMessage("匿名長度須介於1 ~ 20");
//		}
		
//		String member_name = member.getMember_name();
//		if (member_name == null || member_name.length() < 1 || member_name.length() > 20) {
//			System.out.println(member_name);
//			member.setSuccessful(false);
//			member.setMessage("匿名長度須介於1 ~ 20");
//		}
        
        if (isUpdated) {
            int resultCount = memberDao.update(sessionMember);
            
            sessionMember.setSuccessful(resultCount > 0);
            sessionMember.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
        } else {
            sessionMember.setSuccessful(true); // 沒有異動的情況視為成功
            sessionMember.setMessage("資料未改動");
        }

        return sessionMember;
		
//		int resultCount =  memberDao.update(member);
//		member.setSuccessful(resultCount > 0);
//		member.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
//		return member;
	}

	@Override
	public Member login(Member member) {
		String email = member.getEmail();
		if (email == null || email.isEmpty()) {
			member.setSuccessful(false);
			member.setMessage("Email必須輸入");
			return member;
		}
		
		String password = member.getPassword();
		if (password == null || password.isEmpty()) {
			member.setSuccessful(false);
			member.setMessage("密碼必須輸入");
			return member;
		}
		
		member = memberDao.selectByUsernameAndPassword(member);
		if (member != null) {
			member.setSuccessful(true);
		}
		return member;
//		return memberDao.selectByUsernameAndPassword(member);			
	}
	
	
		

	@Override
	public List<Member> findAll() {
		return memberDao.selectAll();
	}

	@Override
	public boolean removeById(Integer id) {
		if (id == null) {
			return false;
		}
		return memberDao.deletById(id) > 0;
	}

	@Override
	public Member updateimg(Member sessionMember, Member updateMember) {
		
		boolean isUpdated = false;

        if (updateMember.getMember_name() != null && !updateMember.getMember_name().equals(sessionMember.getMember_name())) {
            sessionMember.setMember_name(updateMember.getMember_name());
            isUpdated = true;
        }
        if (updateMember.getEmail() != null && !updateMember.getEmail().equals(sessionMember.getEmail())) {
            sessionMember.setEmail(updateMember.getEmail());
            isUpdated = true;
        }
        if (updateMember.getPhone() != null && !updateMember.getPhone().equals(sessionMember.getPhone())) {
            sessionMember.setPhone(updateMember.getPhone());
            isUpdated = true;
        }
        if (updateMember.getDate_of_birth() != null && !updateMember.getDate_of_birth().equals(sessionMember.getDate_of_birth())) {
            sessionMember.setDate_of_birth(updateMember.getDate_of_birth());
            isUpdated = true;
        }
        if (updateMember.getGender() != null && !updateMember.getGender().equals(sessionMember.getGender())) {
            sessionMember.setGender(updateMember.getGender());
            isUpdated = true;
        }
        if (updateMember.getPhoto_base64() != null && !updateMember.getPhoto_base64().equals(sessionMember.getPhoto_base64())) {
            sessionMember.setPhoto_base64(updateMember.getPhoto_base64());
            isUpdated = true;
        }
        
        
        if (isUpdated) {
            int resultCount = memberDao.updateimg(sessionMember);
            
            sessionMember.setSuccessful(resultCount > 0);
            sessionMember.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
        } else {
            sessionMember.setSuccessful(true); // 沒有異動的情況視為成功
            sessionMember.setMessage("資料未改動");
        }

        return sessionMember;
		
//		
//		int resultCount =  memberDao.updateimg(member);
//		
//		member.setSuccessful(resultCount > 0);
//		member.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
//		return member;
		
	}

	@Override
	public Member selectaddress(Member member) {
//		int resultCount =  memberDao.selectaddress(member);
//		
//		member.setSuccessful(resultCount > 0);
//		member.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
		return member;
	}
}
