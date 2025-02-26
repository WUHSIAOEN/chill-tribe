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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activity_id")
	private Integer activityId;
	@Column(name = "activity_prefix")
	private String activityPrefix;
	@Column(name = "activity_name")
	private String activityName;
	@Column(name = "supplier_id")
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
	@Column(name = "unit_price")
	private Integer unitPrice;
	@Column(name = "min_participants")
	private Integer minParticipants;
	@Column(name = "max_participants")
	private Integer maxParticipants;
	private String description;
	private String category;
	@OneToMany
	@JoinColumn(name = "activity_image_id", insertable = false, updatable = false)
	private List<ActivityImage> activityImages;
	@OneToMany
	@JoinColumn(name = "activity_id", insertable = false, updatable = false)
	private List<Comment> comments;
	@Column(name = "start_date_time")
	private Timestamp startDateTime;
	@Column(name = "end_date_time")
	private Timestamp endDateTime;
	private Integer status;
	private String note;
	private String precaution;
	private Integer approved;
	@Column(name = "inventory_count")
	private Integer inventoryCount;
	@Column(name = "inventory_update_time")
	private Timestamp inventoryUpdateTime;
	@Column(name = "create_time")
	private Timestamp createTime;
	private String latitude;
	private String longitude;
	@Column(name = "tickets_activate_time")
	private Timestamp ticketsActivateTime;
	@Column(name = "tickets_expired_time")
	private Timestamp ticketsExpiredTime;

}
