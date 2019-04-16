package cn.tedu.store5.mapper;

import java.util.List;

import cn.tedu.store5.entity.Address;

public interface AddressMapper {
	/**
	 * 插入用户收货地址数据
	 * @param adress 收货地址数据
	 * @return	返回受影响的行数
	 */
	Integer insert(Address adress);
	/**
	 * 查询用户收货地址信息条数
	 * @param uid 查询用户的uid
	 * @return 返回用户的信息数量
	 */
	Integer countByUid(Integer uid);
	/**
	 * 查询用户的收货地址信息
	 * @param uid 查询用户的uid
	 * @return 返回用户的信息数量
	 */
	List<Address> findByUid(Integer uid);
	/**
	 * 通过aid查询需要设为默认地址的那条收货地址所属用户的uid
	 * @param aid 
	 * @return
	 */
	Address findByAid(Integer aid);
	/**
	 * 把所有该用户uid的收货地址都设为不默认
	 * @param uid
	 * @return
	 */
	Integer updateNonDefault(Integer uid);
	/**
	 * 设置该用户指定aid的收货地址为默认值
	 * @param aid
	 * @return
	 */
	Integer updateDefault(Integer aid);
	/**
	 * 删除该用户指定aid的收货地址
	 * @param aid
	 * @return
	 */
	Integer deleteAddress(Integer aid);
	/**
	 * 查找最近被修改的过的地址
	 * @param uid
	 * @return
	 */
	Address getByUid(Integer uid);
}
