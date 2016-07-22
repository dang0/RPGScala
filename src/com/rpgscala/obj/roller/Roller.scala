package com.rpgscala.obj.roller

import scala.util.Random
import com.rpgscala.types.symbols.Sym
import scala.collection.mutable._

trait Roller {
  var i = 0
  def nextId = { i += 1; i-1 }
  protected val values: Map[Int,Sym] = new HashMap
  protected def Value(sym: Sym) = values += nextId -> sym
  
  //def listValues() = values
  
  def roll(): Sym = {
    values(Random.nextInt(i))
  }
  
}
