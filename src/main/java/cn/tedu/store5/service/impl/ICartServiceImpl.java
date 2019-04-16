package cn.tedu.store5.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store5.entity.Cart;
import cn.tedu.store5.entity.CartVO;
import cn.tedu.store5.mapper.CartMapper;
import cn.tedu.store5.service.ICartService;
import cn.tedu.store5.service.ex.AccessDeniedException;
import cn.tedu.store5.service.ex.CartNotFoundException;
import cn.tedu.store5.service.ex.InsertException;
import cn.tedu.store5.service.ex.UpdateException;

@Service
public class ICartServiceImpl implements ICartService {
	@Autowired
	CartMapper cartMapper;

	@Override
	public void addToCart(String username, Cart cart) throws UpdateException, InsertException {
		Integer uid = cart.getUid();
		Long gid = cart.getGid();
		Cart result = findByUidAndGid(uid, gid);

		if (result == null) {
			// 日志信息设置
			cart.setCreateUser(username);
			cart.setCreateTime(new Date());
			cart.setModifiedUser(username);
			cart.setModifiedTime(new Date());
			// 插入信息
			insertCart(cart);
			return;
		}
		Integer num = cart.getNum() + result.getNum();
		Integer cid = result.getCid();
		updateNum(num, cid, username);
	}

	@Override
	public List<CartVO> getCartVOByUid(Integer uid) {
		return findCartVOByUid(uid);
	}

	@Override
	public void addCartNum(Integer cid, Integer uid, String username)
			throws AccessDeniedException, UpdateException, CartNotFoundException {
		// 根据cid查询购物车中该商品数据
		Cart result = cartMapper.findByCid(cid);
		// 判断查询商品是否存在
		if (result == null) {
			throw new CartNotFoundException("商品数据不存在异常，请刷新重试");
		}
		// 判断查询结果中的uid和登录的用户名uid是否匹配
		if (result.getUid() != uid) {
			throw new AccessDeniedException("用户名和购物车uid数据不匹配异常");
		}
		Integer num = result.getNum() + 1;
		updateNum(num, cid, username);
	}

	@Override
	public List<CartVO> getCartVOByCids(Integer[] cids){
		/*for (Integer cid : cids) {
			// 根据cid查询购物车中该商品数据
			Cart result = cartMapper.findByCid(cid);
			// 判断查询商品是否存在
			if (result == null) {
				throw new CartNotFoundException("商品数据不存在异常，请刷新重试");
			}
			// 判断查询结果中的uid和登录的用户名uid是否匹配
			if (result.getUid() != uid) {
				throw new AccessDeniedException("用户名和购物车uid数据不匹配异常");
			}
		}*/
		return cartMapper.findCartVOByCids(cids);
	}

	/**
	 * 更新用户购车商品数据
	 * 
	 * @param cart
	 *            需要添加的商品信息
	 * @param result
	 *            购物车中已存在的该商品信息
	 */
	private void updateNum(Integer num, Integer cid, String username) {
		Date now = new Date();
		Integer rows = cartMapper.updateNum(cid, num, username, now);
		if (rows != 1) {
			throw new UpdateException("购物车商品数量更新异常");
		}
	}

	/**
	 * 插入商品数据至购物车中
	 * 
	 * @param cart
	 */
	private void insertCart(Cart cart) {
		Integer rows = cartMapper.insertCart(cart);
		if (rows != 1) {
			throw new InsertException("购物车数据插入异常");
		}
	}

	/**
	 * 根据页面传来的商品数据查询购物车中是否存在该商品
	 * 
	 * @param uid
	 *            商品所属用户的uid
	 * @param gid
	 *            商品gid
	 * @return 购物车中该商品的数据
	 */
	private Cart findByUidAndGid(Integer uid, Long gid) {
		return cartMapper.findByUidAndGid(uid, gid);
	}

	/**
	 * 查找cart表中用户购物车中商品数据
	 * 
	 * @param uid
	 *            客户uid
	 * @return 该用户的商品列表s
	 */
	private List<CartVO> findCartVOByUid(Integer uid) {
		return cartMapper.findCartVOByUid(uid);
	}

}
