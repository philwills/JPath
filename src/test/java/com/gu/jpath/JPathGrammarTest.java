package com.gu.jpath;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class JPathGrammarTest {

	@Test public void testSingleWord() throws IOException, RecognitionException {
		JPathParser parser = createParser("stuff");
		String result = parser.identifier();
		assertThat(result, equalTo("stuff"));
	}

	@Test
    public void testMultipleWords() throws IOException, RecognitionException {
        JPathParser parser = createParser("red.green.blue");
        List<String> methods = parser.query();
        assertThat(methods.size(), equalTo(3));
		assertThat(methods.get(0), equalTo("red"));
		assertThat(methods.get(1), equalTo("green"));
		assertThat(methods.get(2), equalTo("blue"));
    }

	 private JPathParser createParser(String testString) throws IOException {
    	CharStream stream = new ANTLRStringStream(testString);
    	JPathLexer lexer = new JPathLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JPathParser parser = new JPathParser(tokens);
        return parser;
    }
}
