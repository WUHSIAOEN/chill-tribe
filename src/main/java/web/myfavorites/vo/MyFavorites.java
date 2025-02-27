package web.myfavorites.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyFavorites extends Core{
	private Integer activity_id;
	private Integer member_id;
	private Timestamp added_time;
	@OneToOne
	@JoinColumn(name = "activity_id", insertable = false, updatable = false)
	private Activities activities;
	
	private List<ActivityImage> activityImages;
	
}
