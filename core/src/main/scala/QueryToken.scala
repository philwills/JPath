package com.gu.jpath

import com.google.gson.JsonElement

abstract class QueryToken(identifier: String) {
	def navigate(element: JsonElement) : List[JsonElement] = from(element.getAsJsonObject().get(identifier))
	def from(element: JsonElement) : List[JsonElement]
}

object QueryToken {
	private class DirectAccessQueryToken(identifier: String) extends QueryToken(identifier) {
		override def from(element: JsonElement) = List(element)
		override def toString = identifier
	}

	private class ArrayAccessQueryToken(identifier: String, index: Int) extends QueryToken(identifier) {
		override def from(element: JsonElement) = List(element.getAsJsonArray().get(index))
		override def toString = identifier + "[" + index + "]"
	}

	private class WholeArrayQueryToken(identifier: String) extends QueryToken(identifier) {
		override def from(element: JsonElement) = List.fromIterator(element.getAsJsonArray.iterator)
		override def toString = identifier + "[*]"
	}

	def apply(identifier: String) : QueryToken = new DirectAccessQueryToken(identifier)
	def apply(identifier: String, index: Int) : QueryToken = new ArrayAccessQueryToken(identifier, index)
	def all(identifier: String) : QueryToken = new WholeArrayQueryToken(identifier)
	
	implicit def javaToScalaIterator[T](iterator: java.util.Iterator[T]) : Iterator[T] = new Iterator[T] {
	  def hasNext: Boolean = iterator hasNext
	  def next: T = iterator next
	}
}
