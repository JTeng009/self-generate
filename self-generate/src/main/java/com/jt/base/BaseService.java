package com.jt.base;

import java.util.List;
import java.util.Map;

public class BaseService<K, T> {
	protected BaseMapper<K, T> mapper;

    /**
     * 根据params参数来统计总数
     *
     * @param example
     * @return 记录数
     */
    public int count(T example) {
    	return mapper.countByExample(example);
    }
    /**
     * 根据params参数来统计总数
     *
     * @param example
     * @return 记录数
     */
    public int count(Map<String, Object> example) {
    	return mapper.countByExample(example);
    }

    /**
     * 根据id来删除记录
     *
     * @param id
     * @return 删除的记录数
     */
    public int delete(K id) {
    	return mapper.deleteByPrimaryKey(id);
    }
    /**
     * 根据params参数来删除
     *
     * @param example
     * @return 删除的记录数
     */
    public int deleteByExample(T example){
    	return mapper.deleteByExample(example);
    }
    /**
     * 根据params参数来删除
     *
     * @param example
     * @return 删除的记录数
     */
    public int deleteByExample(Map<String, Object> example){
    	return mapper.deleteByExample(example);
    }
    /**
     * 新增记录
     *
     * @param record
     */
    public int insert(T record) {
    	return mapper.insert(record);
    }
    /**
     * 忽略null字段
     * @param record
     * @return
     */
    public int insertSelective(T record){
    	return mapper.insertSelective(record);
    }

    /**
     * 根据params来选择记录
     *
     * @param params
     * @return 记录列表
     */
    public List<T> selectList(Map<String, Object> example) {
        return mapper.selectByExample(example);
    }
    
    /**
     * 根据主键来查找记录
     *
     * @param object
     * @return 选择的记录
     */
    public T selectOne(K id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键来更新记录
     *
     * @param object
     * @return 影响的行数
     */
    public int update(T record) {
        return mapper.updateByPrimaryKey(record);
    }
    /**
     * 根据主键来更新记录  忽略null字段
     *
     * @param object
     * @return 影响的行数
     */
    public int updateSelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
    /**
     * 根据条件来更新记录
     *
     * @param object
     * @return 影响的行数
     */
    public int updateByExample(T record,T example) {
        return mapper.updateByExample(record, example);
    }
    /**
     * 根据条件来更新记录  忽略null字段
     *
     * @param object
     * @return 影响的行数
     */
    public int updateByExampleSelective(T record,T example) {
        return mapper.updateByExampleSelective(record, example);
    }
    /**
     * 根据参数查找唯一的一条记录
     * @param params
     * @return
     */
	public T selectParamOne(Map<String, Object> example) {
		 List<T> list = this.selectList(example);
		 if(list.size()==0){
			 return null;
		 }
		 if(list.size()>1){
			 throw new RuntimeException("数据不止一条");
		 }
		 return list.get(0);
	}
}
