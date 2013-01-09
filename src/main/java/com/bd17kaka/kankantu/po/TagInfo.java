package com.bd17kaka.kankantu.po;

/**
 * tag信息
 */
public class TagInfo implements java.io.Serializable {
	private static final long serialVersionUID = -1744081377872974319L;
	private String tagName;

	public TagInfo() {
		super();
	}

	public TagInfo(String tagName) {
		super();
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "Tag [tagName=" + tagName + "]";
	}
}
