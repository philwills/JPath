package com.gu.jpath;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.common.base.Function;

import java.util.*;

import java.io.*;

import static com.google.common.collect.Iterables.*;

public class Navigator {
	private static Function<JsonElement, String> stringify = new Function<JsonElement, String>(){
		public String apply(JsonElement element) { return element.getAsString(); } 
	};

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
	
	public Iterable<String> stringsAt(String path) {
		return transform(new JPath(path).from(root), stringify);
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
