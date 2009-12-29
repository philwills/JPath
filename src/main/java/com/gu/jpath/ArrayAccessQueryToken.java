package com.gu.jpath;

import com.google.gson.JsonElement;

public class ArrayAccessQueryToken implements QueryToken {
	private String identifier;
	private Integer index;

	public ArrayAccessQueryToken(String identifier, Integer index) {
		this.identifier = identifier;
		this.index = index;
	}

	@Override
	public JsonElement navigate(JsonElement currentElement) {
		return currentElement.getAsJsonObject().get(identifier).getAsJsonArray().get(index);
	}

	public String toString() {
		return identifier + "[" + index + "]";
	}
}
