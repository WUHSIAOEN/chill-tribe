package web.activity.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import org.aspectj.org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

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
	private String activityPrefix;
	private String activityName;
	private Integer supplierId;
//	要加上@OneToOne, @JoinColumn
//	@JoinColumn(name = "supplier_id", insertable = false, updatable = false)
	private Supplier supplier;
	private Integer city_id;
//	要加上@OneToOne, @JoinColumn
//	@JoinColumn(name = "city_id", insertable = false, updatable = false)
	private City city;
	private Integer district_id;
//	要加上@OneToOne, @JoinColumn
//	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private District district;
	private String address;
	private Integer unitPrice;
	private Integer minParticipants;
	private Integer maxParticipants;
	private String description;
	private String category;
//	要加上@OneToMany, @JoinColumn
//	@JoinColumn(name = "activity_image_id", insertable = false, updatable = false)
	private List<ActivityImage> activityImages;
//	要加上@OneToMany, @JoinColumn
//	@JoinColumn(name = "activity_image_id", insertable = false, updatable = false)
	private List<Comment> comments;
	private Timestamp startDateTime;
	private Timestamp endDateTime;
	private Integer status;
	private String note;
	private String precaution;
	private Integer approved;
	private Integer inventoryCount;
	private Timestamp inventoryUpdateTime;
	private Timestamp createTime;
	private String latitude;
	private String longitude;
	private Timestamp ticketsActivateTime;
	private Timestamp ticketsExpiredTime;

}
