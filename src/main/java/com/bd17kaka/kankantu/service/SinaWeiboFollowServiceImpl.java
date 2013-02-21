package com.bd17kaka.kankantu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.SinaWeiboFollowDao;
import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.SinaweiboUserNotFoundException;
import com.bd17kaka.kankantu.exception.UserNotAuthorizeException;
import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

@Service(value = "sinaWeiboFollowService")
public class SinaWeiboFollowServiceImpl implements SinaWeiboFollowService {

	@Resource(name = "sinaWeiboFollowDao")
	private SinaWeiboFollowDao sinaWeiboFollowDao;
	
	@Resource(name = "sinaWeiboRecommendUserService")
	private SinaWeiboRecommendUserService sinaWeiboRecommendUserService;
	
	@Override
	public void addFollow(String userId, String uid, String tagName) throws WeiboException, KankantuException, UserNotAuthorizeException {
		
		// 获取要关注的用户的信息
		SinaWeiboRecommendUser user = sinaWeiboRecommendUserService.getByUid(userId, uid);
		if (null == user) {
			throw new SinaweiboUserNotFoundException(uid);
		}
		// 保存关注的用户
		sinaWeiboFollowDao.insert(userId, user, tagName);
	}

	@Override
	public void deleteFollow(String userId, String uid, String tagName)
			throws WeiboException, KankantuException, UserNotAuthorizeException {
		// 获取要关注的用户的信息
		SinaWeiboRecommendUser user = sinaWeiboRecommendUserService.getByUid(userId, uid);
		if (null == user) {
			throw new SinaweiboUserNotFoundException(uid);
		}
		// 保存关注的用户
		sinaWeiboFollowDao.delete(userId, user, tagName);		
	}

	@Override
	public List<SinaWeiboRecommendUser> list(String userId, String tagName) throws KankantuException, UserNotAuthorizeException {
		Set<String> set = sinaWeiboFollowDao.list(userId, tagName);
		if (null == set) {
			return null;
		}
		List<SinaWeiboRecommendUser> list = new ArrayList<SinaWeiboRecommendUser>();
		for (String uid : set) {
			try {
				SinaWeiboRecommendUser user = sinaWeiboRecommendUserService.getByUid(userId, uid);
				if (null == user) {
					continue;
				}
				list.add(user);
				
			} catch (WeiboException e) {
				continue;
			}
		}
		return list;
	}
}
