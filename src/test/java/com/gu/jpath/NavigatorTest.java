package com.gu.jpath;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static com.gu.jpath.Navigator.*;

import static java.util.Arrays.asList;

public class NavigatorTest {
	@Test public void Extract_single_string_from_JSON_with_javascript_style_syntax() {
		assertThat(from("{\"wensleydale\":{\"texture\": \"crumbly\"}}").stringAt("wensleydale.texture"), equalTo("crumbly"));
	}
	
	@Test public void Extract_multiple_strings_from_JSON_using_star() {
		assertThat(from(NavigatorTest.class.getResourceAsStream("menu.json")).stringsAt("menu.popup.menuitem[*].value"), equalTo(asList("New", "Open", "Close")));
	}
}
