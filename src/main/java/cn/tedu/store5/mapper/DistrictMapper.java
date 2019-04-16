package cn.tedu.store5.mapper;
/**
 * 处理s省/市/区数据的持久层接口
 * @author 杨大龙
 *
 */

import java.util.List;

import cn.tedu.store5.entity.District;

public interface DistrictMapper {
	/**
	 * 获取某省/市/区 的列表
	 * 
	 * @param parent
	 * @return
	 */
	List<District> findByParent (String parent);
	
	/**
	 * 根据代号获取省/市/区的信息
	 * @param code 省/市/区的代号
	 * @return 匹配的省市区的信息，若无匹配的信息，则返货null
	 */
	District findByCode (String code);
}
