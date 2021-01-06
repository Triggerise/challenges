package challenge

class Discount(val name: String, val forItem: Item) {
  def calculate(numberOfItems: Int): Double = name match {
    case "2For1" =>
      (numberOfItems / 2).toInt * forItem.price
    case "Bulk" =>
      if (numberOfItems >= 3) numberOfItems * (forItem.price * 0.3)
      else 0
  }
}
