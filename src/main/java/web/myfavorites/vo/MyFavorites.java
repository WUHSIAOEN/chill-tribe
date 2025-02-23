package web.myfavorites.vo;

import java.util.List;

import javax.persistence.Entity;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.activity.vo.ActivityImage;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyFavorites extends Core{
	private Integer activity_id;
	private Integer member_id;
	private String activity_name;
	private String category;
	private List<ActivityImage> activityImages;
	
}
