package com.rpgscala.types

import com.rpgscala.types.symbols.Sym

case class QuestComplete (val level: Int, val desc: String, val reward: String, val requires: Sym*) extends Criteria 