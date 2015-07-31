package com.netease.yixing.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.hash.DecoratingStringHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.JacksonHashMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.collections.DefaultRedisMap;
import org.springframework.stereotype.Repository;

import com.netease.yixing.cache.redis.AbstractBaseRedisDao;
import com.netease.yixing.dao.ITokenRedisDao;

@Repository
public class TokenRedisDao extends AbstractBaseRedisDao<Object, Object>implements ITokenRedisDao {

    private  Class<Serializable> entityClass = (Class<Serializable>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];  
    
    private final HashMapper<Serializable, String, String> entityMapper = new DecoratingStringHashMapper<Serializable>(new JacksonHashMapper<Serializable>((Class<Serializable>) entityClass));
	
	
	@Override
	public Object getTokenName(final String tokenName) {
		Object result = redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {				
				HashOperations opsHash = redisTemplate.opsForHash();							
				return opsHash.entries(tokenName);
			}			
		});
		return result;
	}

	@Override
	public boolean saveToken(final String tokenName, final HashMap<String, String> tokenKeyValMap) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				HashOperations opsHash = redisTemplate.opsForHash();
				opsHash.putAll(tokenName, entityMapper.toHash(tokenKeyValMap));
				return true;
			}
			
		});
		return result;
	}

}
