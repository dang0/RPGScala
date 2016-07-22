package com.rpgscala.cards

trait CardFace {
  def getFace(): String = "unknown info"
  implicit override def toString(): String = getClass().getSimpleName()+"("+ getFace() +")"
}