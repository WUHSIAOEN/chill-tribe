package web.activity.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import org.aspectj.org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import com.fasterxml.jackson.annotation.JsonFormat;

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
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;

    @Column(name = "ACTIVITY_PREFIX", insertable = false)
    private String activityPrefix;
    
    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @Column(name = "SUPPLIER_ID")
    private Integer supplierId;

//    @ManyToOne
//    @JoinColumn(name = "SUPPLIER_ID", insertable = false, updatable = false)
    @Transient
    private Supplier supplier;

    @Column(name = "CITY_ID")
    private Integer cityId;

    @ManyToOne
    @JoinColumn(name = "CITY_ID", insertable = false, updatable = false)
    @Transient
    private City city;

    @Column(name = "DISTRICT_ID")
    private Integer districtId;

    @ManyToOne
    @JoinColumn(name = "DISTRICT_ID", insertable = false, updatable = false)
    @Transient
    private District district;

    private String address;

    @Column(name = "UNIT_PRICE")
    private Integer unitPrice;

    @Column(name = "MIN_PARTICIPANTS")
    private Integer minParticipants;

    @Column(name = "MAX_PARTICIPANTS")
    private Integer maxParticipants;

    private String description;

    private String category;

    @OneToMany
    @JoinColumn(name = "ACTIVITY_ID")
//    @Transient
    private List<ActivityImage> activityImages;

//    @OneToMany
//    @JoinColumn(name = "COMMENT_ID", insertable = false, updatable = false)
    @Transient
    private List<Comment> comments;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "START_DATE_TIME")
    private Timestamp startDateTime;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "END_DATE_TIME")
    private Timestamp endDateTime;

    private Integer status;

    private String note;

    private String precaution;

    private Integer approved;

    @Column(name = "INVENTORY_COUNT")
    private Integer inventoryCount;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "INVENTORY_UPDATE_TIME")
    private Timestamp inventoryUpdateTime;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    private String latitude;

    private String longitude;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "TICKETS_ACTIVATE_TIME")
    private Timestamp ticketsActivateTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "TICKETS_EXPIRED_TIME")
    private Timestamp ticketsExpiredTime;
    
    @PrePersist
    public void prePersistStatus() {
        if (status == null) {
            status = 1; 
        }
        
        if (approved == null) {
        	approved = 1;
        }
        
        if (inventoryCount == null) {
        	inventoryCount = 0;
        }
    }

}
