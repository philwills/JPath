package com.gu.jpath;

public class DirectAccessQueryItem implements QueryItem {
	private String identifier;

	public DirectAccessQueryItem(String identifier) {
		this.identifier = identifier;
	}

	public String toString() {
		return identifier;
	}
}
