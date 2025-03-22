package web.order.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.vo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.activity.vo.Activities;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket extends Core{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_ID")
	private Integer ticketId;
	
	@Column(name = "TICKET_PREFIX")
	private String ticketPrefix;
	
	@Column(name = "ORDER_ID")
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
	private Orders order;
	
	@Column(name = "ACTIVITY_ID")
	private Integer activityId;
	
	@ManyToOne
	@JoinColumn(name = "ACTIVITY_ID", insertable = false, updatable = false)
	private Activities activity;
	
	@Column(name = "TICKET_STATUS")
	private String ticketStatus;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "TICKET_CREATE_DATETIME", insertable = false)
	private Timestamp ticketCreateDatetime;
	
	@PrePersist
    public void prePersistStatus() {
        if (ticketPrefix == null) {
        	ticketPrefix = "TKT"; 
        }
        
        if (ticketStatus == null) {
        	ticketStatus = "not_used";
        }

    }
}
