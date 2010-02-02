package com.gu.jpath

import org.scalatest.junit._
import org.junit.Test

class JsonArgoGrammarTest extends JUnitSuite with ShouldMatchersForJUnit {

	@Test def shouldSplitOnDots() {
		val tokens = JsonArgoGrammar.tokens("red.green.blue")
		tokens.length should be (3)
		tokens(0).toString should be ("red")
		tokens(1).toString should be ("green")
		tokens(2).toString should be ("blue")
    }

	@Test def shouldDistinguishArrayAccess() {
		val tokens = JsonArgoGrammar.tokens("red.green[2].blue")
		tokens.length should be (3)
		tokens(0).toString should be ("red")
		tokens(1).toString should be ("green[2]")
		tokens(2).toString should be ("blue")
	}
}
