package com.bd17kaka.kankantu.service;

import com.bd17kaka.kankantu.po.User;

public interface UserService {
	
	/**
	 * 用户注册
	 * @param user
	 */
	void signup(User user);
	
	/**
	 * 用户是否存在
	 * @param user
	 * @return TODO
	 */
	User verifyUser(User user);
}
