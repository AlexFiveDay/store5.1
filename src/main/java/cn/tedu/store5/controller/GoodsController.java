package cn.tedu.store5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store5.entity.Goods;
import cn.tedu.store5.service.IGoodsService;
import cn.tedu.store5.util.ResponseResult;

@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController{
	@Autowired
	IGoodsService goodsService;
	@RequestMapping("showHotGoods")
	public ResponseResult<List<Goods>> getHotGoods() {
		List<Goods> data = goodsService.getHotGoods();
		return new ResponseResult<>(SUCCESS,data);
	}
	@GetMapping("/{id}/details")
	public ResponseResult<Goods> getById(@PathVariable("id") Long id) {
		Goods data = goodsService.getById(id);
		return new ResponseResult<>(SUCCESS,data);
	}
}
