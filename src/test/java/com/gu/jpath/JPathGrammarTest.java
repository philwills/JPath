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

	@Test public void shouldSplitOnDots() throws IOException, RecognitionException {
        JPathParser parser = createParser("red.green.blue");
        List<QueryItem> methods = parser.query();
        assertThat(methods.size(), equalTo(3));
		assertThat(methods.get(0).toString(), equalTo("red"));
		assertThat(methods.get(1).toString(), equalTo("green"));
		assertThat(methods.get(2).toString(), equalTo("blue"));
    }

	@Test public void shouldDistinguishArrayAccess() throws IOException, RecognitionException {
		JPathParser parser = createParser("red.green[2].blue");
        List<QueryItem> methods = parser.query();
        assertThat(methods.size(), equalTo(3));
		assertThat(methods.get(0).toString(), equalTo("red"));
		assertThat(methods.get(1).toString(), equalTo("green[2]"));
		assertThat(methods.get(2).toString(), equalTo("blue"));
	}

	 private JPathParser createParser(String testString) throws IOException {
    	CharStream stream = new ANTLRStringStream(testString);
    	JPathLexer lexer = new JPathLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JPathParser parser = new JPathParser(tokens);
        return parser;
    }
}
