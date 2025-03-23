package web.member.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;
import web.member.service.MemberService;
import web.member.vo.Addresses;
import web.member.vo.Member;
import web.member.vo.MemberOther;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;


	// 資料檢查
	@Override
	public Member register(Member member) {
		String membername = member.getMembername();
		if (memberDao.selectByUsername(membername) != null) {
			System.out.println("此使用者名稱已被註冊");
			return member;
		}

		String email = member.getEmail();
		if (memberDao.selectByEmail(email) != null) {
			System.out.println("此Email已被註冊");
			return member;
		}

		String phone = member.getPhone();
		if (memberDao.selectByPhone(phone) != null) {
			System.out.println("此手機號碼已被註冊");
			return member;
		}

		if (membername == null || membername.length() < 2 || membername.length() > 50) {
			System.out.println(membername);
			System.out.println("使用者名稱長度須介於2 ~ 50");
			return member;
		}

		String password = member.getPassword();
		if (password == null || password.length() < 4 || password.length() > 12) {
			System.out.println(password);
			System.out.println("密碼長度須介於4 ~ 12");
			return member;
		}

		String cPassword = member.getCpassword();
		if (!Objects.equals(password, cPassword)) {
			System.out.println(cPassword);
			System.out.println("密碼與確認密碼不符合");
			return member;
		}

		int resultCount = memberDao.insert(member);

		if (resultCount < 0) {
			System.out.println("註冊成功");
			member.setMessage("註冊成功");
			member.setSuccessful(true);
			return member;
		} else {
			System.out.println("註冊發生錯誤");
			member.setMessage("註冊發生錯誤");
			member.setSuccessful(true);
			return member;
		}
	}

	@Override
	public Member edit(Member member) {
		final int resultCount = memberDao.update(member);
		member.setSuccessful(resultCount > 0);
		member.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		System.out.println(member.getMembername() + "  異動了資料");
		return member;
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

		member = memberDao.selectByUsernameAndPassword(email, password);
		if (member != null) {
			member.setSuccessful(true);
		}
		System.out.println(member.getMembername() + "  登入成功了");
		return member;
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
	public Member updateimg(Member member) {
		final int resultCount = memberDao.updateimg(member);
		member.setSuccessful(resultCount > 0);
		member.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		System.out.println(member.getMembername() + "  異動了照片");
		return member;
	}

	@Override
	public Member selectByMemberID(Integer memberid) {
		Member memberdate = memberDao.selectByMemberID(memberid);
		if (memberid != null) {
			Date dateOfBirth = memberdate.getDateofbirth();
			if (dateOfBirth != null) {
				String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth);
				memberdate.setStrdateofbirth(formattedDate);
			}
		}
		return memberdate;
	}

	@Override
	public Member forgetpassowrd(Member member) {
		String email = member.getEmail();
		if (memberDao.selectByEmail(email) != null) {
			System.out.println("有這個Email");
			memberDao.forgetpassword(email);
			return member;
		}
		return member;
	}

	@Override
	public Member editpassword(Member member) {
		final int resultCount = memberDao.updatepassword(member);
		member.setSuccessful(resultCount > 0);
		member.setMessage(resultCount > 0 ? "修改密碼成功" : "修改密碼失敗");
		System.out.println(member.getMemberid() + "  修改了密碼");
		return member;
	}

	@Override
	public Addresses selectaddress(Integer member_id) {
		return memberDao.selectaddress(member_id);
	}

	@Override
	public Member selectaddressAll(Integer member_id) {
		return memberDao.selectaddressAll(member_id);
	}

	@Override
	public Addresses upaddress(Addresses addresses) {
		int resultCount = memberDao.upaddress(addresses);
		
		if (resultCount > 0) {
			System.out.println("新增地址成功");
			addresses.setMessage("新增地址成功");
			addresses.setSuccessful(true);
			return addresses;
		} else {
			System.out.println("新增地址發生錯誤");
			addresses.setMessage("新增地址發生錯誤");
			addresses.setSuccessful(true);
			return addresses;
		}
	}

//	@Override
//	public Member addressedit(Member sessionMember, Member updateMember) {
//		boolean isUpdated = false;
//		// 判斷欄位有沒有被異動到&檢查欄位狀態值
//		if (updateMember.getZip_code() != null
//				&& !updateMember.getZip_code().equals(sessionMember.getZip_code())) {
//			sessionMember.setZip_code(updateMember.getZip_code());
//			isUpdated = true;
//		}
//		if (updateMember.getCity_id() != null && !updateMember.getCity_id().equals(sessionMember.getCity_id())) {
//			sessionMember.setCity_id(updateMember.getCity_id());
//			isUpdated = true;
//		}
//		if (updateMember.getDistrict_id() != null && !updateMember.getDistrict_id().equals(sessionMember.getDistrict_id())) {
//			sessionMember.setDistrict_id(updateMember.getDistrict_id());
//			isUpdated = true;
//		}
//		if (updateMember.getAddress() != null
//				&& !updateMember.getAddress().equals(sessionMember.getAddress())) {
//			sessionMember.setAddress(updateMember.getAddress());
//			isUpdated = true;
//		}
//		if (updateMember.getTag() != null && !updateMember.getTag().equals(sessionMember.getTag())) {
//			sessionMember.setTag(updateMember.getTag());
//			isUpdated = true;
//		}
//		
//		
//		if (isUpdated) {
//			int resultCount = memberDao.updateaddress(sessionMember);
//
//			sessionMember.setSuccessful(resultCount > 0);
//			sessionMember.setMessage(resultCount > 0 ? null : "發生錯誤，請聯繫專員");
//		} else {
//			sessionMember.setSuccessful(true); // 沒有異動的情況視為成功
//			sessionMember.setMessage("資料未改動");
//		}
//		return sessionMember;
//	}

//	@Override
//	public List<Member> selectcityAll() {
//		return memberDao.selectcityAll();
//	}
}
