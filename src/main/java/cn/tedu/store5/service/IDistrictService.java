package cn.tedu.store5.service;

import java.util.List;

import cn.tedu.store5.entity.District;
/**
 * 处理s省/市/区数据的业务层接口
 * @author 杨大龙
 *
 */
public interface IDistrictService {
	/**
	 * 获取所有省市区的列表
	 * @param parent	86获取省的列表信息
	 * @return
	 */
	List<District> getByParent(String parent);
	/**
	 * 根据代号获取省/市/区的信息
	 * @param code 省/市/区的代号
	 * @return 匹配的省市区的信息，若无匹配的信息，则返货null
	 */
	District getByCode (String code);
}
