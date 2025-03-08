package web.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.shoppingcart.service.MemberShoppingCartService;
import web.shoppingcart.vo.ShoppingCart;

@RestController
@RequestMapping("shoppingcart/list")
public class MemberShoppingCartController {
	
	@Autowired
	private MemberShoppingCartService service;
	
//	參數是member_id
	@GetMapping("{id}")
	public List<ShoppingCart> listMemberShoppingCartItems(@PathVariable Integer id) {
		List<ShoppingCart> shoppingCarts = service.getMemberShopppingCartItems(id);
//		如果是空值的判斷要再調整
		if (shoppingCarts == null) {
			System.out.println("沒東西");
		}

		return shoppingCarts;
	}
}
