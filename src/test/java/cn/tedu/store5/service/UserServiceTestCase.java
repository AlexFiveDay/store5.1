package cn.tedu.store5.service;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import cn.tedu.store5.entity.User;
import cn.tedu.store5.service.IUserService;
import cn.tedu.store5.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	@Autowired
	IUserService service;
	@Test
	public void regTset() {
		try {
			User user = new User();
			user.setUsername("小小");
			user.setPassword("123456");
			user.setIsDelete(0);
			user.setGender(1);
			user.setEmail("102655@qq.com");
			service.reg(user);
			System.out.println("ok");
		} catch (ServiceException e) {
			System.out.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * 密码加密测试
	 */
	@Test
	public void messageDigest() {
		String password="123456";
		String salt = UUID.randomUUID().toString();
		String result = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes());
		System.err.println(result);
		System.err.println();
	}
	/**
	 * 用户登录测试
	 */
	@Test
	public void loginTest() {
		try {
			String username="aaa";
			String password="123456";
			User user = service.login(username, password);
			System.err.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void updateTest() {
		try {
			String username="jack";
			Integer uid = 13;
			String oldPassword = "123456";
			String newPassword = "456789";
			//Integer rows = service.update(uid , username, oldPassword, newPassword);
			//System.err.println(rows);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void changeInfoTest() {
		try {
			User user = new User();
			user.setUid(17);
			user.setUsername("jack");
			user.setPhone("186799305472");
			user.setModifiedTime(new Date());
			user.setModifiedUser("jack2");
			user.setGender(1);
			user.setEmail("222222@qq.com");
			user.setIsDelete(1);
			service.changeInfo(user);
			//Integer rows = service.update(uid , username, oldPassword, newPassword);
			//System.err.println(rows);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void getByUid() {
		Integer uid = 17;
		User user = service.getByUid(uid);
		System.err.println(user);
	}
	@Test
	public void changeAvatarTest() {
		try {
			String avatar = "jjjjj";
			Integer uid = 17;
			service.changeAvatar(uid, avatar);
			//Integer rows = service.update(uid , username, oldPassword, newPassword);
			//System.err.println(rows);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
}
