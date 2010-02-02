package com.gu.jpath;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ArrayAccessQueryTokenTest {
	@Test public void shouldNavigateToArrayEntry() throws Exception {
		JsonObject jsonRoot = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		JsonElement jsonLeaf = new JsonObject();
		jsonArray.add(jsonLeaf);
		jsonRoot.add("array", jsonArray);

		ArrayAccessQueryToken queryToken = new ArrayAccessQueryToken("array", 0);

		assertThat(queryToken.navigate(jsonRoot).get(0), equalTo(jsonLeaf));
	}
}
