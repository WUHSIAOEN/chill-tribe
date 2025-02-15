package web.activity.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;

import core.vo.City;
import core.vo.District;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.supplier.vo.Supplier;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Activities {
	private Integer activityId;
	private String activityName;
	// 要加上@OneToOne, @JoinColumn
	private Supplier supplier;
//	private Integer supplierId;
//	private String supplierName;
	// 要加上@OneToOne, @JoinColumn
	private City city;
//	private Integer cityId;
//	private String cityName;
	// 要加上@OneToOne, @JoinColumn
	private District district;
//	private Integer districtId;
//	private String districtName;
	private String address;
	private Integer unitPrice;
	private Integer minParticipants;
	private Integer maxParticipants;
	private String category;
	// 要加上@OneToMany, @JoinColumn
	private List<ActivityImage> activityImages;
	// 要加上@OneToMany, @JoinColumn
	private List<Comment> comments;
//  private List<String> imageName;
//	private List<String> imageBase64;
	private Timestamp startDateTime;
	private Timestamp endDateTime;
	private Integer status;
	private Boolean approved;
	private Integer inventoryCount;
	private Timestamp createTime;

//	public Integer getActivityId() {
//		return activityId;
//	}
//	public void setActivityId(Integer activityId) {
//		this.activityId = activityId;
//	}
//	public String getActivityName() {
//		return activityName;
//	}
//	public void setActivityName(String activityName) {
//		this.activityName = activityName;
//	}
//	public Supplier getSupplier() {
//		return supplier;
//	}
//	public void setSupplier(Supplier supplier) {
//		this.supplier = supplier;
//	}
//	public City getCity() {
//		return city;
//	}
//	public void setCity(City city) {
//		this.city = city;
//	}
//	public District getDistrict() {
//		return district;
//	}
//	public void setDistrict(District district) {
//		this.district = district;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public Integer getUnitPrice() {
//		return unitPrice;
//	}
//	public void setUnitPrice(Integer unitPrice) {
//		this.unitPrice = unitPrice;
//	}
//	public Integer getMinParticipants() {
//		return minParticipants;
//	}
//	public void setMinParticipants(Integer minParticipants) {
//		this.minParticipants = minParticipants;
//	}
//	public Integer getMaxParticipants() {
//		return maxParticipants;
//	}
//	public void setMaxParticipants(Integer maxParticipants) {
//		this.maxParticipants = maxParticipants;
//	}
//	public String getCategory() {
//		return category;
//	}
//	public void setCategory(String category) {
//		this.category = category;
//	}
//	public List<ActivityImage> getActivityImages() {
//		return activityImages;
//	}
//	public void setActivityImages(List<ActivityImage> activityImages) {
//		this.activityImages = activityImages;
//	}
//	public List<Comment> getComments() {
//		return comments;
//	}
//	public void setComments(List<Comment> comments) {
//		this.comments = comments;
//	}
//	public Timestamp getStartDateTime() {
//		return startDateTime;
//	}
//	public void setStartDateTime(Timestamp startDateTime) {
//		this.startDateTime = startDateTime;
//	}
//	public Timestamp getEndDateTime() {
//		return endDateTime;
//	}
//	public void setEndDateTime(Timestamp endDateTime) {
//		this.endDateTime = endDateTime;
//	}
//	public Integer getStatus() {
//		return status;
//	}
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//	public Boolean getApproved() {
//		return approved;
//	}
//	public void setApproved(Boolean approved) {
//		this.approved = approved;
//	}
//	public Integer getInventoryCount() {
//		return inventoryCount;
//	}
//	public void setInventoryCount(Integer inventoryCount) {
//		this.inventoryCount = inventoryCount;
//	}
//	public Timestamp getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(Timestamp createTime) {
//		this.createTime = createTime;
//	}
	

}
