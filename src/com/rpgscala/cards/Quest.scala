package com.rpgscala.cards

import com.rpgscala.types.ActivationCost
import com.rpgscala.types.Criteria
import com.rpgscala.types.QuestComplete
import scala.collection.mutable.ListBuffer
import com.rpgscala.types.symbols._
import com.rpgscala.types.Cost

class Quest private (val name: String, criteria: Criteria*) extends CardFace {
  val aCost = new ListBuffer[ActivationCost]()
  val qComplete = new ListBuffer[QuestComplete]()
  
  criteria.foreach(_ match {
    case e: ActivationCost => aCost += e
    case e: QuestComplete => qComplete += e
    case _ =>
  })
  
  def this(qName: String) {
    this(qName,Quest.questLookup(qName): _*)
  }
  
  override def getFace() = name +", " + criteria
}

object Quest {
  private final def questLookup(qName: String): List[Criteria] = qName match {
    case "Swamp Tales" => List( ActivationCost(Cost(1,ACTION),"Explore Blackwing Swamp"),
        QuestComplete(1,"Ruin your favorite boots.","No bonus.", WATER),
        QuestComplete(2,"Discover an ancient chest.","Gain 3 GOLD.", WATER, WILD),
        QuestComplete(3,"Visit the city below.","Become delayed to learn a skill in your hand for free. Discard this card.", WATER,WATER,PLAINS))
        
    case _ => throw new Exception("Quest '" + qName + "' not found")
  }
}