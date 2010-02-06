package com.gu.jpath;

import org.junit.Test;
import org.junit.Before;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JPathTest {
	private JsonElement glossaryRoot;
	private JsonElement menuRoot;
	
	@Test public void shouldFindJSONContentBasedOnPath() {
		JPath jPath = new JPath("glossary.title");

		assertThat(jPath.from(glossaryRoot).get(0).getAsString(), equalTo("example glossary"));
	}

	@Test public void shouldFindJSONContentInArrayElement() {
		JPath jPath = new JPath("glossary.GlossDiv.GlossList.GlossEntry.GlossDef.GlossSeeAlso[1]");

		assertThat(jPath.from(glossaryRoot).get(0).getAsString(), equalTo("XML"));
	}
	
	@Test public void shouldFindAllJSONContentInArrayElement() {
		JPath jPath = new JPath("glossary.GlossDiv.GlossList.GlossEntry.GlossDef.GlossSeeAlso[*]");

		assertThat(jPath.from(glossaryRoot), equalTo(Arrays.asList((JsonElement)new JsonPrimitive("GML"), new JsonPrimitive("XML"))));
	}
	
	@Test public void shouldFindAllElementsFromNodesInsideArrayElements() {
		JPath jPath = new JPath("menu.popup.menuitem[*].value");

		assertThat(jPath.from(menuRoot), equalTo(Arrays.asList((JsonElement)new JsonPrimitive("New"), new JsonPrimitive("Open"), new JsonPrimitive("Close"))));	
	}

	@Before	public void buildJSON() {
		JsonParser parser = new JsonParser();
		InputStream glossaryJsonStream = JPathTest.class.getResourceAsStream("glossary.json");
		glossaryRoot = parser.parse(new InputStreamReader(glossaryJsonStream));
		InputStream menuJsonStream = JPathTest.class.getResourceAsStream("menu.json");
		menuRoot = parser.parse(new InputStreamReader(menuJsonStream));
	}
}
