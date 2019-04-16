package cn.tedu.store5;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.tedu.store5.mapper")//指定接口文件所在的根包,实现所在包内的mapper的接口文件等同@Mapper或xml文件中的MapperScannerConfigurer
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
