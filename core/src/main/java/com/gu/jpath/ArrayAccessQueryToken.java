package com.gu.jpath;

import com.google.gson.JsonElement;

import java.util.List;
import java.util.Arrays;

class ArrayAccessQueryToken implements QueryToken {
	private String identifier;
	private Integer index;

	public ArrayAccessQueryToken(String identifier, Integer index) {
		this.identifier = identifier;
		this.index = index;
	}

	@Override
	public List<JsonElement> navigate(JsonElement currentElement) {
		return Arrays.asList(currentElement.getAsJsonObject().get(identifier).getAsJsonArray().get(index));
	}

	public String toString() {
		return identifier + "[" + index + "]";
	}
}
