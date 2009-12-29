package com.gu.jpath;

import com.google.gson.JsonElement;

public class DirectAccessQueryToken implements QueryToken {
	private String identifier;

	public DirectAccessQueryToken(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public JsonElement navigate(JsonElement currentElement) {
		return currentElement.getAsJsonObject().get(identifier);
	}

	public String toString() {
		return identifier;
	}
}
