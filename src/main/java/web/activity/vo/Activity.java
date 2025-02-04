package web.activity.vo;

import java.sql.Timestamp;

public class Activity {
	private Integer activityId;
	private String activityPrefix;
    private String activityName;
    private Integer supplierId;
    private Integer cityId;
    private Integer districtId;
    private String address;
    private Integer unitPrice;
    private Integer minParticipants;
    private Integer maxParticipants;
    private String description;
    private String precaution;
    private String category;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private Integer status;
    private String note;
    private Boolean approved;
    private Integer inventoryCount;
    private String latitude;
    private String longitude;
    private Timestamp ticketsActivateTime;
    private Timestamp ticketsExpiredTime;
    private Timestamp inventoryUpdateTime;
    private Timestamp createTime;

	// Getter and Setter methods

	public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityPrefix() {
        return activityPrefix;
    }

    public void setActivityPrefix(String activityPrefix) {
        this.activityPrefix = activityPrefix;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPrecaution() {
		return precaution;
	}

	public void setPrecaution(String precaution) {
		this.precaution = precaution;
	}

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(Integer minParticipants) {
        this.minParticipants = minParticipants;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(Integer inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public Timestamp getInventoryUpdateTime() {
        return inventoryUpdateTime;
    }

    public void setInventoryUpdateTime(Timestamp inventoryUpdateTime) {
        this.inventoryUpdateTime = inventoryUpdateTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Timestamp getTicketsActivateTime() {
        return ticketsActivateTime;
    }

    public void setTicketsActivateTime(Timestamp ticketsActivateTime) {
        this.ticketsActivateTime = ticketsActivateTime;
    }

    public Timestamp getTicketsExpiredTime() {
        return ticketsExpiredTime;
    }

    public void setTicketsExpiredTime(Timestamp ticketsExpiredTime) {
        this.ticketsExpiredTime = ticketsExpiredTime;
    }

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
