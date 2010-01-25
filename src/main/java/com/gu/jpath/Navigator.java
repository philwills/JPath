package com.gu.jpath;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.common.base.Function;

import java.util.*;

import java.io.*;

import static com.google.common.collect.Iterables.*;

public class Navigator {
	private static Function<JsonElement, String> asString = new Function<JsonElement, String>(){
		public String apply(JsonElement element) { return element.getAsString(); } 
	};
	private static Function<JsonElement, Integer> asInt = new Function<JsonElement, Integer>(){
		public Integer apply(JsonElement element) { return element.getAsInt(); } 
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
		return unique(path).getAsString();
	}
	
	public Iterable<String> stringsAt(String path) {
		return transform(all(path), asString);
	}
	
	public Integer intAt(String path) {
		return unique(path).getAsInt();
	}
	
	public Iterable<Integer> intsAt(String path) {
		return transform(all(path), asInt);
	}
	
	private JsonElement unique(String path) {
		return all(path).get(0);
	}

	private List<JsonElement> all(String path) {
		return new JPath(path).from(root);
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
