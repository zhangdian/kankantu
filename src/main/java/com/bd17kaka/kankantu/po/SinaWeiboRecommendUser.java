package com.bd17kaka.kankantu.po;

/**
 * 推荐的sina微博用户信息
 */
public class SinaWeiboRecommendUser implements java.io.Serializable {
	private static final long serialVersionUID = -1744081377872974319L;
	/**
	 * sina微博里的用户id
	 */
	private String userId;
	/**
	 * sina微博里的用户名称
	 */
	private String userName;
	/**
	 * follows_count粉丝数
	 */
	private String followCount;
	/**
	 * 头像地址
	 */
	private String profileImageURL;

	public SinaWeiboRecommendUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SinaWeiboRecommendUser(String userId, String userName, String followCount,
			String profileImageURL) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.followCount = followCount;
		this.profileImageURL = profileImageURL;
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

	public String getFollowCount() {
		return followCount;
	}

	public void setFollowCount(String followCount) {
		this.followCount = followCount;
	}

	public String getProfileImageURL() {
		return profileImageURL;
	}

	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}

	@Override
	public String toString() {
		return "RecommendUser [userId=" + userId + ", userName=" + userName
				+ ", followCount=" + followCount + ", profileImageURL="
				+ profileImageURL + "]";
	}
}
