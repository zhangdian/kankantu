package com.bd17kaka.kankantu.service;

import java.util.List;

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

public interface SinaWeiboRecommendUserService {
	
	/**
	 * 获取指定tagName的推荐用户列表
	 * @param userId
	 * @param tagName
	 * @return
	 * @throws WeiboException 
	 */
	List<SinaWeiboRecommendUser> listRecommendUser(String userId, String tagName) throws WeiboException;
}
