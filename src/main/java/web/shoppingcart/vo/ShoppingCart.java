package web.shoppingcart.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import core.util.Core;
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
@Table(name = "shopping_cart_items")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class ShoppingCart extends Core{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SHOPPING_CART_ITEM_ID")
	private Integer shoppingCartItemId;
	
	@Column(name = "ACTIVITY_ID")
	private Integer activityId;
	
	@ManyToOne
	@JoinColumn(name = "ACTIVITY_ID", insertable = false, updatable = false)
	private Activities activity;
	
	private Integer quantity;
	
//	@Column(name = "TOTAL_PRICE")
//	private Integer totalPrice;
	
	@Column(name = "MEMBER_ID")
	private Integer memberId;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
	@JsonIgnore
	private Member member;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "ADDED_TIME", insertable = false)
	private Timestamp addedTime;
}
