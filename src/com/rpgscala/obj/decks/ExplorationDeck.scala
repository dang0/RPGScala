package com.rpgscala.obj.decks

import com.rpgscala.cards.Exploration
import com.rpgscala.cards.Adventure

object ExplorationDeck extends AdventureDeck {
  discardPile += (
      Exploration("Swamp Tales"))
}