package web.shoppingcart.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ShoppingCart extends Core{
	private Integer Shopping_Cart_Item_Id;
	private Integer Activity_Id;
	private Integer Quantity;
	private Integer Total_Price;
	private Integer Member_Id;
	private Timestamp Added_Time;
}
