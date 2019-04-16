package cn.tedu.store5.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store5.entity.Cart;
import cn.tedu.store5.entity.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTestcase {
	@Autowired
	ICartService cartService;
	/**
	 * 测试热销商品的筛选
	 */
	@Test
	public void cartTest() {
		
		String username = "测试";
		Cart cart = new Cart();
		cart.setUid(18);
		cart.setGid((long) 10000042);
		cart.setNum(3);
		cart.setCid(2);
		cartService.addToCart(username, cart);
	}
	@Test
	public void showCartTableTest() {
		Integer uid = 17;
		List<CartVO> carts = cartService.getCartVOByUid(uid );
		for (CartVO cartVO : carts) {
			System.err.println(cartVO);
		}
	}
	@Test
	public void increatmentTest() {
		 //addCartNum(Integer cid,Integer uid,String username)
		Integer uid = 17;
		Integer cid = 1;
		String username = "小狗";
		cartService.addCartNum(cid, uid, username);
	}
	@Test
	public void showCartsOrderTest() {
		Integer[] cids = {1,3};
		List<CartVO> carts = cartService.getCartVOByCids(cids);
		for (CartVO cartVO : carts) {
			System.err.println(cartVO);
		}
	}
	
}
