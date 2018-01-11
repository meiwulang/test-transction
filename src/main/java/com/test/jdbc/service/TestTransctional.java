package com.test.jdbc.service;

import java.util.Date;
import java.util.HashMap;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @author 王斌
 * @date 2018年1月5日 上午11:52:00
 * @version V1.0
 */
@Service
//@Transactional(propagation=Propagation.REQUIRED)
public class TestTransctional {

	@Autowired
	TestMapper mapper;
	@Autowired 
	TestService2 service2;

	@Transactional(propagation=Propagation.REQUIRED)
	public void test1() {
		insert("0",null);
//		service2.test2();
//		service2.test3();
		((TestTransctional) AopContext.currentProxy()).test2();
		((TestTransctional) AopContext.currentProxy()).test3();
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void test2() {
		insert("1",new Date());
//		throw new RuntimeException("出错了");
	}
	@Transactional(propagation=Propagation. REQUIRES_NEW)
	public void test3() {
		insert("2",new Date());
		throw new RuntimeException("出错了");
	}
	private void insert(String display,Date date_time) {
		HashMap<String, Object> map = new HashMap<String, Object>(1) {
			{
				put("display", display);
				put("date_time", date_time);
			}
		};
		mapper.insert(map);
	}

	
	/**
	 * 实践结论
	 * 1：方法test1 被 @Transactional(propagation=Propagation.REQUIRED)，同时 test2不被注解修饰，此时方法test2如果存在runtimeException，那么事务会回滚 
	 * 2:方法test1 被 @Transactional(propagation=Propagation.REQUIRED)，同时 test2被@Transactional(propagation=Propagation.NOT_SUPPORTED)修饰，此时方法test2如果存在runtimeException，那么事务会回滚
	 * 3:方法test1 被 @Transactional(propagation=Propagation.NOT_SUPPORTED)，同时 test2被@Transactional(propagation=Propagation.*)修饰，此时方法test2如果存在runtimeException，那么事务不会回滚
	 * 4:方法test1不被注解修饰,同时方法test2 被 @Transactional(propagation=Propagation.*)，此时方法test2如果存在runtimeException，那么事务不会回滚
	 * 5:方法test1 被 @Transactional(propagation=Propagation.REQUIRED)，同时 被@Transactional(propagation=Propagation.NEVER)修饰，此时方法test2如果存在runtimeException，那么事务会回滚 
	 * 
	 * 
	 * 
	 */
}
