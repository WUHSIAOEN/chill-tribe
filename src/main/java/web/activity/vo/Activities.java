package web.activity.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	@Column(name = "activity_id")
	private Integer activityId;
	private String activityPrefix;
	private String activityName;
	private Integer supplierId;
	@OneToOne
	@JoinColumn(name = "supplier_id", insertable = false, updatable = false)
	private Supplier supplier;
	private Integer city_id;
	@OneToOne
	@JoinColumn(name = "city_id", insertable = false, updatable = false)
	private City city;
	private Integer district_id;
	@OneToOne
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private District district;
	private String address;
	private Integer unitPrice;
	private Integer minParticipants;
	private Integer maxParticipants;
	private String description;
	private String category;
	@OneToMany
	@JoinColumn(name = "activity_image_id", insertable = false, updatable = false)
	private List<ActivityImage> activityImages;
	@OneToMany
	@JoinColumn(name = "activity_image_id", insertable = false, updatable = false)
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
