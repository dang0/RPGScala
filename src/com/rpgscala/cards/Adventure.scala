package com.rpgscala.cards

import com.rpgscala.types.Criteria
import com.rpgscala.types.ActivationCost
import com.rpgscala.types.QuestComplete
import scala.collection.mutable.ListBuffer
import com.rpgscala.types.Trophy
import com.rpgscala.types.symbols._
import com.rpgscala.entities.Enemy
import scala.util.Try



case class Combat (n: String) extends Adventure(n, COMBAT) {
  lookup
}

case class Courier (n: String) extends Adventure(n, COURIER) {
  lookup
}

case class Exploration (n: String) extends Adventure(n, EXPLORATION) {
  lookup
}



abstract class Adventure(name: String, val trophyType: TrophySym = ANY) extends Card(name) {
  type Adventure = Card
  var card: CardFace = null
  
  final def lookup() {
    try { card = new Quest(name)
    } catch { case _ => lookupEnemy }
    
    def lookupEnemy() {
      try { card = new Enemy(name)
      } catch { case _ => lookupEvent }
    }
    
    def lookupEvent() {
      try { card = new Event(name)
      } catch { case e: Exception => throw new Exception(e.toString()) }
    }
  }
  
  implicit override def toString: String = card.toString
}
