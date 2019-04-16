package cn.tedu.store5.service;

import java.util.List;

import cn.tedu.store5.entity.Cart;
import cn.tedu.store5.entity.CartVO;
import cn.tedu.store5.service.ex.AccessDeniedException;
import cn.tedu.store5.service.ex.CartNotFoundException;
import cn.tedu.store5.service.ex.InsertException;
import cn.tedu.store5.service.ex.UpdateException;

public interface ICartService {
	/**
	 * 向指定用户购物车中添加商品信息
	 * 
	 * @param username
	 *            用户名
	 * @param cart
	 *            添加的商品信息
	 * @throws InsertException
	 *             数据插入异常
	 * @throws UpdateException
	 *             数据更新异常
	 */
	void addToCart(String username, Cart cart) throws InsertException, UpdateException;

	/**
	 * 查找cart表中用户购物车中商品数据
	 * 
	 * @param uid
	 * @return
	 */
	List<CartVO> getCartVOByUid(Integer uid);

	/**
	 * 更改用户uid的购物车中该商品数量
	 * 
	 * @param cid
	 *            用户的数据cid
	 * @return
	 * @throws UpdateException
	 *             数据更新异常
	 * @throws AccessDeniedException
	 *             用户的id和购物车商品数据的uid不匹配异常
	 * @throws CartNotFoundException
	 *             购物车商品数据cid不存在异常
	 */
	void addCartNum(Integer cid, Integer uid, String username)
			throws UpdateException, AccessDeniedException, CartNotFoundException;

	/**
	 * 显示购物车中勾选的商品数据
	 * 
	 * @param cids
	 *            商品数据cids
	 * @return 该用户准备购买的商品列表
	 */
	List<CartVO> getCartVOByCids(Integer[] cids);
}
