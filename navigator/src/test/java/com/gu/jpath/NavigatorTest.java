package com.gu.jpath;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import static com.gu.jpath.Navigator.*;

import static java.util.Arrays.asList;

public class NavigatorTest {
	@Test public void Extract_a_single_string_from_JSON_with_javascript_style_syntax() {
		assertThat(from("{\"wensleydale\":{\"texture\": \"crumbly\"}}").stringAt("wensleydale.texture"), equalTo("crumbly"));
	}
	
	@Test public void Extract_multiple_strings_from_JSON_using_star() {
		assertThat(from(NavigatorTest.class.getResourceAsStream("menu.json")).stringsAt("menu.popup.menuitem[*].value"), hasItems("New", "Open", "Close"));
	}
	
	
	@Test public void Extract_int_from_JSON() {
		assertThat(from(NavigatorTest.class.getResourceAsStream("widget.json")).intAt("widget.window.width"), equalTo(500));
	}
	
	@Test public void Get_the_raw_GSON_element() {
		assertThat(from(NavigatorTest.class.getResourceAsStream("widget.json")).elementAt("widget.window.width"), equalTo((JsonElement)new JsonPrimitive(500)));
	}
}
