package web.shoppingcart.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.activity.vo.ActivityImage;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ShoppingCart extends Core{
	private Integer shopping_cart_item_id;
	private Integer activity_id;
	private Integer quantity;
	private Integer total_price;
	private Integer member_id;
	private Timestamp added_time;
	
	private String activity_name;
	private Integer unit_price;
	private List<ActivityImage> activityImages;
}
