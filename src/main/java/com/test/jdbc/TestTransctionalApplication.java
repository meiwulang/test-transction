package com.test.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.test.jdbc.service.TestTransctional;
import com.test.jdbc.util.SpringUtil;

@SpringBootApplication
//@EnableTransactionManagement
public class TestTransctionalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(TestTransctionalApplication.class, args);
//		String[] beanNames = run.getBeanDefinitionNames();
//	      for(String bn:beanNames){  
//	            System.out.println(bn);  
//	        }  
		SpringUtil.setApplicationContext(run);
		TestTransctional service=SpringUtil.getBean(TestTransctional.class);
		service.test1();
//		service.test2();
	}
}
