package com.gu.jpath;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DirectAccessQueryTokenTest {
	@Test public void shouldMoveDirectlyDownToNamedElement() {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("number", 42);

		DirectAccessQueryToken queryToken = new DirectAccessQueryToken("number");

		assertThat(queryToken.navigate(jsonObject).get(0).getAsInt(), equalTo(42));
	}
}
