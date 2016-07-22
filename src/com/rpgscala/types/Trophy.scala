package com.rpgscala.types

import com.rpgscala.cards.Adventure
import com.rpgscala.cards.Courier
import com.rpgscala.cards.Exploration
import com.rpgscala.cards.Combat

object Trophy {
  final def any = classOf[Adventure]
  final def combat = classOf[Combat]
  final def courier = classOf[Courier]
  final def exploration = classOf[Exploration]
}