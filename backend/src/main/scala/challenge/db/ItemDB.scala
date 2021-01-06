package challenge.db

import challenge.Item

object ItemDB {
  def find(code: String): Option[Item] = code match {
    case "TICKET" => Some(Item("TICKET", "Triggerise Ticket", 5.0))
    case "HOODIE" => Some(Item("HOODIE", "Triggerise Hoodie", 20.0))
    case "HAT" => Some(Item("HAT", "Triggerise Hat", 7.5))
    case _ => None
  }
}
