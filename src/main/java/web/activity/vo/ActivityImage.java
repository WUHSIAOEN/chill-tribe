package web.activity.vo;

public class ActivityImage {
	
	private Integer activityImageId;
	private Integer activityId;
    private String imageName;
    private String imageBase64;
	
    public Integer getActivityImageId() {
		return activityImageId;
	}
    
	public void setActivityImageId(Integer activityImageId) {
		this.activityImageId = activityImageId;
	}
	
	public Integer getActivityId() {
		return activityId;
	}
	
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String getImageBase64() {
		return imageBase64;
	}
	
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
}
