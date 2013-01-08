package com.bd17kaka.kankantu.po;

/**
 * 用户新浪微博分组信息
 */
public class SinaWeiboGroupInfo implements java.io.Serializable {
	private static final long serialVersionUID = -1744081377872974319L;
	/**
	 * 分组id，微博里的分组id
	 */
	private int groupId;
	/**
	 * group名称,微博里的名字
	 */
	private String groupName;
	/**
	 * desc
	 */
	private String desc;

	public SinaWeiboGroupInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SinaWeiboGroupInfo(int groupId, String groupName, String desc) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.desc = desc;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "SinaWeiboGroupInfo [groupId=" + groupId + ", groupName="
				+ groupName + ", desc=" + desc + "]";
	}
}
