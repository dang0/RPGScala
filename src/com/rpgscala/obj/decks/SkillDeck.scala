package com.rpgscala.obj.decks

import com.rpgscala.cards.Skill

object SkillDeck extends Deck[Skill] {
  discardPile += (
      new Skill("Mountaineering"))
}