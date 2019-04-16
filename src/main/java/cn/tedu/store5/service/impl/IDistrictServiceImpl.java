package cn.tedu.store5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store5.entity.District;
import cn.tedu.store5.mapper.DistrictMapper;
import cn.tedu.store5.service.IDistrictService;

@Service
public class IDistrictServiceImpl implements IDistrictService {
	@Autowired
	private DistrictMapper districtMapper;

	@Override
	public List<District> getByParent(String parent) {
		return findByParent(parent);
	}

	@Override
	public District getByCode(String code) {
		return findByCode(code);
	}

	/**
	 * 获取所有省所有市所有区的列表
	 * 
	 * @param parent
	 *            获取省列表时使用86;获取市列表时，使用省的代号code；获取区列表时使用市的代号code
	 * @return 省/市/区的列表集合
	 */
	private List<District> findByParent(String parent) {
		return districtMapper.findByParent(parent);
	}
	/**
	 * 根据代号获取省/市/区的信息
	 * @param code 省/市/区的代号
	 * @return 匹配的省市区的信息，若无匹配的信息，则返货null
	 */
	private District findByCode(String code) {
		return districtMapper.findByCode(code);
	}
	
}
