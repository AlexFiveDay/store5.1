package cn.tedu.store5.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store5.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {
	@Autowired
	IAddressService service;
	@Test
	public void addAddressTset() {
		Address address = new Address();
		address.setAddress("江西省南昌市安义县");
		address.setUid(18);
		address.setName("红瓢");
		address.setPhone("1235679546");
		String username = "拜拜";
		service.addNewAddress(address, username);
	}
	@Test
	public void showAddressTset() {
		Integer uid = 17;
		List<Address> t = service.showAddress(uid);
		for (Address address : t) {
			System.err.println(address);
		}
	}
	@Test
	public void changeDefaultAddressTset() {
		Integer uid = 17;
		Integer aid = 14;
		service.changeDefaultAddress(aid, uid);
	}
	@Test
	public void deleteAddressTset() {
		Integer uid = 17;
		Integer aid = 14;
		service.dropAddress(aid, uid);
	}
	
}
