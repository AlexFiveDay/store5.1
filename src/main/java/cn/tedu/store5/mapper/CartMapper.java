package cn.tedu.store5.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store5.entity.Cart;
import cn.tedu.store5.entity.CartVO;

public interface CartMapper {
	/**
	 * 插入购物车中新商品的数据
	 * @param uid 归属用户的id
	 * @param gid 商品的id
	 * @return 插入数据的条数
	 */
	Integer insertCart(Cart cart);
	/**
	 * 获取某用户在购物车中添加的指定的商品的数据
	 * @param uid
	 * @param gid
	 * @return 没有，则返回null
	 */
	Cart findByUidAndGid(@Param("uid")Integer uid,@Param("gid")Long gid);
	/**
	 * 购物车中已存在的商品数量更新
	 * @param cid
	 * @return
	 */
	Integer updateNum(@Param("cid")Integer cid,@Param("num")Integer num,@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	/**
	 * 查找cart表中用户购物车中商品数据
	 * @param uid 客户uid
	 * @return 该用户的商品列表s
	 */
	List<CartVO> findCartVOByUid(Integer uid);
	/**
	 * 查询用户uid和购物车中该商品数量
	 * @param cid 用户的数据cid
	 * @return
	 */
	Cart findByCid(Integer cid);
	/**
	 * 显示购物车中勾选的商品数据
	 * @param cids 商品数据cids
	 * @return 该用户准备购买的商品列表
	 */
	List<CartVO> findCartVOByCids(Integer[] cids);
}
