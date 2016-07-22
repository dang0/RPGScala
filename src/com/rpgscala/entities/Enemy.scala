package com.rpgscala.entities

import com.rpgscala.types.Criteria
import scala.collection.mutable.ListBuffer
import com.rpgscala.types.Health
import com.rpgscala.types.CardSkill
import com.rpgscala.types.Reward
import com.rpgscala.obj.roller.Token
import com.rpgscala.types.symbols.combat._
import com.rpgscala.types.Cost
import com.rpgscala.cards.CardFace

class Enemy private (n: String, criteria: Criteria*) extends Entity with CardFace {
  name = n
  val passiveSkills = ListBuffer[CardSkill]()
  val combatSkills = ListBuffer[CardSkill]()
  var reward: String = ""
  
  criteria.foreach(_ match {
    case e: Health => health = e.value
    case e: CardSkill if e.cost == 0 => passiveSkills += e
    case e: CardSkill if e.cost > 0 => combatSkills += e
    case e: Reward => reward = e.desc
    case _ =>
  })
  
  def this(eName: String) {
    this(eName,Enemy.enemyLookup(eName): _*)
    combatTokens = Token.listFor(this)
  }

  override def getFace(): String = name +", " + criteria
}

object Enemy {
  private final def enemyLookup(eName: String): List[Criteria] = eName match {
    case "Dragon Hybrid" => List(Health(6),
        CardSkill("This enemy has +1 HEALTH for each lore its foe has."), 
        CardSkill("Deal 2 SKULL.","Slice", Cost(1,SURGE)),
        CardSkill("Remove all of your foe's BURST tokens.","Barrier", Cost(2,SURGE)),
        Reward("Gain 2 GOLD or 1 LORE"))
    case _ => throw new Exception("Enemy '" + eName + "' not found")
  }
}