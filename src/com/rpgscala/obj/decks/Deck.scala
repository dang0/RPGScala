package com.rpgscala.obj.decks

import scala.collection.mutable.ListBuffer
import scala.util.Random
import com.rpgscala.cards.Card

trait Deck[T] {
  val cards = ListBuffer[T]()
  val discardPile = ListBuffer[T]()
  
  
  def draw(): T = {
    if(cards.length == 0) shuffle
    if(cards.length > 0) cards.remove(0)
    else throw new Exception("Deck empty")
  }
  
  def draw(x: Int): ListBuffer[T] = {
    val tmp = ListBuffer[T]()
    (1 to x) foreach { _ => tmp += draw }
    tmp
  }
  
  def shuffle() {
    while(discardPile.length > 0)
      cards += discardPile.remove(Random.nextInt(discardPile.length))
  }
}