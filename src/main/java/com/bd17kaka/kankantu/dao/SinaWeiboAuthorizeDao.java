package com.bd17kaka.kankantu.dao;

import java.util.List;

import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;

/**
 * 新浪微博授权信息
 * @author bd17kaka
 */
public interface SinaWeiboAuthorizeDao {
	/**
	 * 插入用户信息
	 * @param user
	 * @return
	 */
	Long insert(SinaWeiboAuthorizeInfo info, String userId);
	
	/**
	 * 获取指定userId用户的所有授权历史信息
	 * @param userId
	 * @return
	 */
	List<SinaWeiboAuthorizeInfo> list(String userId);
}
