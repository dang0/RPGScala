package com.rpgscala.types.symbols.combat

import com.rpgscala.types.symbols.Sym

trait CombatSym extends Sym { def name: String }
case object DASH extends CombatSym { def name = "-" }

trait AGILITY extends CombatSym { def name: String }
trait AXE extends CombatSym { def name: String }
trait BURST extends CombatSym { def name: String }
trait DUPLICATE extends CombatSym { def name: String }
trait SHIELD extends CombatSym { def name: String }
trait SKULL extends CombatSym { def name: String }
trait SURGE extends CombatSym { def name: String }

case object AGILITY extends AGILITY { def name = "AGILITY" }
case object AXE extends AXE { def name = "AXE" }
case object BURST extends BURST { def name = "BURST" }
case object DUPLICATE extends DUPLICATE { def name = "DUPLICATE" }
case object SHIELD extends SHIELD { def name = "SHIELD" }
case object SKULL extends SKULL { def name = "SKULL" }
case object SURGE extends SURGE { def name = "SURGE" }

case object TWOAGILITY extends AGILITY { def name = "2AGILITY" }
case object TWOAXE extends AXE { def name = "2AXE" }
case object TWOBURST extends BURST { def name = "2BURST" }
case object TWODUPLICATE extends DUPLICATE { def name = "2DUPLICATE" }
case object TWOSHIELD extends SHIELD { def name = "2SHIELD" }
case object TWOSKULL extends SKULL { def name = "2SKULL" }
case object TWOSURGE extends SURGE { def name = "2SURGE" }