package cn.tedu.store5.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store5.entity.User;
/**
 * 处理用户数据的持久层接口
 * @author 杨大龙
 *
 */
public interface UserMapper {
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer insert(User user);
	/**
	 * 根据用户id更新用户信息
	 * 超过一个参数必须加注解
	 * @param uid 
	 * @param password
	 * @param modifiedTime
	 * @param modifiedUser
	 * @return
	 */
	Integer updatePassword(@Param("uid")Integer uid,@Param("password")String password,@Param("modifiedTime")Date modifiedTime,@Param("modifiedUser")String modifiedUser);
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	Integer updateInfo(User user);
	/**
	 * 修改用户头像信息
	 * @param user
	 * @return
	 */
	Integer updateAvatar(@Param("uid")Integer uid,@Param("avatar")String avatar,@Param("modifiedTime")Date modifiedTime,@Param("modifiedUser")String modifiedUser);
	/**
	 * 根据用户名查找用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，若无匹配数据则返回null
	 */
	User findByUsername(String username);
	/**
	 * 根据用户ID查找用户数据
	 * @param uid
	 * @return
	 */
	User findByUid(Integer uid);
	
}
