package com.bd17kaka.kankantu.po;

/**
 * token信息
 */
public class Token implements java.io.Serializable {
	private static final long serialVersionUID = -1744081377872974319L;

	// sinaweibo里的 userid和username
	private String userId;
	private String userName;
	private String token;
	private String expire;

	public Token() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Token(String userId, String userName, String token, String expire) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.token = token;
		this.expire = expire;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	@Override
	public String toString() {
		return "Token [userId=" + userId + ", userName=" + userName
				+ ", token=" + token + ", expire=" + expire + "]";
	}
}
