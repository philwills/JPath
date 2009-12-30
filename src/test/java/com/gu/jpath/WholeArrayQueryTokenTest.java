package com.gu.jpath;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class WholeArrayQueryTokenTest {
	@Test public void shouldNavigateToArrayEntry() throws Exception {
		JsonObject jsonRoot = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		JsonElement jsonLeaf = new JsonObject();
		JsonElement jsonLeaf2 = new JsonObject();
		jsonArray.add(jsonLeaf);
		jsonArray.add(jsonLeaf2);
		jsonRoot.add("array", jsonArray);

		WholeArrayQueryToken queryToken = new WholeArrayQueryToken("array");

		assertThat(queryToken.navigate(jsonRoot), equalTo(Arrays.asList(jsonLeaf, jsonLeaf2)));
	}
}
