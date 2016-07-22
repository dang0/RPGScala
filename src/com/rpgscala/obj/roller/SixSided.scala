package com.rpgscala.obj.roller

import scala.util.Random
import com.rpgscala.types.symbols._

class SixSided(a: Sym, b: Sym, c: Sym, d: Sym, e: Sym, f: Sym) extends Roller {
  val SIDE1 = Value(a)
  val SIDE2 = Value(b)
  val SIDE3 = Value(c)
  val SIDE4 = Value(d)
  val SIDE5 = Value(e)
  val SIDE6 = Value(f)
  
  def this() {
    this(WILD,PLAINS,WATER,HILLSFOREST,MOUNTAINSFOREST,WATERMOUNTAINS)
  }
}