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

	def from(element: JsonElement) : java.util.List[JsonElement] = descend(queryTokens, element)
	
	private def descend(tokens: List[QueryToken], element: JsonElement) : List[JsonElement] = {
		if (tokens.isEmpty) 
			List(element) 
		else {
			for { 
				navigatedElement <- tokens.head.navigate(element)
				descendedElement <- descend(tokens.drop(1), navigatedElement)
			} yield descendedElement
		}
	}
	
	implicit def scalaToJavaList(scalaList: List[JsonElement]): java.util.List[JsonElement] = {
		var javaList = new ArrayList[JsonElement]()
		scalaList foreach(javaList.add(_))
		javaList
	}
}
