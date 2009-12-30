package com.gu.jpath;

import com.google.gson.JsonElement;

import java.util.List;

public interface QueryToken {
	List<JsonElement> navigate(JsonElement currentElement);
}
