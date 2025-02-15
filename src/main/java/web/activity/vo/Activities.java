package web.activity.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;

import core.util.Core;
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
public class Activities extends Core{
	private Integer activityId;
	private String activityName;
	// 要加上@OneToOne, @JoinColumn
	private Supplier supplier;
	// 要加上@OneToOne, @JoinColumn
	private City city;
//	private Integer cityId;
//	private String cityName;
	// 要加上@OneToOne, @JoinColumn
	private District district;
	private String address;
	private Integer unitPrice;
	private Integer minParticipants;
	private Integer maxParticipants;
	private String category;
	// 要加上@OneToMany, @JoinColumn
	private List<ActivityImage> activityImages;
	// 要加上@OneToMany, @JoinColumn
	private List<Comment> comments;
	private Timestamp startDateTime;
	private Timestamp endDateTime;
	private Integer status;
	private Boolean approved;
	private Integer inventoryCount;
	private Timestamp createTime;

}
