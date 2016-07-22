package com.rpgscala.entities

import com.rpgscala.obj.roller.Token
import com.rpgscala.cards.Item
import com.rpgscala.types.symbols.TOWN
import com.rpgscala.types.CardSkill
import com.rpgscala.types.symbols.combat.SURGE
import scala.collection.mutable.ListBuffer
import com.rpgscala.cards.Skill
import com.rpgscala.types.symbols.Sym
import com.rpgscala.types.Cost
import com.rpgscala.cards.Adventure
import com.rpgscala.types.symbols.COURIER
import com.rpgscala.types.symbols.COMBAT
import com.rpgscala.types.symbols.EXPLORATION
import com.rpgscala.types.symbols.ANY
import com.rpgscala.obj.decks.CombatDeck
import com.rpgscala.obj.decks.ExplorationDeck
import com.rpgscala.obj.decks.CourierDeck
import com.rpgscala.obj.decks.ItemDeck
import com.rpgscala.types.Clothing
import com.rpgscala.types.Equipment
import com.rpgscala.types.Weapon
import com.rpgscala.types.Goods

class Player (pName: String, criteria: Any*) extends Entity {
  name = pName
  
  var gold: Int = criteria(0).asInstanceOf[Int]
  var maxHandSize: Int = criteria(1).asInstanceOf[Int]
  var startingHandSize: Int = criteria(1).asInstanceOf[Int]
  var startingLocation: Sym = criteria(2).asInstanceOf[Sym]
  var setup: CardSkill = criteria(3).asInstanceOf[CardSkill]
  
  var speed: Int = criteria(4).asInstanceOf[Int]
  health = criteria(5).asInstanceOf[Int]
  var passiveAction: CardSkill = criteria(6).asInstanceOf[CardSkill]
  var combatAction: CardSkill = criteria(7).asInstanceOf[CardSkill]
  var body: Int = criteria(8).asInstanceOf[Int]
  var mind: Int = criteria(9).asInstanceOf[Int]
  var spirit: Int = criteria(10).asInstanceOf[Int]
  
  
  
  var loreTokens: Int = 0
  val trophies = ListBuffer[Adventure]()
  def combatTrophies: Int = trophies.count(_.trophyType == COMBAT)
  def courierTrophies: Int = trophies.count(_.trophyType == COURIER)
  def exploreTrophies: Int = trophies.count(_.trophyType == EXPLORATION)
  
  
  val learnedSkills: ListBuffer[Skill] = new ListBuffer[Skill]()
  val skillInventory: ListBuffer[Skill] = new ListBuffer[Skill]()
  
  var weapon: Item = null
  var clothing: Item = null
  var equipment: Item = null
  var goods: Item = null
  
  var currentLocation: (Int,Int) = (0,0)
  
  def this(pName: String) {
    this(pName, Player.validate(Player.playerChoice(pName)): _*)
    combatTokens = Token.listFor(this)
  }
  def inventoryItems(): List[Item] = {
    List(weapon,clothing,equipment,goods)
  }
  
  def train() {
    
  }
  
  def learn(skillIndex: Int): Boolean = {
    val reqComb = skillInventory(skillIndex).cost.count(_ == COMBAT) 
    val reqCour = skillInventory(skillIndex).cost.count(_ == COURIER) 
    val reqExpl = skillInventory(skillIndex).cost.count(_ == EXPLORATION)
    val reqAny = skillInventory(skillIndex).cost.count(_ == ANY) 
    if(reqComb > combatTrophies 
        || reqCour > courierTrophies 
        || reqExpl > exploreTrophies 
        || reqAny > (trophies.length - reqComb - reqCour - reqExpl)) { false }
    else {//choose trophies
      var invalid = true
      while(invalid) {
        print("which cards? ")
        val chosenCards = ListBuffer[Int]()
        (1 to (skillInventory(skillIndex).cost.length)).foreach(_ => chosenCards += readInt)
        chosenCards.foreach(i => if(i < 0 || i > trophies.length-1) throw new Exception("Out of trophy inventory bounds"))
        var chosenComb, chosenCour, chosenExpl = 0
        chosenCards.foreach(i => trophies(i).trophyType match {
          case COMBAT => chosenComb += 1
          case COURIER => chosenCour += 1
          case EXPLORATION => chosenExpl += 1
          case _ => throw new Exception("Chose invalid trophy")
        })
        chosenComb -= reqComb
        chosenCour -= reqCour
        chosenExpl -= reqExpl
        if(chosenComb >= 0
          && chosenCour >= 0
          && chosenExpl >= 0
          && reqAny <= (chosenComb + chosenCour + chosenExpl)) {
            chosenCards.foreach(i => trophies.remove(i) match {
              case e: Adventure if e.trophyType == COMBAT => CombatDeck.discardPile += e
              case e: Adventure if e.trophyType == COURIER => CourierDeck.discardPile += e
              case e: Adventure if e.trophyType == EXPLORATION => ExplorationDeck.discardPile += e
              case _ => throw new Exception("Removed invalid trophy")
            })
            invalid = false
        }
      }
      learnedSkills += skillInventory.remove(skillIndex)
      true
    }
  }
  
