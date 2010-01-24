package com.gu.jpath;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.io.Reader;
import java.io.StringReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class JPath {
	private List<QueryToken> queryTokens;

	public JPath(String path) {
		JPathLexer lexer = new JPathLexer(new ANTLRStringStream(path));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JPathParser parser = new JPathParser(tokens);
		
		try {
			queryTokens = parser.query();
		} catch (RecognitionException e) {
			throw new JPathException(e);
		}
	}

	public JsonElement elementFrom(Reader reader) {
		return elementFrom(new JsonParser().parse(reader));
	}
	
	public JsonElement elementFrom(JsonElement element) {
		return from(element).get(0);
	}
	
	public List<JsonElement> from(JsonElement element) {
		List<JsonElement> currentElements = new ArrayList<JsonElement>();
		List<JsonElement> foundElements = new ArrayList<JsonElement>(Arrays.asList(element));
		for (QueryToken token: queryTokens) {
			currentElements = new ArrayList<JsonElement>(foundElements);
			foundElements = new ArrayList<JsonElement>();
			for (JsonElement currentElement : currentElements) {
				foundElements.addAll(token.navigate(currentElement));
			}
		}
		return foundElements;
	}
}
