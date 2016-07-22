package com.rpgscala.types

import com.rpgscala.types.symbols.Sym
import com.rpgscala.types.symbols.NONE

case class CardSkill (val desc: String, val name: String = "", val cost: Cost = Cost(0,NONE) ) extends Criteria 
