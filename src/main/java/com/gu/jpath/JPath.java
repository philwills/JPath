package com.gu.jpath;

import com.google.gson.JsonElement;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

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

	public JsonElement elementFrom(JsonElement element) {
		return elementsFrom(element).get(0);
	}
	
	public List<JsonElement> elementsFrom(JsonElement element) {
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
