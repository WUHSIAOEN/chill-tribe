package web.order.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.util.Core;
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
public class Ticket extends Core{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_ID")
	private Integer ticketId;
	
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
	
	@Column(name = "TICKET_CREATE_DATETIME")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp ticketUsedDatetime;
}
