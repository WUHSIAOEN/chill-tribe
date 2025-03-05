package web.order.vo;

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
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.vo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.activity.vo.Activities;
import web.member.vo.Member;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends Core{
	private static final long serialVersionUID = -1924879674331510853L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private Integer orderId;
	
	@Column(name = "ORDER_PREFIX")
	private String orderPrefix;
	
	@Column(name = "MEMBER_ID")
    private Integer memberId;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
    private Member member;
    
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;
    
    @ManyToOne
    @JoinColumn(name = "ACTIVITY_ID", insertable = false, updatable = false)
    private Activities activity;
    
    private Integer quantity;
    
    @Column(name = "ORDER_STATUS")
    private String orderStatus;
    
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;
    
    @Column(name = "ORDER_CONTACT")
    private String orderContact;
    
    @Column(name = "CONTACT_MAIL")
    private String contactMail;
    
    @Column(name = "CONTACT_PHONE")
    private String contactPhone;
    
    private String requirement;
    
    @Column(name = "MERCHANT_TRADE_NO")
    private String merchantTradeNo;
    
    @Column(name = "TRADE_NO")
    private String tradeNo;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "ORDER_UPDATE_DATETIME", insertable = false)
    private Timestamp orderUpdateDatetime;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "ORDER_CREATE_DATETIME", insertable = false)
    private Timestamp orderCreateDatetime;
    
    @OneToMany
	@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID", insertable = false, updatable = false)
    @Transient
    private List<Ticket> tickets;
    
    @PrePersist
    public void prePersistStatus() {
        if (orderPrefix == null) {
        	orderPrefix = "ORD"; 
        }
        
        if (orderStatus == null) {
        	orderStatus = "pending_payment";
        }
        
        if (paymentMethod == null) {
        	paymentMethod = "none";
        }

    }
	
}
