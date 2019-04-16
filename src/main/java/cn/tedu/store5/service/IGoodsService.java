package cn.tedu.store5.service;

import java.util.List;

import cn.tedu.store5.entity.Goods;

/**
 * 处理热销商品排行榜的业务层接口
 * @author 杨大龙
 *
 */
public interface IGoodsService {
	/**
	 * 查询热销商品前四的信息
	 * @return
	 */
	List<Goods> getHotGoods();
	/**
	 * 通过商品id获取热销商品的详细信息
	 * @param id
	 * @return
	 */
	Goods getById(Long id);
}
