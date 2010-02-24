package com.gu.jpath

import com.google.gson.JsonElement

trait QueryToken {
	def navigate(element: JsonElement) : List[JsonElement]
}

object QueryToken {
	private class DirectAccessQueryToken(identifier: String) extends QueryToken {
		override def navigate(element: JsonElement) = {
			List(element.getAsJsonObject().get(identifier))
		}	
		override def toString = identifier
	}

	private class ArrayAccessQueryToken(identifier: String, index: Int) extends QueryToken {
		override def navigate(element: JsonElement) = {
			List(element.getAsJsonObject().get(identifier).getAsJsonArray().get(index));
		}
		override def toString = identifier + "[" + index + "]"
	}

	private class WholeArrayQueryToken(identifier: String)  extends QueryToken {
		override def navigate(element: JsonElement) = {
			List.fromIterator(new ScalaJavaIterator(element.getAsJsonObject.get(identifier).getAsJsonArray.iterator))
		}
		override def toString = identifier + "[*]"
	}

	def apply(identifier: String) : QueryToken = new DirectAccessQueryToken(identifier)
	def apply(identifier: String, index: Int) : QueryToken = new ArrayAccessQueryToken(identifier, index)
	def all(identifier: String) : QueryToken = new WholeArrayQueryToken(identifier)
}

class ScalaJavaIterator[T](private val iterator: java.util.Iterator[T]) extends Iterator[T] {
  def hasNext: Boolean = iterator hasNext
  def next: T = iterator next
}
