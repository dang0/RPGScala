package com.rpgscala.types

import com.rpgscala.cards.Item

case class Weapon(ic: String) extends Equipable(ic) {
}
case class Clothing(ic: String) extends Equipable(ic) {
}
case class Equipment(ic: String) extends Equipable(ic) {
}
case class Goods(ic: String) extends Equipable(ic) {
}

abstract class Equipable(val itemCode: String) extends Criteria {
}