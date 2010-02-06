package com.gu.jpath

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
		List.fromIterator(new ScalaJavaIterator(element.getAsJsonObject.get(identifier).getAsJsonArray.iterator))
	}

	override def toString = identifier + "[*]"
}

class ScalaJavaIterator[T](private val iterator: java.util.Iterator[T]) extends Iterator[T] {
  def hasNext: Boolean = iterator hasNext

  def next: T = iterator next
}
