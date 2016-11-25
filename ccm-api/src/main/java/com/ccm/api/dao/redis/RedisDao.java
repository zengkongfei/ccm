package com.ccm.api.dao.redis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public interface RedisDao {
	public void save(String key,Serializable r,long timeout,TimeUnit unit) ;
	public void save(String key,Serializable r) ;
	public Serializable readObject(String key) ;
	public void delete(String key) ;
	public void saveList(String key,List<Serializable>list);
	public void saveSortedSet(String key,Serializable s,double index);
	public void saveHash(String key,Map<String,Object>map);
	public void saveSet(String key,Serializable s);
	public List<Serializable> findList(String key,long beginIdx,long endIdx);
	public Set<Serializable> findSet(String key);
	public void hincrebyForHash(String key,Object field,long increment);
	public void hincrebyForHash(String key,Object field,float increment);
	public void hsetForHash(String key,Object field,Object value);
	public Object getFieldValueForHash(String key,Object field);
	public Set<String> getValueLike(String pattern);
	public Map readHashValueForMap(String key,Object field);
	public long readLongValueFromHash(final String key, final String field);
	public Map<String, Long> readLongValueFromMap(final String key);
	public void deleteFieldForHash(String key,Object...o) ;
	/**
	 * 过期时间
	 * @param key
	 * @return
	 */
	public Long getExpire(String key);
	/**
	 * 保存hash，设置过期时间
	 * @param key
	 * @param map
	 * @param timeout
	 * @param unit
	 */
	public void saveHash(String key, Map<String, Object> map,long timeout,TimeUnit unit);
	
	public Map<Object, Object> getFieldValueForHash(String key);
	
	public void hincrebyForHash(String key, Object field, long increment,long timeout,TimeUnit unit);
	
}
