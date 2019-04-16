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

import cn.tedu.store5.entity.User;
import cn.tedu.store5.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {

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
	UserMapper userMapper;

	/**
	 * 测试插入数据功能
	 */
	@Test
	public void findUser() {
		String username = "jack";
		User user = userMapper.findByUsername(username);
		System.out.println(user);
	}

	@Test
	public void insertUser() {
		User user = new User();
		user.setUsername("小白");
		user.setPassword("123456");
		user.setIsDelete(0);
		user.setGender(1);
		user.setEmail("102655@qq.com");
		Integer result = userMapper.insert(user);
		System.out.println(result);
	}

	/**
	 * 测试更新数据功能
	 */
	@Test
	public void findByUid() {
		Integer uid = 14;
		User user = userMapper.findByUid(uid);
		System.out.println(user);
	}

	@Test
	public void updatePassword() {
		Integer uid = 14;
		Date now = new Date();
		Date modifiedTime = now;
		String modifiedUser = "ok";
		String password = "123456";
		userMapper.updatePassword(uid, password, modifiedTime, modifiedUser);
	}

	@Test
	public void updateInfo() {
		User user = new User();
		user.setUid(17);
		user.setUsername("jack");
		user.setPhone("18679930547");
		user.setModifiedTime(new Date());
		user.setModifiedUser("jack");
		user.setGender(1);
		user.setEmail("111111@qq.com");
		Integer result = userMapper.updateInfo(user);
		System.out.println(result);
	}
	@Test
	public void updateAvatar() {
		Integer uid = 17;
		Date now = new Date();
		Date modifiedTime = now;
		String modifiedUser = "ok";
		String avatar = "123456";
		userMapper.updateAvatar(uid, avatar, modifiedTime, modifiedUser);
	}
}
