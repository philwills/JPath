package com.gu.jpath;

import org.junit.Test;
import org.junit.Before;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JPathTest {
	private JsonElement exampleRoot;
	
	@Test public void shouldFindJSONContentBasedOnPath() {
		JPath jPath = new JPath("glossary.title");

		assertThat(jPath.fromElement(exampleRoot).getAsString(), equalTo("example glossary"));
	}

	@Test public void shouldFindJSONContentInArrayElement() {
		JPath jPath = new JPath("glossary.GlossDiv.GlossList.GlossEntry.GlossDef.GlossSeeAlso[1]");

		assertThat(jPath.fromElement(exampleRoot).getAsString(), equalTo("XML"));
	}

	@Before
	public void buildExampleRoot() {
		InputStream jsonStream = JPathTest.class.getResourceAsStream("example.json");
		JsonParser parser = new JsonParser();
		exampleRoot = parser.parse(new InputStreamReader(jsonStream));
	}
}
