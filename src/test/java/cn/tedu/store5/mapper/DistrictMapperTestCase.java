package cn.tedu.store5.mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store5.entity.Address;
import cn.tedu.store5.entity.District;
import cn.tedu.store5.entity.User;
import cn.tedu.store5.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTestCase {

	@Autowired
	DataSource dataSource;

	/**
	 * 测试数据库是否连接
	 * 
	 * @throws SQLException
	 */
	@Test
	public void getConnection() throws SQLException {
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
	}

	@Autowired
	DistrictMapper Mapper;

	/**
	 * 测试插入数据功能
	 */
	@Test
	public void selectTest() {
		String parent = "360000";
		List<District> cities = Mapper.findByParent(parent);
		for(District city:cities) {
			System.out.println(city);
		}
	}
}
