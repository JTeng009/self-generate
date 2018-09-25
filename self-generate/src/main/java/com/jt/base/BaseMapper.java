package com.jt.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper <K,T>{
	
	int countByExample(T example);

	int countByExample(Map<String, Object> example);
	
	int deleteByExample(T example);

	int deleteByExample(Map<String, Object> example);
	
	int deleteByPrimaryKey(K id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(Map<String, Object> example);

	T selectByPrimaryKey(K id);

	int updateByExampleSelective(@Param("record") T record,@Param("example") T example);
	
	int updateByExampleSelective(@Param("record") T record,@Param("example") Map<String, Object> example);

	int updateByExample(@Param("record") T record, @Param("example") T example);
	
	int updateByExample(@Param("record") T record, @Param("example") Map<String, Object> example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

}
