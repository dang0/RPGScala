package com.rpgscala.types

import com.rpgscala.types.symbols.Sym

case class Cost (val value: Int, val costType: Sym) extends Criteria {
  def >(i: Int): Boolean = value > i
  def <(i: Int): Boolean = value < i
  def ==(i: Int): Boolean = value == i
}