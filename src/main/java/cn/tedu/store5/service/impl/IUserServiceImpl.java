package cn.tedu.store5.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store5.entity.User;
import cn.tedu.store5.mapper.UserMapper;
import cn.tedu.store5.service.IUserService;
import cn.tedu.store5.service.ex.InsertException;
import cn.tedu.store5.service.ex.PasswordNotMatchException;
import cn.tedu.store5.service.ex.UpdateException;
import cn.tedu.store5.service.ex.UserNotFoundException;
import cn.tedu.store5.service.ex.UsernameDuplicateException;

/**
 * 处理用户数据的业务层实现类
 * 
 * @author 杨大龙
 *
 */
@Service
public class IUserServiceImpl implements IUserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		// 检查用户名是否被占用
		String username = user.getUsername();
		User result = findByUsername(username);
		if (result == null) {
			// 未占用，执行注册

			// 设置is_delete
			user.setIsDelete(0);

			// 设置4项日志
			Date now = new Date();
			user.setCreateTime(now);
			user.setCreateUser(username);
			user.setModifiedTime(now);
			user.setModifiedUser(username);

			// 密码加密

			// 生成随机盐
			String salt = UUID.randomUUID().toString();
			// 执行密码加密,得到加密后的密码
			String md5Password = getMd5Password(user.getPassword(), salt);
			// 将盐和加密后的密码封装到user中
			user.setSalt(salt);
			user.setPassword(md5Password);

			// 插入数据
			insert(user);

		} else {
			// 占用，抛出UsernameDuplicateException
			throw new UsernameDuplicateException("你尝试注册的用户名：(" + username + ")已经被注册");
		}
	}

	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		// 检查用户名是否存在
		User user = userMapper.findByUsername(username);
		// 判断查询结果是否为null，是否被删除
		if (user != null && user.getIsDelete() == 0) {
			password = getMd5Password(password, user.getSalt());
			if (password.equals(user.getPassword())) {
				user.setSalt(null);
				user.setPassword(null);
				user.setIsDelete(null);
				return user;
			} else {
				throw new PasswordNotMatchException("密码输入错误");
			}
		} else {
			throw new UserNotFoundException("用户名不存在");
		}
	}

	@Override
	public void changePassword(Integer uid, String username, String oldPassword, String newPassword)
			throws UserNotFoundException, UpdateException {
		// 检查用户名是否存在
		User user = userMapper.findByUid(uid);
		// 判断查询结果是否为null，是否被删除
		/*if (user != null && user.getIsDelete() == 0) {
			oldPassword = getMd5Password(oldPassword, user.getSalt());
			if (oldPassword.equals(user.getPassword())) {
				String salt = user.getSalt();
				Date modifiedTime = new Date();
				String modifiedUser = username;
				//新密码加密
				newPassword = getMd5Password(newPassword, salt);
				//更新密码
				Integer rows = userMapper.update(uid, newPassword, modifiedTime, modifiedUser);
				if (rows == 1) {
					return rows;
				} else {
					throw new UpdateException("不知名错误，找管理员去");
				}
			} else {
				throw new PasswordNotMatchException("原密码输入错误");
			}
		} else {
			throw new UserNotFoundException("用户名不存在");
		}*/
		if (user == null || user.getIsDelete() == 1) {
			throw new UserNotFoundException("用户名不存在");
		}
		oldPassword = getMd5Password(oldPassword, user.getSalt());
		if (!oldPassword.equals(user.getPassword())) {
			throw new PasswordNotMatchException("原密码输入错误");
		}
		String salt = user.getSalt();
		Date modifiedTime = new Date();
		String modifiedUser = username;
		//新密码加密
		newPassword = getMd5Password(newPassword, salt);
		//更新密码
		updatePassword(uid, newPassword, modifiedTime, modifiedUser);
		
	}
	@Override
	public void changeInfo(User user) throws UserNotFoundException, UpdateException {
		User result = userMapper.findByUid(user.getUid());
		if (result.getIsDelete()==1|| result==null) {
			throw new UserNotFoundException("用户不存在");
		}
		user.setModifiedTime(new Date());
		user.setModifiedUser(user.getUsername());
		updateInfo(user);
	}
	@Override
	public User getByUid(Integer uid) {
		User result = userMapper.findByUid(uid);
		if (result.getIsDelete()==1|| result==null) {
			throw new UserNotFoundException("用户不存在");
		}
		//在返回数据之前隐藏不向外提供的数据
		result.setSalt(null);
		result.setPassword(null);
		result.setIsDelete(null);
		//返回数据
		return result;
	}
	@Override
	public void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException {
		User result = userMapper.findByUid(uid);
		if (result==null) {
			throw new UserNotFoundException("该用户不存在");
		}
		Date modifiedTime = new Date();
		String modifiedUser = result.getUsername();
		updateAvatar(uid, avatar, modifiedTime, modifiedUser);
	}
	/**
	 * 插入用户数据
	 * 
	 * @param user
	 *            用户数据
	 * @return 受影响的行数
	 */
	private void insert(User user) {
		Integer rows = userMapper.insert(user);
		if (rows != 1) {
			throw new InsertException("插入用户数据出现位置数据，请联系sb");
		} else {

		}
	}

	/**
	 * 根据用户名查找用户数据
	 * 
	 * @param username
	 *            用户名
	 * @return 匹配的用户数据，若无匹配数据则返回null
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	/**
	 * 执行md5加密后的算法
	 * 
	 * @param password
	 *            原密码
	 * @param salt
	 *            盐值
	 * @return 加密后的密码
	 */
	private String getMd5Password(String password, String salt) {
		// 加密规则:使用"盐+密码+盐"作为原始数据,加密五次
		String result = salt + password + salt;
		for (int i = 0; i < 5; i++) {
			result = DigestUtils.md5Hex(salt + password + salt);
		}
		return result;
	}
	/**
	 * 根据用户id更新用户密码
	 * @param uid
	 * @param newPassword
	 * @param modifiedTime
	 * @param modifiedUser
	 */
	private void updatePassword(Integer uid, String newPassword, Date modifiedTime, String modifiedUser) {
		Integer rows = userMapper.updatePassword(uid, newPassword, modifiedTime, modifiedUser);
		if (rows != 1) {
			throw new UpdateException("不知名错误，找管理员去");
		}
	}
	/**
	 * 更新用户信息
	 * @param user
	 */
	private void updateInfo(User user) {
		Integer rows = userMapper.updateInfo(user);
		if (rows != 1) {
			throw new UpdateException("不知名错误，changeInfo错误，找管理员去");
		}
	}

	
	
	private void updateAvatar(Integer uid,String avatar,Date modifiedTime,String modifiedUser) {
		Integer rows = userMapper.updateAvatar(uid, avatar, modifiedTime, modifiedUser);
		if (rows != 1) {
			throw new UpdateException("不知名错误，changeAvatar错误，找管理员去");
		}
	}
	
}
