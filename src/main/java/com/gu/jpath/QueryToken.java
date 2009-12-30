package com.gu.jpath;

import com.google.gson.JsonElement;

import java.util.List;

interface QueryToken {
	List<JsonElement> navigate(JsonElement currentElement);
}
