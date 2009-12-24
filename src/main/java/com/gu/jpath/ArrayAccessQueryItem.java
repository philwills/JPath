package com.gu.jpath;

public class ArrayAccessQueryItem implements QueryItem {
	private Integer index;

	public ArrayAccessQueryItem(Integer index) {
		this.index = index;
	}

	public String toString() {
		return "[" + index + "]";
	}
}
