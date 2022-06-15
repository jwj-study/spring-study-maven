package com.tmax.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

//  Oracle
//	@Select("SELECT sysdate FROM dual")
//	public String getTime();

//	public String getTime2();
	
//  Mysql
	@Select("SELECT now()")
	public String getTime();	

	public String getTime2();
	
}