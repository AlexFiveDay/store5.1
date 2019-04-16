package cn.tedu.store5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store5.entity.Cart;
import cn.tedu.store5.entity.CartVO;
import cn.tedu.store5.service.ICartService;
import cn.tedu.store5.util.ResponseResult;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController{
	@Autowired
	ICartService cartService;
	@RequestMapping("addToCart")
	private ResponseResult<Cart> addToCart(HttpSession session,Cart cart) {
		String username = session.getAttribute("username").toString();
		//客户端提交的cart只包含gid和num，我们需要重新设置cart的uid
		Integer uid = getUidFromSession(session);
		cart.setUid(uid);
		cartService.addToCart(username, cart);
		return new ResponseResult<Cart>(SUCCESS);
	}
	@RequestMapping("showCarts")
	private ResponseResult<List<CartVO>> getCartVOByUid(HttpSession session) {
		Integer uid = getUidFromSession(session);
		List<CartVO> data = cartService.getCartVOByUid(uid);
		return new ResponseResult<>(SUCCESS,data);
	}
	@RequestMapping("/{id}/add_num")
	private ResponseResult<Void> addCartNum(@PathVariable("id") Integer cid,HttpSession session) {
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		cartService.addCartNum(cid, uid, username);
		return new ResponseResult<>(SUCCESS);
	}
	@GetMapping("/check_list")
	private ResponseResult<List<CartVO>> getCartVOByCids(Integer[] cids){
		List<CartVO> data = cartService.getCartVOByCids(cids);
		return new ResponseResult<>(SUCCESS,data);
	}
}
