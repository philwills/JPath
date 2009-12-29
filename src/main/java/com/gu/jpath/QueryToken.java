package com.gu.jpath;

import com.google.gson.JsonElement;

public interface QueryToken {
	JsonElement navigate(JsonElement currentElement);
}
