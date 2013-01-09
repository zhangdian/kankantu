package com.bd17kaka.kankantu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.SinaWeiboGroupInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.Friendships;

/**
 * 队列信息DAO实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboGroupInfoDao")
public class SinaWeiboGroupInfoDaoImpl extends RedisUtils implements SinaWeiboGroupInfoDao {

	private static String prefix = "sinaweibo:group:";

	@Override
	public List<SinaWeiboGroupInfo> listSinaWeiboGroups(Token token) {
		return null;
	}
}
