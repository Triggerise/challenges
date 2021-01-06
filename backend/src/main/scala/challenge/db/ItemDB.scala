package challenge.db

import challenge.Item

object ItemDB {
  val MUG =  Item("MUG", "Triggerise Mug", 4.0)
  val TSHIRT = Item("TSHIRT", "Triggerise Tshirt", 21.0)
  val USBKEY = Item("USBKEY", "Triggerise Usb Key", 10.0)

  val all = List(MUG, TSHIRT, USBKEY)

  def find(code: String): Item = code match {
    case MUG.code => MUG
    case TSHIRT.code => TSHIRT
    case USBKEY.code => USBKEY
  }
}
