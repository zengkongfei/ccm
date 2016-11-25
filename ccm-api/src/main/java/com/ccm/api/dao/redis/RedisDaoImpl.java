package com.ccm.api.dao.redis;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@Repository("redisDao")
public class RedisDaoImpl implements RedisDao {

	@Resource
	protected RedisTemplate<String, Serializable> redisTemplate;

	public void saveList(String key, List<Serializable> list) {
		ListOperations<String, Serializable> listOperations = redisTemplate
				.opsForList();
		for (int i = 0; i < list.size(); i++) {
			listOperations.leftPush(key, list.get(i));
		}
	}

	public void saveSortedSet(String key, Serializable s, double index) {
		ZSetOperations<String, Serializable> zsetOperations = redisTemplate
				.opsForZSet();
		zsetOperations.add(key, s, index);
	}

	public void saveSet(String key, Serializable s) {
		SetOperations<String, Serializable> setOperations = redisTemplate
				.opsForSet();
		setOperations.add(key, s);
	}

	public void saveHash(String key, Map<String, Object> map) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		for (Map.Entry<String, Object> en : map.entrySet()) {
			hashOperations.put(key, en.getKey(), en.getValue());
		}
	}

	public void hincrebyForHash(String key, Object field, long increment) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		hashOperations.increment(key, field, increment);
	}

	public void hincrebyForHash(String key, Object field, float increment) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		hashOperations.increment(key, field, increment);
	}

	public void hsetForHash(String key, Object field, Object value) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		hashOperations.put(key, field, value);
	}

	public Object getFieldValueForHash(String key, Object field) {
		BoundHashOperations boundHashOperations = redisTemplate
				.boundHashOps(key);
		Object o = boundHashOperations.get(field);
		return o;
	}

	public Set<String> getValueLike(String pattern) {
		Set<String> resultSet = redisTemplate.keys(pattern);
		return resultSet;
	}

	public List<Serializable> findList(String key, long beginIdx, long endIdx) {
		BoundListOperations<String, Serializable> boundListOperations = redisTemplate
				.boundListOps(key);
		return boundListOperations.range(beginIdx, endIdx);
	}

	public Set findSet(String key) {
		BoundSetOperations<String, Serializable> setOperaions = redisTemplate
				.boundSetOps(key);
		return setOperaions.members();
	}

	public void save(String key, Serializable s,long timeout,TimeUnit unit) {
		ValueOperations<String, Serializable> valueOper = redisTemplate
				.opsForValue();
		valueOper.set(key,s,timeout,unit);
		
	}

	public Map readHashValueForMap(String key, Object field) {
		BoundHashOperations boundHashOperations = redisTemplate
				.boundHashOps(key);
		return boundHashOperations.entries();
	}

	public Serializable readObject(String key) {
		ValueOperations<String, Serializable> valueOper = redisTemplate
				.opsForValue();
		return valueOper.get(key);
	}

	public void deleteFieldForHash(String key,Object...o) {
		BoundHashOperations boundHashOperations = redisTemplate
		.boundHashOps(key);
		boundHashOperations.delete(o);
	}

	public void delete(String key) {
		ValueOperations<String, Serializable> valueOper = redisTemplate
				.opsForValue();
		RedisOperations<String, Serializable> RedisOperations = valueOper
				.getOperations();
		RedisOperations.delete(key);
	}
	
	public long readLongValueFromHash(final String key, final String field) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keybytes = redisTemplate.getStringSerializer()
						.serialize(key);
				if (connection.exists(keybytes)) {
					byte[] valueOfBytes = connection.hGet(keybytes,
							redisTemplate.getStringSerializer()
									.serialize(field));
					Long value;
					if(valueOfBytes==null){
						value=new Long(0);
					}else{
						value=Long.valueOf(redisTemplate
								.getStringSerializer().deserialize(valueOfBytes));
					}
					
					return value;
				}
				return new Long(0);
			}

		});
	}

	public Map<String, Long> readLongValueFromMap(final String key) {
		return redisTemplate.execute(new RedisCallback<Map<String, Long>>() {
			public Map<String, Long> doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keybytes = redisTemplate.getStringSerializer()
						.serialize(key);
				if (connection.exists(keybytes)) {
					Map<String, Long> resultMap = new HashMap<String, Long>();
					Map<byte[], byte[]> originalMap = connection
							.hGetAll(keybytes);

					for (Map.Entry<byte[], byte[]> en : originalMap.entrySet()) {
						resultMap.put(redisTemplate.getStringSerializer()
								.deserialize(en.getKey()), Long
								.valueOf(redisTemplate.getStringSerializer()
										.deserialize(en.getValue())));
					}

					return resultMap;
				}
				return new HashMap<String, Long>();
			}

		});
	}

	@Override
	public void save(String key, Serializable r) {
		ValueOperations<String, Serializable> valueOper = redisTemplate.opsForValue();
		valueOper.set(key, r);
	}

	@Override
	public Long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	@Override
	public void saveHash(String key, Map<String, Object> map, long timeout,TimeUnit unit) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		for (Map.Entry<String, Object> en : map.entrySet()) {
			hashOperations.put(key, en.getKey(), en.getValue());
			hashOperations.getOperations().boundHashOps(key).expire(timeout, unit);
		}
	}

	public Map<Object, Object> getFieldValueForHash(String key) {
		Map<Object, Object> o = redisTemplate.boundHashOps(key).entries();
		return o;
	}

	@Override
	public void hincrebyForHash(String key, Object field, long increment,
			long timeout, TimeUnit unit) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		hashOperations.increment(key, field, increment);
		hashOperations.getOperations().boundHashOps(key).expire(timeout, unit);
	}



}
