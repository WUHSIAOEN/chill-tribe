package web.activity.vo;

import java.sql.Timestamp;

public class Activity {
	private Integer activityId; // 活動編號
	private String activityPrefix; // 活動代碼前綴
	private String activityName; // 活動名稱
	private Integer supplierId; // 供應商編號
	private String address; // 活動地址
	private Integer unitPrice; // 單位價格
	private Integer minParticipants; // 最小參加人數
	private Integer maxParticipants; // 最大參加人數
	private String description; // 活動描述
	private String precaution;
	private String category; // 活動類別
	private Timestamp startDateTime; // 開始時間
	private Timestamp endDateTime; // 結束時間
	private Integer status; // 狀態 (0: 暫定 1: 確定)
	private String note; // 備註
	private Integer approved; // 審核狀態 (0: 未審核 1: 已通過)
	private String cityId; // 城市
	private String district; // 地區
	private Integer inventoryCount; // 庫存數量
	private Timestamp inventoryUpdateTime; // 庫存更新時間
	private Timestamp createdTime; // 建立時間
	private String latitude; // 緯度
	private String longitude; // 經度
	private Timestamp ticketsActivateTime; // 票券啟動時間
	private Timestamp ticketsExpiredTime; // 票券過期時間

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

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
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
}
