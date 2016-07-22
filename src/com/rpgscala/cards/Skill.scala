package com.rpgscala.cards

import com.rpgscala.types.Trophy
import com.rpgscala.types.Criteria
import com.rpgscala.types._
import scala.collection.mutable.ListBuffer
import com.rpgscala.types.symbols._

case class Skill private (n: String, val criteria: Criteria*) extends Card(n) {
  val cost = ListBuffer[TrophySym]()
  var test: Boolean = false
  val description = ListBuffer[CardSkill]()
  var skillType: Sym = NONE
  
  criteria.foreach(_ match {
    case e: LearnCost => cost ++= e.trophies
    case Success(e) => test = e
    case e: CardSkill => description += e
    case SkillType(e) => skillType = e
    case _ =>
  })
  
  def this(name: String) {
    this(name, Skill.lookup(name): _*)
  }
  
  
}

object Skill {
  private final def lookup(name: String): List[Criteria] = name match {
    case "Mountaineering" => List(LearnCost(ANY), Success(true), CardSkill("Once per turn, you may spend any terrain die as a .."), SkillType(BODY))
    case _ => throw new Exception("Skill '" + name + "' not found")
  }
}