package web.member.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Date;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 一般會員其他VO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberOther {
	// 會員五寶
	private String cpassword;
	// 會員基本資料
//	public String getcPassword() {
//		return cPassword;
//	}
//
//	public void setcPassword(String cPassword) {
//		this.cPassword = cPassword;
//	}

}
