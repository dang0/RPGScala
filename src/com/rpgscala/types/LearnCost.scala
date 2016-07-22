package com.rpgscala.types

import com.rpgscala.types.symbols.TrophySym

case class LearnCost (val trophies: TrophySym*) extends Criteria {

}