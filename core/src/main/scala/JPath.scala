package com.gu.jpath

import com.google.gson.JsonElement;

class JPath(path: String) {
	val queryTokens = JsonArgoGrammar.tokens(path)

	def from(element: JsonElement) : java.util.List[JsonElement] = descend(queryTokens, element)
	
	private def descend(tokens: List[QueryToken], element: JsonElement) : List[JsonElement] = {
		if (tokens.isEmpty) List(element) else {
			for { 
				navigatedElement <- tokens.head.navigate(element)
				descendedElement <- descend(tokens.drop(1), navigatedElement)
			} yield descendedElement
		}
	}
	
	implicit def scalaToJavaList(scalaList: List[JsonElement]): java.util.List[JsonElement] = {
		var javaList = new java.util.ArrayList[JsonElement]()
		scalaList foreach(javaList.add(_))
		javaList
	}
}
