package cn.tedu.store5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store5.entity.Address;
import cn.tedu.store5.service.IAddressService;
import cn.tedu.store5.util.ResponseResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
	@Autowired
	IAddressService addressService;
	@PostMapping("addnew")
	public ResponseResult<Void> addNewAddress(Address address,HttpSession session) {
		Integer uid = getUidFromSession(session);
		address.setUid(uid);
		String username = session.getAttribute("username").toString();
		addressService.addNewAddress(address, username);
		return new ResponseResult<>(SUCCESS);
	}
	@PostMapping("/")
	public ResponseResult<List<Address>> showAddress(HttpSession session) {
		Integer uid = getUidFromSession(session);
		List<Address> data = addressService.showAddress(uid);
		return new ResponseResult<>(SUCCESS,data);
	}
	@PostMapping("{aid}/change_address")
	public ResponseResult<Void> changeAddresss(@PathVariable("aid")Integer aid, HttpSession session) {
		Integer uid = getUidFromSession(session);
		addressService.changeDefaultAddress(aid, uid);
		return new ResponseResult<>(SUCCESS);
	}
	@PostMapping("{aid}/drop_address")
	public ResponseResult<Void> dropAddresss(@PathVariable("aid")Integer aid, HttpSession session) {
		Integer uid = getUidFromSession(session);
		addressService.dropAddress(aid, uid);
		return new ResponseResult<>(SUCCESS);
	}
}
