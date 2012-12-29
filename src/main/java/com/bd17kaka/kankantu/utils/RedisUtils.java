package com.bd17kaka.kankantu.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisUtils {
	private static String redisHost = "69.85.92.97";
	private static int redisPort = 6379;
	private static String password = "199111@0Smy";
	
	public static Jedis getRedisConn() {
		JedisShardInfo params = new JedisShardInfo(redisHost, redisPort);
		params.setPassword(password);
		Jedis jedis = new Jedis(params);
		return jedis;
	}
}
