package com.gu.jpath;

import com.google.gson.JsonElement;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.util.List;

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

	public JsonElement fromElement(JsonElement element) {
		JsonElement currentElement = element;
		for (QueryToken token: queryTokens) {
			currentElement = token.navigate(currentElement);
		}
		return currentElement;
	}
}
