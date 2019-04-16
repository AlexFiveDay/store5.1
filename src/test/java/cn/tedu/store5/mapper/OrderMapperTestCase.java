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
import cn.tedu.store5.entity.Order;
import cn.tedu.store5.entity.OrderItem;
import cn.tedu.store5.entity.User;
import cn.tedu.store5.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestCase {

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
	OrderMapper orderMapper;

	/**
	 * 测试插入数据功能
	 */
	@Test
	public void insertOrderTest() {
		Order order = new Order();
		order.setUid(9);
		order.setName("小杨同学");
		Integer rows = orderMapper.insertOrder(order);
		System.err.println(rows);
	}
	@Test
	public void countByUidTest() {
		OrderItem orderItem = new OrderItem();
		orderItem.setNum((long) 3);
		orderItem.setPrice((long) 999);
		orderItem.setGid((long) 99);
		Integer rows = orderMapper.insertOrderItem(orderItem);
		System.err.println(rows);
	}
	
}
