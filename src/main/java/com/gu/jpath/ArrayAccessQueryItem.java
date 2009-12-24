package com.gu.jpath;

public class ArrayAccessQueryItem implements QueryItem {
	private String identifier;
	private Integer index;

	public ArrayAccessQueryItem(String identifier, Integer index) {
		this.identifier = identifier;
		this.index = index;
	}

	public String toString() {
		return identifier + "[" + index + "]";
	}
}
