package cn.tedu.store5.service;

import cn.tedu.store5.entity.User;
import cn.tedu.store5.service.ex.InsertException;
import cn.tedu.store5.service.ex.PasswordNotMatchException;
import cn.tedu.store5.service.ex.UpdateException;
import cn.tedu.store5.service.ex.UserNotFoundException;
import cn.tedu.store5.service.ex.UsernameDuplicateException;
/**
 * 处理用户数据的业务层接口
 * @author 杨大龙
 *
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param user 尝试注册的用户数据
	 * @return
	 * @throws UsernameDuplicateException 用户名被占用时的异常
	 * @throws InsertException 插入数据失败时的异常
	 */
	void reg(User user) throws UsernameDuplicateException,InsertException;
	/**
	 * 用户登录
	 * @param username 
	 * @param password
	 * @return 返回成功登录的用户信息
	 * @throws UserNotFoundException 用户名不存在或者用户已被注册但被标记为删除
	 * @throws PasswordNotMatchException 密码不匹配
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;
	/**
	 * 修改用户密码
	 * @param uid
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws UserNotFoundException 用户名不存在或者用户已被注册但被标记为删除
	 * @throws UpdateException 更新数据失败
	 */
	void changePassword(Integer uid,String username,String oldPassword,String newPassword) throws UserNotFoundException,UpdateException,PasswordNotMatchException;
	/**
	 * 更新用户信息
	 * @param user
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(User user) throws UserNotFoundException,UpdateException;
	/**
	 * 更新用户头像信息
	 * @param uid
	 * @param avatar 头像图片地址信息
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer uid,String avatar) throws UserNotFoundException,UpdateException;
	/**
	 * 获取用户信息
	 * @param uid
	 * @return 匹配的用户数据，若无数据则返回null。
	 */
	User getByUid(Integer uid);
}
