package com.gu.jpath;

import com.google.gson.JsonElement;

import java.util.List;
import java.util.ArrayList;

public class WholeArrayQueryToken implements QueryToken {
	private String identifier;

	public WholeArrayQueryToken(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public List<JsonElement> navigate(JsonElement currentElement) {
		List<JsonElement> elements = new ArrayList<JsonElement>();
		for (JsonElement element : currentElement.getAsJsonObject().get(identifier).getAsJsonArray()) {
			elements.add(element);
		}
		return elements;
	}

	public String toString() {
		return identifier + "[*]";
	}
}
