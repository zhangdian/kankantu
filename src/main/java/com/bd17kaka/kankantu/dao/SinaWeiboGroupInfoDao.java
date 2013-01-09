package com.bd17kaka.kankantu.dao;

import java.util.List;

import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.SinaWeiboGroupInfo;
import com.bd17kaka.kankantu.po.Token;

/**
 * 用户的sina微博分组信息Dao
 * @author bd17kaka
 */
public interface SinaWeiboGroupInfoDao {
	
	///////////////////////////////////////////////////////////////////////////
	// 获取用户sina微博分组的相关信息
	///////////////////////////////////////////////////////////////////////////
	/**
	 * 获取所有分组信息
	 * @param token
	 * @return
	 */
	List<SinaWeiboGroupInfo> listSinaWeiboGroups(Token token);
}
