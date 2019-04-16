package cn.tedu.store5.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store5.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTestcase {
	@Autowired
	IDistrictService service;
	@Test
	public void selectTest() {
		String parent = "360000";
		List<District> cities = service.getByParent(parent);
		for(District city:cities) {
			System.out.println(city);
		}
	}
	@Test
	public void selectCodeTest() {
		String code = "320305";
		District city = service.getByCode(code);
			System.out.println(city);
	}
}
