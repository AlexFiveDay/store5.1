package cn.tedu.store5.mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store5.entity.Address;
import cn.tedu.store5.entity.User;
import cn.tedu.store5.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {

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
	AddressMapper addressMapper;

	/**
	 * 测试插入数据功能
	 */
	@Test
	public void insertAddressTest() {
		Address address = new Address();
		address.setAddress("江西省南昌市安义县");
		address.setUid(17);
		address.setName("红瓢");
		address.setPhone("1235679546");
		Integer rows = addressMapper.insert(address);
		System.err.println(rows);
	}
	@Test
	public void countByUidTest() {
		Integer uid = 17;
		Integer rows = addressMapper.countByUid(uid);
		System.err.println(rows);
	}
	
}
