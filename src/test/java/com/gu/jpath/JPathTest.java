package com.gu.jpath;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JPathTest {
	@Test public void shouldFindJSONContentBasedOnPath() {
		JPath jPath = new JPath(getCheeseStream());

		assertThat(jPath.forPath("search.count").getAsInt(), equalTo(7129));
	}

	@Test public void shouldFindJSONContentInArrayElement() {
		JPath jPath = new JPath(getCheeseStream());

		assertThat(jPath.forPath("search.results[0].id").getAsInt(), equalTo(356272128));
	}

	private InputStream getCheeseStream() {
		InputStream jsonStream = JPathTest.class.getResourceAsStream("cheese.json");
		return jsonStream;
	}
}
