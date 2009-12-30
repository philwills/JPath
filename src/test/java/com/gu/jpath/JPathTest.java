package com.gu.jpath;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JPathTest {
	@Test public void shouldFindJSONContentBasedOnPath() {
		JPath jPath = new JPath(getCheeseStream());

		assertThat(jPath.forPath("glossary.title").getAsString(), equalTo("example glossary"));
	}

	@Test public void shouldFindJSONContentInArrayElement() {
		JPath jPath = new JPath(getCheeseStream());

		assertThat(jPath.forPath("glossary.GlossDiv.GlossList.GlossEntry.GlossDef.GlossSeeAlso[1]").getAsString(), equalTo("XML"));
	}

	private InputStream getCheeseStream() {
		InputStream jsonStream = JPathTest.class.getResourceAsStream("example.json");
		return jsonStream;
	}
}
