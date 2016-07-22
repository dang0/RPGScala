package com.rpgscala.cards

import com.rpgscala.types.Criteria

class Event private (val name: String, criteria: Criteria*) extends CardFace {
  
  criteria.foreach(_ match {
    case _ =>
  })
  
  def this(vName: String) = this(vName, Event.eventLookup(vName): _*)
  
  override def getFace() = name +", " + criteria
}

object Event {
  private final def eventLookup(vName: String): List[Criteria] = vName match {
    case _ => throw new Exception("Event '" + vName + "' not found")
  }
}