  def purchase(itemList: ListBuffer[Item], i: Int): Boolean = {
    if(itemList(i).cost > gold) false
    else {
      gold -= itemList(i).cost
      itemList(i).eqType match {
        case e: Weapon => weapon = itemList.remove(i)
        case e: Clothing => clothing = itemList.remove(i)
        case e: Equipment => equipment = itemList.remove(i)
        case e: Goods => goods = itemList.remove(i)
        case _ => throw new Exception("Item eqType not found")
      }
      true
    }
  }
  
  private def sellEquip(item: Item) {
    var bonusGold = 0
    item.desc.foreach(_ match {
      case e if e.name == "Trade Goods" && currentLocation == (0,0) => bonusGold += 2  //TODO: properly implement
    })
    gold += item.cost + bonusGold
    ItemDeck.discardPile += item
  }
  
  def sellWeapon() {
    val tmp = weapon
    weapon = null
    sellEquip(tmp)
  }
  
  def sellClothing() {
    val tmp = clothing
    clothing = null
    sellEquip(tmp)
  }
  
  def sellEquiment() {
    val tmp = equipment
    equipment = null
    sellEquip(tmp)
  }
  
  def sellGoods() {
    val tmp = goods
    goods = null
    sellEquip(tmp)
  }

  def status() {
    println("Name: " + name 
    	+ "\nGold: " + gold
    	+ "\nHandsize: " + maxHandSize
    	+ "\nstart loc: " + startingLocation
    	+ "\nsetup: " + setup

    	+ "\nspeed: " + speed
        + "\nhealth: " + health
    	+ "\npaction: " + passiveAction
    	+ "\ncaction: " + combatAction
    	+ "\nbody: " + body
    	+ "\nmind: " + mind
    	+ "\nspirit: " + spirit

    	+ "\nlore: " + loreTokens
    	+ "\ncombatTrophies: " + combatTrophies
    	+ "\ncourierTrophies: " + courierTrophies
    	+ "\nexploreTrophies: " + exploreTrophies

    	+ "\nlearnedSkills: " + learnedSkills
    	+ "\nskillInventory: " + skillInventory
    	+ "\ninventory: " + inventoryItems
    )
  }
  
}

object Player {
  private final def playerChoice(pName: String): List[Any] = pName match {
    case "Lord Hawthorne" => List()
    
    case "Laurel of Bloodwood" => List( // <index> : <description>
        2, // 0 : Starting GOLD
        4, // 1 : Max hand size
        TOWN, // 2 : Starting location
        CardSkill("Roll 2 terrain dice. You may spend them to move."), // 3 : Setup
        3, // 4 : Speed
        8, // 5 : Health
        CardSkill("Once per turn, you may reroll all of your terrain dice.", "Pathfinding"), // 6 : Passive skill
        CardSkill("Recast all of your tokens.", "PLACEHOLDERNAME", Cost(2,SURGE)), // 7 : Combat skill
        2, // 8 : Body
        2, // 9 : Mind
        1) // 10 : Spirit
        
    case _ => throw new Exception("Hero '" + pName + "' not found")
  }
  
  private final def validate(list: List[Any]): List[Any] = {
    if(list.length != 11 
      || !list(0).isInstanceOf[Int]
      || !list(1).isInstanceOf[Int]
      || !list(2).isInstanceOf[Sym]
      || !list(3).isInstanceOf[CardSkill]
      || !list(4).isInstanceOf[Int]
      || !list(5).isInstanceOf[Int]
      || !list(6).isInstanceOf[CardSkill]
      || !list(7).isInstanceOf[CardSkill]
      || !list(8).isInstanceOf[Int]
      || !list(9).isInstanceOf[Int]
      || !list(10).isInstanceOf[Int]) throw new Exception("Invalid Player criteria format")
    list
  }
}
