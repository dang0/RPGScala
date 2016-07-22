package com.rpgscala.types.symbols

trait Sym {
  this: { def name: String } => 
  implicit override def toString(): String = "["+name+"]"
}

case class CUSTOM(s: String) extends Sym { def name = s }

case object NONE extends Sym { def name = "" }

case object GOLD extends Sym { def name = "GOLD" }
case object SPEED extends Sym { def name = "SPEED" }
case object CARDS extends Sym { def name = "CARDS" }
case object HEALTH extends Sym { def name = "HEALTH" }

case object BODY extends Sym { def name = "BODY" }
case object MIND extends Sym { def name = "MIND" }
case object SPIRIT extends Sym { def name = "SPIRIT" }

case object ACTION extends Sym { def name = "ACTION" }

trait TrophySym extends Sym { def name: String }
case object ANY extends TrophySym { def name = "ANY" }
case object COMBAT extends TrophySym { def name = "COMBAT" }
case object COURIER extends TrophySym { def name = "COURIER" }
case object EXPLORATION extends TrophySym { def name = "EXPLORATION" }

case object TOWN extends Sym { def name = "TOWN" }
case object TEMPLE extends Sym { def name = "TEMPLE" }
case object CITY extends Sym { def name = "CITY" }

trait TerrainSym extends Sym { def name: String }
trait PLAINS extends TerrainSym { def name: String }
trait WATER extends TerrainSym { def name: String }
trait MOUNTAINS extends TerrainSym { def name: String }
trait HILLS extends TerrainSym { def name: String }
trait FOREST extends TerrainSym { def name: String }

case object WILD extends TerrainSym { def name = "WILD" }
case object PLAINS extends PLAINS { def name = "PLAINS" }
case object WATER extends WATER { def name = "WATER" }
case object MOUNTAINS extends MOUNTAINS { def name = "MOUNTAINS" }
case object HILLS extends HILLS { def name = "HILLS" }
case object FOREST extends FOREST { def name = "FOREST" }

case object PLAINSWATER extends PLAINS with WATER { def name = "PLAINS/WATER" }
case object PLAINSMOUNTAINS extends PLAINS with MOUNTAINS { def name = "PLAINS/MOUNTAINS" }
case object PLAINSHILLS extends PLAINS with HILLS { def name = "PLAINS/HILLS" }
case object PLAINSFOREST extends PLAINS with FOREST { def name = "PLAINS/FOREST" }
case object WATERMOUNTAINS extends WATER with MOUNTAINS { def name = "WATER/MOUNTAINS" }
case object WATERHILLS extends WATER with HILLS { def name = "WATER/HILLS" }
case object WATERFOREST extends WATER with FOREST { def name = "WATER/FOREST" }
case object MOUNTAINSHILLS extends MOUNTAINS with HILLS { def name = "MOUNTAINS/HILLS" }
case object MOUNTAINSFOREST extends MOUNTAINS with FOREST { def name = "MOUNTAINS/FOREST" }
case object HILLSFOREST extends HILLS with FOREST { def name = "HILLS/FOREST" }
