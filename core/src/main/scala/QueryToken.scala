package com.gu.jsonpath

import com.google.gson.JsonElement

trait QueryToken {
	def navigate(element: JsonElement) : List[JsonElement]
}

class DirectAccessQueryToken(identifier: String) extends QueryToken {
	override def navigate(element: JsonElement) = {
		List(element.getAsJsonObject().get(identifier))
	}
	
	override def toString = identifier
}

class ArrayAccessQueryToken(identifier: String, index: Int) extends QueryToken {
	override def navigate(element: JsonElement) = {
		List(element.getAsJsonObject().get(identifier).getAsJsonArray().get(index));
	}

	override def toString = identifier + "[" + index + "]"
}

class WholeArrayQueryToken(identifier: String)  extends QueryToken {
	override def navigate(element: JsonElement) = {
		List.fromIterator(new ScalaJavaIterable(element.getAsJsonObject().get(identifier).getAsJsonArray()).elements)
	}

	override def toString = identifier + "[*]"
}

class ScalaJavaIterable[T](private val iterable: java.lang.Iterable[T]) extends Iterable[T] {
	def elements(): Iterator[T] = new ScalaJavaIterator(iterable.iterator())
}


class ScalaJavaIterator[T](private val iterator: java.util.Iterator[T]) extends Iterator[T] {
  def hasNext: Boolean = iterator hasNext

  def next: T = iterator next
}
