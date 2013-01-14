package com.bd17kaka.kankantu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.UserDao;
import com.bd17kaka.kankantu.exception.SignupException;
import com.bd17kaka.kankantu.exception.UserNameExistException;
//import com.bd17kaka.kankantu.dao.UserDao;
import com.bd17kaka.kankantu.po.User;

@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	public Long signup(User user) {
		if (userDao.hasUserName(user.getUserName())) {
			throw new UserNameExistException(user.getUserName());
		}
		Long ret = userDao.insert(user); 
		if (0L == ret) {
			throw new SignupException(user.getUserName());
		}
		return ret;
	}

	@Override
	public User verifyUser(User user) {
		return userDao.hasUser(user);
	}
}

