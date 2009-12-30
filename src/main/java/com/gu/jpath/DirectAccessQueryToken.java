package com.gu.jpath;

import com.google.gson.JsonElement;

import java.util.List;
import java.util.Arrays;

public class DirectAccessQueryToken implements QueryToken {
	private String identifier;

	public DirectAccessQueryToken(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public List<JsonElement> navigate(JsonElement currentElement) {
		return Arrays.asList(currentElement.getAsJsonObject().get(identifier));
	}

	public String toString() {
		return identifier;
	}
}
