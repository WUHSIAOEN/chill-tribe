package web.member.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 一般會員VO
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBERS")
public class Member extends Core {

	// 會員五寶
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "MEMBER_ID")
	private Integer memberid;
	@Column(name = "MEMBER_NAME")
	private String membername;
	private String email;
	private String password;
//	private String cPassword;
	// 會員基本資料
	private String phone;
	@Column(name = "ID_CARD")
	private String idcard;
	private String gender;
	@Column(name = "DATE_OF_BIRTH")
	private Date dateofbirth;
	@Column(name = "PHOTO_BASE64")
	private String photobase64;
//	// 地址
//	private List<Addresses> addresses;
//	private Integer address_id;
//	private Integer city_id;
//	private String city_name;
//	private Integer district_id;
//	private String district_name;
//	private Integer zip_code;
//	private String address;
//	private Integer address_default;
//	private String tag;
	// 年薪500萬工程師查日期用
//	private Timestamp registration_date;
//	private Timestamp lastUpdatedDate;

//	public Integer getMember_id() {
//		return member_id;
//	}
//
//	public void setMember_id(Integer member_id) {
//		this.member_id = member_id;
//	}
//
//	public String getMember_name() {
//		return member_name;
//	}
//
//	public void setMember_name(String member_name) {
//		this.member_name = member_name;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getcPassword() {
//		return cPassword;
//	}
//
//	public void setcPassword(String cPassword) {
//		this.cPassword = cPassword;
//	}
//
//	public String getId_card() {
//		return id_card;
//	}
//
//	public void setId_card(String id_card) {
//		this.id_card = id_card;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public String getCreator() {
//		return creator;
//	}
//
//	public void setCreator(String creator) {
//		this.creator = creator;
//	}
//
//	public Timestamp getRegistration_date() {
//		return registration_date;
//	}
//
//	public void setRegistration_date(Timestamp registration_date) {
//		this.registration_date = registration_date;
//	}
//
//	public String getUpdater() {
//		return updater;
//	}
//
//	public void setUpdater(String updater) {
//		this.updater = updater;
//	}
//
//	public Timestamp getLastUpdatedDate() {
//		return lastUpdatedDate;
//	}
//
//	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
//		this.lastUpdatedDate = lastUpdatedDate;
//	}
//
//	public Date getDate_of_birth() {
//		return date_of_birth;
//	}
//
//	public void setDate_of_birth(Date date_of_birth) {
//		this.date_of_birth = date_of_birth;
//	}
//
//	public String getPhoto_base64() {
//		return photo_base64;
//	}
//
//	public void setPhoto_base64(String photo_base64) {
//		this.photo_base64 = photo_base64;
//	}
//
//	public Integer getAddress_id() {
//		return address_id;
//	}
//
//	public void setAddress_id(Integer address_id) {
//		this.address_id = address_id;
//	}
//
//	public Integer getCity_id() {
//		return city_id;
//	}
//
//	public void setCity_id(Integer city_id) {
//		this.city_id = city_id;
//	}
//
//	public Integer getDistrict_id() {
//		return district_id;
//	}
//
//	public void setDistrict_id(Integer district_id) {
//		this.district_id = district_id;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public Integer getAddress_default() {
//		return address_default;
//	}
//
//	public void setAddress_default(Integer address_default) {
//		this.address_default = address_default;
//	}
//
//	public String getTag() {
//		return tag;
//	}
//
//	public void setTag(String tag) {
//		this.tag = tag;
//	}
//	
//	public String getCity_name() {
//		return city_name;
//	}
//
//	public void setCity_name(String city_name) {
//		this.city_name = city_name;
//	}
//
//	public String getDistrict_name() {
//		return district_name;
//	}
//
//	public void setDistrict_name(String district_name) {
//		this.district_name = district_name;
//	}
//
//	public Integer getZip_code() {
//		return zip_code;
//	}
//
//	public void setZip_code(Integer zip_code) {
//		this.zip_code = zip_code;
//	}
//	
//	public List<Addresses> getAddresses() {
//		return addresses;
//	}
//
//	public void setAddresses(List<Addresses> addresses) {
//		this.addresses = addresses;
//	}

}
