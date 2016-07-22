package com.rpgscala.obj.decks

import com.rpgscala.cards.Item

object ItemDeck extends Deck[Item] {
  discardPile += (
      new Item("Truesight Bow"))
}