package com.selune.wechatordering;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.selune.wechatordering.pojo.mapper")
@EnableCaching  // 开启缓存
public class WechatOrderingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatOrderingApplication.class, args);
	}

}
