package cn.tedu.store5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store5.entity.District;
import cn.tedu.store5.service.IDistrictService;
import cn.tedu.store5.util.ResponseResult;

@RestController
@RequestMapping("districts") // 'districts/'后面加/一般表示显示数据列表
public class DistrictController extends BaseController {
	@Autowired
	IDistrictService districtService;
	@RequestMapping("/")
	private ResponseResult<List<District>> name(@RequestParam("parent") String parent) {
		List<District> data = districtService.getByParent(parent);
		return new ResponseResult<>(SUCCESS,data);
	}
}
