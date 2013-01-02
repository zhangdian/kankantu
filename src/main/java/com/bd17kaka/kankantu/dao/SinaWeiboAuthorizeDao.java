package com.bd17kaka.kankantu.dao;

import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.User;

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
}
