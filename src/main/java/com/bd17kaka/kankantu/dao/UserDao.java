package com.bd17kaka.kankantu.dao;

import com.bd17kaka.kankantu.po.User;

/**
 * 队列信息Dao接口
 * @author bd17kaka
 */
public interface UserDao {
	/**
	 * 插入用户信息
	 * @param user
	 * @return
	 */
	Long insert(User user);
	
	/**
	 * 某用户名是否存在
	 * @param userName
	 * @return
	 */
	boolean hasUserName(String userName);
	
	/**
	 * 该用户是否存在
	 * @param user
	 * @return
	 */
	User hasUser(User user);
}
