package com.rpgscala.obj.roller

import scala.collection.mutable.ArrayBuffer
import com.rpgscala.types.symbols.Sym
import com.rpgscala.types.symbols.CUSTOM

class NSided private (n: Int, s: Sym*) extends Roller {

	if(n > 1) {
	  for(i <- 1 to n) {
	    Value(CUSTOM(i.toString()))
	  }
	}
	else s.foreach{x => Value(x)}
	
	def this(s: Sym*) {
	  this(0, s: _*)
	}
	
	def this(n: Int) {
	  this(n, CUSTOM("1"))
	}
	
	def this() {
	  this(1)
	}
}