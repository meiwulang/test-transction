package com.test.jdbc.service;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**   
* @Description TODO
* @author 王斌
* @date 2018年1月5日 上午11:57:55 
* @version V1.0   
*/
@Mapper
public interface TestMapper {
	int  insert(@Param("map")HashMap< String, Object> map);
}
