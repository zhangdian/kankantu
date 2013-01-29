package com.bd17kaka.kankantu.po;

/**
 * token信息
 */
public class Token implements java.io.Serializable {
	private static final long serialVersionUID = -1744081377872974319L;

	/**
	 * 我们系统里的userid
	 */
	private String userId;

	/**
	 * sinaweibo里的 userid和username
	 */
	private String uid;
	private String userName;
	private String token;
	private String expire;

	public Token() {
		super();
	}

	public Token(String userId, String uid, String userName, String token,
			String expire) {
		super();
		this.userId = userId;
		this.uid = uid;
		this.userName = userName;
		this.token = token;
		this.expire = expire;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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
		return "Token [userId=" + userId + ", uid=" + uid + ", userName="
				+ userName + ", token=" + token + ", expire=" + expire + "]";
	}

}
