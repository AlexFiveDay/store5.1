package cn.tedu.store5.mapper;

import java.util.List;

import cn.tedu.store5.entity.Goods;

public interface GoodsMapper {
	/**
	 * 显示热销商品
	 * @return 热销商品排行前四
	 */
	List<Goods> findHotGoods();
	/**
	 * 显示热销商品详情
	 * @param id 商品id
	 * @return 该id对应商品的实体对象
	 */
	Goods findById(Long id);
}
