package com.rpgscala.obj.roller

import com.rpgscala.Main
import com.rpgscala.entities.Enemy
import com.rpgscala.entities.Player
import com.rpgscala.entities.Entity
import com.rpgscala.types.symbols.combat._
import com.rpgscala.types.symbols.Sym
import scala.util.Random

case class Token private (criteria: Any*) extends Roller {
    private val side1: CombatSym = criteria(0).asInstanceOf[CombatSym]
    private val side2: CombatSym = criteria(1).asInstanceOf[CombatSym]
	private val init: Int = criteria(2).asInstanceOf[Int]
    var currentFace: Sym = side1

	Value(side1)
	Value(side2)
    
	def this() {
	  this("HEADS","TAILS")
	}
	
	def this(itemCode: String) {
	  this(Token.itemLookup(itemCode): _*)
	}
	
	def flipOver() {
	  if(currentFace == side1) currentFace = side2
	  else currentFace = side1
	}
	
	def otherSide(): CombatSym = {
	  if(currentFace == side1) side2
	  else side1
	}
	
	def hasInitiative(): Boolean = {
	  currentFace == values(init)
	}
	
	def current(): Sym = currentFace
	
	override def roll(): Sym = {
	  currentFace = super.roll
	  currentFace
	}
	
}

object Token {
  
  private final def itemLookup(itemCode: String): List[Any] = itemCode match {
	case "A01" => List(AGILITY,DASH, 0)
	case "C07" => List(TWOAXE,SHIELD, 0)
	case _ => throw new Exception("Item code '"+ itemCode +"' not found")
  }
  
  final def listFor(c: Entity): List[Token] = c match {
    case e: Enemy => List(
      Token(DUPLICATE,DASH, 1),
      Token(SKULL,SURGE, 0),
      Token(DASH,SKULL, 0),
      Token(SHIELD,SKULL, -1),
      Token(AGILITY,SKULL, -1)) ++ 
      (if(Main.phaseTwo) List(Token(TWOSKULL,TWOSURGE, 1)) else List())
      
    case e: Player if e.name == "Lord Hawthorne" => List(
      Token(AGILITY,AXE, 1),
      Token(AXE,DASH, 0),
      Token(SHIELD,TWOAXE, -1))
      
    case e: Player if e.name == "Laurel of Bloodwood" => List(
      Token(TWOAXE,SURGE, -1),
      Token(SHIELD,AXE, 1),
      Token(SURGE,AGILITY, 0))
      
    case e => throw new Exception("Class '"+ c +"' not found" + e.name)
  }
  
}