package web.member.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.sql.Date;

import core.util.Core;
import core.vo.City;
import core.vo.District;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 一般會員地址VO
@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Addresses extends Core {

	// 地址
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer address_id;
	private Integer city_id;
//	private String city_name;
	private Integer district_id;
//	private String district_name;
//	private Integer zip_code;
	private String address;
	private Integer address_default;
	private String tag;
	
	
//	@ManyToOne
//    @JoinColumn(name = "member_id", insertable = false, updatable = false)
//    private Member member;
//	
//	@ManyToOne
//    @JoinColumn(name = "city_id", insertable = false, updatable = false)
//    private City city;
//
//    @ManyToOne
//    @JoinColumn(name = "district_id", insertable = false, updatable = false)
//    private District district;
//    
    @Column(name = "member_id")
    private Integer memberid;
	

//	public City getCity() {
//		return city;
//	}
//
//	public void setCity(City city) {
//		this.city = city;
//	}
//
//	public District getDistrict() {
//		return district;
//	}
//
//	public void setDistrict(District district) {
//		this.district = district;
//	}
//
	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberd(Integer memberid) {
		this.memberid = memberid;
	}

	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public Integer getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAddress_default() {
		return address_default;
	}

	public void setAddress_default(Integer address_default) {
		this.address_default = address_default;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
//	public String getCity_name() {
//		return city_name;
//	}

//	public void setCity_name(String city_name) {
//		this.city_name = city_name;
//	}

//	public String getDistrict_name() {
//		return district_name;
//	}

//	public void setDistrict_name(String district_name) {
//		this.district_name = district_name;
//	}

//	public Integer getZip_code() {
//		return zip_code;
//	}

//	public void setZip_code(Integer zip_code) {
//		this.zip_code = zip_code;
//	}

}
