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

	def elementFrom(reader: Reader) : JsonElement = {
		elementFrom(new JsonParser().parse(reader))
	}
	
	def elementFrom(element: JsonElement) : JsonElement = {
		from(element).get(0)
	}
	
	def from(element: JsonElement) : java.util.List[JsonElement] = {
		var currentElements = List[JsonElement]()
		var foundElements = List(element)
		
		for (token <- queryTokens) {
			currentElements = foundElements
			foundElements = List[JsonElement]()
			for (element <- currentElements) {
				foundElements = foundElements ++ new ScalaJavaIterable(token.navigate(element))
			}
		}
		
		scala2JavaList(foundElements)
	}
	
	def scala2JavaList(scalaList: List[JsonElement]): java.util.List[JsonElement] = {
		var javaList = new ArrayList[JsonElement]()
		scalaList foreach(javaList.add(_))
		javaList
	}
}

class ScalaJavaIterable[T](private val iterable: java.lang.Iterable[T]) extends Iterable[T] {
	def elements(): Iterator[T] = new ScalaJavaIterator(iterable.iterator())
}


class ScalaJavaIterator[T](private val iterator: java.util.Iterator[T]) extends Iterator[T] {
  def hasNext: Boolean = iterator hasNext

  def next: T = iterator next
}

