package cn.tedu.store5.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store5.entity.Address;
import cn.tedu.store5.entity.District;
import cn.tedu.store5.mapper.AddressMapper;
import cn.tedu.store5.service.IAddressService;
import cn.tedu.store5.service.IDistrictService;
import cn.tedu.store5.service.ex.AccessDeniedException;
import cn.tedu.store5.service.ex.AddressNotFoundException;
import cn.tedu.store5.service.ex.InsertException;
import cn.tedu.store5.service.ex.UpdateException;
import cn.tedu.store5.service.ex.deleteException;

@Service
public class IAddressServiceImpl implements IAddressService {
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private IDistrictService districtService;

	@Override
	public void addNewAddress(Address address, String username) throws InsertException {
		// 判断用户的地址数据量
		Integer count = addressMapper.countByUid(address.getUid());
		// 判断值是否为0
		// 是：设为默认收货地址
		// 否：不设置
		address.setIsDefault(count == 0 ? 1 : 0);
		// 处理district地址显示
		address.setDistrict(getDistrict(address.getProvince(), address.getCity(), address.getArea()));
		// 设置时间
		address.setCreateTime(new Date());
		address.setCreateUser(username);
		address.setModifiedTime(new Date());
		address.setModifiedUser(username);
		// 插入数据
		insert(address);
	}

	@Override
	public List<Address> showAddress(Integer uid) {
		return findByUid(uid);
	}

	@Override
	@Transactional // 表示此方法以事务(springBoot注解)的方法运行，保证的数据的安全性(来自springJDBC的jar包)
	public void changeDefaultAddress(Integer aid, Integer uid)
			throws UpdateException, AddressNotFoundException, AccessDeniedException {
		// 查询aid的数据
		Address result = findByAid(aid);
		// 如果查询的数据result的地址为null，则抛出异常AddressNotFoundException
		if (result == null) {
			throw new AddressNotFoundException("所查询的aid的收货地址不存在异常");
		}
		// 判断参数中的uid是否与查询结果中的uid一致
		if (result.getUid() != uid) {
			throw new AccessDeniedException("用户信息不匹配异常，请重新登录");
		}
		// 全部设为非默认
		updateNonDefault(result.getUid());
		// 把制定的设为默认
		updateDefault(aid);
	}

	@Override
	@Transactional // 表示此方法以事务(springBoot注解)的方法运行，保证的数据的安全性(来自springJDBC的jar包)
	public void dropAddress(Integer aid, Integer uid)
			throws UpdateException, AddressNotFoundException, AccessDeniedException ,deleteException{
		// 查询aid的数据
		Address result = findByAid(aid);
		// 如果查询的数据result的地址为null，则抛出异常AddressNotFoundException
		if (result == null) {
			throw new AddressNotFoundException("所查询的aid的收货地址不存在异常");
		}
		// 判断参数中的uid是否与查询结果中的uid一致
				if (result.getUid() != uid) {
					throw new AccessDeniedException("用户信息不匹配异常，请重新登录");
				}
		// 查询删除的是否是默认地址
		if (result.getIsDefault() == 1) {
			// 查询剩余数据信息
			Integer rows = addressMapper.countByUid(uid);
			if (rows > 1) {
				// 查找最近被修改的过的地址
				Address result2 = addressMapper.getByUid(uid);
				//设置最近被修改的过的地址为默认地址
				// 全部设为非默认
				updateNonDefault(result2.getUid());
				// 把制定的设为默认
				updateDefault(result2.getAid());
			}
			deleteAddress(aid);
		}
	}

	private void insert(Address address) {
		Integer result = addressMapper.insert(address);
		if (result != 1) {
			throw new InsertException("添加地址数据插入异常");
		}
	}

	/**
	 * 根据省、市、区的代号获取名称
	 * 
	 * @param provinc
	 *            省的代号
	 * @param city
	 *            市的代号
	 * @param area
	 *            区的代号
	 * @return 省、市、区的代号对应的名称
	 */
	private String getDistrict(String province, String city, String area) {
		District p = districtService.getByCode(province);
		District c = districtService.getByCode(city);
		District a = districtService.getByCode(area);
		String pName = p == null ? null : p.getName();
		String cName = c == null ? null : c.getName();
		String aName = a == null ? null : a.getName();

		// 对于字符串的频繁更改单线程推荐使用stringBuilder，多线程使用StringBuffer（线程安全的）。节约内存空间
		StringBuffer result = new StringBuffer();
		result.append(pName);
		result.append(",");
		result.append(cName);
		result.append(",");
		result.append(aName);
		result.append(".");

		return result.toString();
	}

	private List<Address> findByUid(Integer uid) {
		return addressMapper.findByUid(uid);
	}

	private Address findByAid(Integer aid) {
		return addressMapper.findByAid(aid);
	}

	private void updateNonDefault(Integer uid) {
		Integer count = addressMapper.countByUid(uid);
		Integer rows = addressMapper.updateNonDefault(uid);
		// System.err.println(count);
		// System.err.println(rows);
		if (count != rows) {
			throw new UpdateException("收货地址信息全部默认归0失败");
		}
	}

	private void updateDefault(Integer aid) {
		Integer rows = addressMapper.updateDefault(aid);
		if (rows != 1) {
			throw new UpdateException("指定收货地址设为默认值失败");
		}
	}
	private void deleteAddress(Integer aid) {
		Integer rows = addressMapper.deleteAddress(aid);
		if (rows!=1) {
			throw new deleteException("地址删除失败异常");
		}
	}
}
