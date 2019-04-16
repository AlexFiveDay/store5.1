package cn.tedu.store5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store5.entity.Goods;
import cn.tedu.store5.mapper.GoodsMapper;
import cn.tedu.store5.service.IGoodsService;

@Service
public class IGoodsServiceImpl implements IGoodsService {
	@Autowired
	GoodsMapper goodsMapper;

	@Override
	public List<Goods> getHotGoods() {
		return findHotGoods();
	}

	@Override
	public Goods getById(Long id) {
		return findById(id);
	}

	/**
	 * 获取热销商品列表
	 * 
	 * @return 热销商品列表
	 */
	private List<Goods> findHotGoods() {
		return goodsMapper.findHotGoods();
	}

	/**
	 * 通过商品id获取热销商品的详细信息
	 * 
	 * @return 该id热销商品的详细信息
	 */
	private Goods findById(Long id) {
		return goodsMapper.findById(id);
	}

}
