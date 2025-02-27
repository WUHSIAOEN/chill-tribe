package web.order.vo;

import java.sql.Timestamp;
import java.util.List;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.activity.vo.Activities;
import web.member.vo.Member;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends Core{
	private Integer orderId;
	private String orderPrefix;
    private Integer memberId;
//    要加上OneToOne, @JoinColum
    private Member member;
    private Integer activityId;
//    要加上OneToOne, @JoinColum
    private Activities activity;
    private Integer quantity;
    private String orderStatus;
    private String paymentMethod;
    private String orderContact;
    private String contactMail;
    private String contactPhone;
    private String requirement;
    private String merchantTradeNo;
    private String tradeNo;
    private Timestamp orderUpdateDatetime;
    private Timestamp orderCreateDatetime;
//	要加上@OneToMany, @JoinColumn
//	@JoinColumn(name = "ticket_id", insertable = false, updatable = false)
    private List<Ticket> tickets;
	
}
