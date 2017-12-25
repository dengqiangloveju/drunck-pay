package com.drunck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;

@ServletComponentScan
@SpringBootApplication
@EnableAutoConfiguration
@ImportResource(locations = { "classpath:druid-bean.xml" })
@MapperScan(basePackages = "com.drunck.mapper")
public class DrunckPayApplication {

	/**
	 * 自定义异常页
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, "/page/403.html");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/page/404.html");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/page/500.html");
			container.addErrorPages(error401Page, error404Page, error500Page);
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(DrunckPayApplication.class, args);
	}
}
