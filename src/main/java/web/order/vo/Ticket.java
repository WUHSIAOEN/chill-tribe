package web.order.vo;

import java.sql.Timestamp;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends Core{
	private Integer ticketId;
	private Integer orderId;
	private Integer activityId;
	private String ticketStatus;
	private Timestamp ticketUsedDatetime;
}
