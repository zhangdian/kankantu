package com.bd17kaka.kankantu.dao;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.po.User;

/**
 * 队列信息DAO实现
 * @author bd17kaka
 */
@Repository(value="userDao")
public class UserDaoImpl extends RedisUtils implements UserDao {

	private static String prefix = "user:";
	@Override
	public Long insert(User user) {
		ShardedJedis jedis =  getConnection(); 
		// 获取一个id
		Long id = jedis.incr(prefix + "maxid");
		// 插入用户的信息，id，username，password，email
		if (id < 0L) {
			return id;
		}
		// 添加用户各个字段的信息
		String keyPrefix = prefix + id.toString() + ":";
		jedis.set(keyPrefix + "username", user.getUserName());
		jedis.set(keyPrefix + "password", user.getPassword());
		jedis.set(keyPrefix + "email", user.getEmail());
		keyPrefix = prefix + user.getUserName() + ":";
		jedis.set(keyPrefix + "userid", id.toString());
		
		// 添加用户id和用户名称到user:ids和user:usernames
		jedis.sadd(prefix + "userids", id.toString());
		jedis.sadd(prefix + "usernames", user.getUserName());
		
		return id;
	}
	@Override
	public boolean hasUserName(String userName) {
		ShardedJedis jedis =  getConnection(); 
		String key = prefix + "usernames";
		return jedis.sismember(key, userName);
	}
	@Override
	public User hasUser(User user) {
		// 首先判断用户名是否存在
		if (!hasUserName(user.getUserName())) {
			return null;
		}
		ShardedJedis jedis =  getConnection(); 
		// 获取用户名对于的userid
		String key = prefix + user.getUserName() + ":userid";
		String userId = jedis.get(key);
		// 判断用户密码是否匹配
		key = prefix + userId + ":password";
		String password = jedis.get(key);
		if (!password.equals(user.getPassword())) {
			return null;
		}
		// 获取用户其他信息，比如email
		key = prefix + userId + ":email";
		String email = jedis.get(key);
		
		return new User(Integer.parseInt(userId), user.getUserName(), "", email);
	}
}
