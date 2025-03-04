package web.activity.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_images")
public class ActivityImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACTIVITY_IMAGE_ID")
	private Integer activityImageId;
	
	@Column(name = "ACTIVITY_ID")
	private Integer activityId;
	
	@Column(name = "IMAGE_NAME")
    private String imageName;
	
	@Column(name = "IMAGE_BASE64")
    private String imageBase64;
	
}
