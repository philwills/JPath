package com.gu.jpath

import org.scalatest.junit._
import org.junit.Test

import com.google.gson._

class DirectAccessQueryTokenTest extends JUnitSuite with ShouldMatchersForJUnit {

	@Test def shouldMoveDirectlyDownToNamedElement() {
		val jsonObject = new JsonObject()
		jsonObject.addProperty("number", 42)

		val queryToken = new DirectAccessQueryToken("number")

		queryToken.navigate(jsonObject)(0).getAsInt() should be (42)
	}
}

class ArrayAccessQueryTokenTest extends JUnitSuite with ShouldMatchersForJUnit {

	@Test def shouldNavigateToArrayEntry() {
		val jsonRoot = new JsonObject()
		val jsonArray = new JsonArray()
		val jsonLeaf = new JsonObject()
		jsonArray.add(jsonLeaf)
		jsonRoot.add("array", jsonArray)

		val queryToken = new ArrayAccessQueryToken("array", 0)

		queryToken.navigate(jsonRoot)(0) should be (jsonLeaf)
	}
}

class WholeArrayQueryTokenTest extends JUnitSuite with ShouldMatchersForJUnit {

	@Test def shouldNavigateToArrayEntry() {
		val jsonRoot = new JsonObject();
		val jsonArray = new JsonArray();
		val jsonLeaf = new JsonObject();
		val jsonLeaf2 = new JsonObject();
		jsonArray.add(jsonLeaf);
		jsonArray.add(jsonLeaf2);
		jsonRoot.add("array", jsonArray);

		val queryToken = new WholeArrayQueryToken("array");

		queryToken.navigate(jsonRoot) should be (List(jsonLeaf, jsonLeaf2))
	}
}
