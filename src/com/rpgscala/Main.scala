package com.rpgscala

import com.rpgscala.obj.roller._
import scala.collection.mutable.ListBuffer
import com.rpgscala.cards.Combat
import com.rpgscala.cards.Courier
import com.rpgscala.cards.Exploration
import com.rpgscala.cards.Skill
import com.rpgscala.cards.Item
import com.rpgscala.entities.Enemy
import com.rpgscala.entities.Player
import com.rpgscala.obj.decks._
import com.rpgscala.cards.Card
import com.rpgscala.cards.Adventure

object Main extends App {
//  while(true){
////	println(SixSided.roll +" "+ SixSided.roll)
//    val tk = new Token("A01")
//    println(tk.roll + " " + tk.roll)
//	Thread.sleep(200)
//  }
  var phaseTwo = false
  
  CombatDeck.shuffle
  CourierDeck.shuffle
  ExplorationDeck.shuffle
  ItemDeck.shuffle
  SkillDeck.shuffle
  
  val turnOrder = TurnOrder(ListBuffer(new Player("Laurel of Bloodwood")))
  //while(true) {
    turnOrder.players.foreach(player => player.status)
      
    
  //}  
  
}

case class TurnOrder(players: ListBuffer[Player]) extends Enumeration {
  players.foreach {p => Value(p.name)}
}
