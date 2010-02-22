package com.gu.jpath

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.Reader;
import java.io.StringReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;

class JPath(path: String) {
	val queryTokens = JsonArgoGrammar.tokens(path)

	def from(element: JsonElement) = {
		var currentElements = List[JsonElement]()
		var foundElements = List(element)
		
		for (token <- queryTokens) {
			currentElements = foundElements
			foundElements = for {
				element <- currentElements
				navigatedElement <- token.navigate(element)
			} yield navigatedElement
		}
		
		scala2JavaList(foundElements)
	}
	
	def scala2JavaList(scalaList: List[JsonElement]): java.util.List[JsonElement] = {
		var javaList = new ArrayList[JsonElement]()
		scalaList foreach(javaList.add(_))
		javaList
	}
}
