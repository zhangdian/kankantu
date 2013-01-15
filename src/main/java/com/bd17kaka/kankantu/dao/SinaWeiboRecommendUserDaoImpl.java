package com.bd17kaka.kankantu.dao;

import org.springframework.stereotype.Repository;

/**
 * 新浪微博推荐用户信息DAO实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboRecommendUserDao")
public class SinaWeiboRecommendUserDaoImpl extends RedisUtils implements SinaWeiboRecommendUserDao {

}
