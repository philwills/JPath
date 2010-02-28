package com.gu.jpath

import com.google.gson.JsonElement;

class JPath(path: String) {
	val queryTokens = JsonArgoGrammar.tokens(path)

	def from(startElement: JsonElement) : java.util.List[JsonElement] = {
		def descend(tokens: List[QueryToken], element: JsonElement) : List[JsonElement] = 
			tokens match {
				case List() => List(element) 
				case topToken :: remainingTokens => for { 
						navigatedElement <- topToken.navigate(element)
						descendedElement <- descend(remainingTokens, navigatedElement)
					} yield descendedElement
			}
		descend(queryTokens, startElement)
	}
	
	implicit def scalaToJavaList(scalaList: List[JsonElement]): java.util.List[JsonElement] = {
		var javaList = new java.util.ArrayList[JsonElement]()
		scalaList foreach(javaList.add(_))
		javaList
	}
}
