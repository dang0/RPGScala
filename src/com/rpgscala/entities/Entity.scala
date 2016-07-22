package com.rpgscala.entities

import com.rpgscala.obj.roller.Token

trait Entity {
  var name: String = ""
  var health: Int = 0
  var combatTokens: List[Token] = null

  def combatRoll() {
    combatTokens.foreach(_.roll)
    println(combatTokens)
  }
}