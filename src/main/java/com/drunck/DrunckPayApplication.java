package com.drunck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@ServletComponentScan
@SpringBootApplication
@ImportResource(locations = { "classpath:druid-bean.xml" })
@MapperScan(basePackages = "com.drunck.mapper")
public class DrunckPayApplication {
	public static void main(String[] args) {
		SpringApplication.run(DrunckPayApplication.class, args);
	}
}
