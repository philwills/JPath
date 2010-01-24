package com.gu.jpath;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.List;
import java.util.ArrayList;

import java.io.*;

public class Navigator {
	private JsonElement root;

	public Navigator(String json) {
		this(new JsonParser().parse(json));
	}
	
	public Navigator(InputStream stream) {
		this(new InputStreamReader(stream));
	}
	
	public Navigator(Reader reader) {
		this(new JsonParser().parse(reader));
	}
		
	public Navigator(JsonElement root) {
		this.root = root;
	}
	
	public String stringAt(String path) {
		return new JPath(path).from(root).get(0).getAsString();
	}
	
	public List<String> stringsAt(String path) {
		List<String> strings = new ArrayList<String>();
		for (JsonElement element:  new JPath(path).from(root)) {
			strings.add(element.getAsString());
		}
		return strings;
	}

	public static Navigator from(String json) {
		return new Navigator(json);
	}
	
	public static Navigator from(InputStream stream) {
		return new Navigator(stream);
	}
	
	public static Navigator from(Reader reader) {
		return new Navigator(reader);
	}
}