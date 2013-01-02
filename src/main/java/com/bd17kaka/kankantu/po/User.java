package com.bd17kaka.kankantu.po;

/**
 * 用户信息
 */
public class User implements java.io.Serializable {
	private static final long serialVersionUID = -1744081377872974319L;
	/**
	 * 用户id
	 */
	private int userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户邮件地址
	 */
	private String email;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String userName, String password, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + "]";
	}
}
