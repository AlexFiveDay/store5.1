package cn.tedu.store5.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store5.entity.Goods;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTestcase {
	@Autowired
	IGoodsService goodsService;
	/**
	 * 测试热销商品的筛选
	 */
	@Test
	public void getHotGoodsTest() {
		List<Goods> goods = goodsService.getHotGoods();
		for (Goods good : goods) {
			System.err.println(good);
		}
	}
	/**
	 * 获取热销商品的详细信息
	 */
	@Test
	public void getMessageGoodsByIdTest() {
		Goods good = goodsService.getById((long) 10000001);
			System.err.println(good);
	}
}
