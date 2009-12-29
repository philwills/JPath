package com.gu.jpath;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JPath {
	private JsonElement root;

	public JPath(InputStream jsonStream) {
		JsonParser parser = new JsonParser();
		root = parser.parse(new InputStreamReader(jsonStream));
	}

	public JsonElement forPath(String path) {
		JPathLexer lexer = new JPathLexer(new ANTLRStringStream(path));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JPathParser parser = new JPathParser(tokens);
		List<QueryToken> queryTokens = null;
		try {
			queryTokens = parser.query();
		} catch (RecognitionException e) {
			throw new JPathException(e);
		}

		JsonElement currentElement = root;
		for (QueryToken token: queryTokens) {
			currentElement = token.navigate(currentElement);
		}
		return currentElement;
	}
}
