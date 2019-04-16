package cn.tedu.store5.service;

import java.util.List;

import cn.tedu.store5.entity.Address;
import cn.tedu.store5.service.ex.AccessDeniedException;
import cn.tedu.store5.service.ex.AddressNotFoundException;
import cn.tedu.store5.service.ex.InsertException;
import cn.tedu.store5.service.ex.UpdateException;
import cn.tedu.store5.service.ex.deleteException;

public interface IAddressService {
	/**
	 * 添加用户收货地址信息
	 * 
	 * @param address
	 * @param username
	 */
	void addNewAddress(Address address, String username) throws InsertException;

	/**
	 * 显示用户收货地址信息
	 * 
	 * @param uid
	 * @return
	 */
	List<Address> showAddress(Integer uid);

	/**
	 * 修改用户的默认收货地址
	 * 
	 * @param aid
	 */
	void changeDefaultAddress(Integer aid, Integer uid)
			throws UpdateException, AccessDeniedException, AddressNotFoundException;

	/**
	 * 删除收货地址信息
	 * 
	 * @param aid
	 *            删除该用户指定aid的收货地址
	 * @param uid
	 *            通过uid查找最近被修改的过的地址信息
	 */
	void dropAddress(Integer aid, Integer uid)
			throws UpdateException, AddressNotFoundException, AccessDeniedException,deleteException;
}
