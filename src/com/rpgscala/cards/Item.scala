package com.rpgscala.cards

import com.rpgscala.obj.roller.Token
import com.rpgscala.types.Criteria
import com.rpgscala.types.Equipable
import com.rpgscala.types.CardSkill
import scala.collection.mutable.ListBuffer
import com.rpgscala.types._
import com.rpgscala.types.symbols._
import com.rpgscala.types.symbols.combat._

case class Item private (n: String, criteria: Criteria*) extends Card(n) {

  var cost = 0
  var eqType: Class[_] = null
  var desc = ListBuffer[CardSkill]()
  var abilityList = ListBuffer[CardSkill]()
  var token: Token = null

  criteria.foreach(_ match {
    case Cost(e, _) => cost = e
    case e: Equipable => token = new Token(e.itemCode); eqType = e.getClass()
    case e: CardSkill if e.cost > 0 => abilityList += e
    case e: CardSkill => desc += e
    case _ =>
  })
  
  def this(iName: String) {
    this(iName, Item.itemLookup(iName): _*)
  }
  
}

object Item {
  final def itemLookup(iName: String): List[Criteria] = iName match {
    case "Truesight Bow" => List(Cost(12,GOLD), Weapon("C07"), CardSkill("Whenever you engage a foe, deal " + TWOAXE + "."))
    case _ => throw new Exception("Item '" + iName + "' not found")
  }
}