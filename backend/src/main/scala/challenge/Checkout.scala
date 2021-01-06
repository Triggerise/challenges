package challenge

import challenge.db.ItemDB

case class Checkout(pricingRules: List[Discount], basket: List[Item] = List.empty) {
  def scan(code: String): Checkout = this.copy(basket = basket :+ ItemDB.find(code))

  def total: Double = {
    val mugs = basket.foldLeft((0, 0.0)) {
      case ((numberOfItems, acc), item) if item == ItemDB.MUG =>
        (numberOfItems + 1, acc + ItemDB.MUG.price)
      case ((numberOfItems, acc), _) =>
        (numberOfItems, acc)
    }
    val mugsFinalTotal = mugs._2 - pricingRules.find(_.forItem == ItemDB.MUG).map(_.calculate(mugs._1)).getOrElse(0.0)

    val tshirts = basket.foldLeft((0, 0.0)) {
      case ((numberOfItems, acc), item) if item == ItemDB.TSHIRT =>
        (numberOfItems + 1, acc + ItemDB.TSHIRT.price)
      case ((numberOfItems, acc), _) =>
        (numberOfItems, acc)
    }
    val tshirtsFinalTotal = tshirts._2 - pricingRules.find(_.forItem == ItemDB.TSHIRT).map(_.calculate(tshirts._1)).getOrElse(0.0)

    val usbs = basket.foldLeft((0, 0.0)) {
      case ((numberOfItems, acc), item) if item == ItemDB.USBKEY =>
        (numberOfItems + 1, acc + ItemDB.USBKEY.price)
      case ((numberOfItems, acc), _) =>
        (numberOfItems, acc)
    }
    val usbsFinalTotal = usbs._2 - pricingRules.find(_.forItem == ItemDB.USBKEY).map(_.calculate(usbs._1)).getOrElse(0.0)

    mugsFinalTotal + tshirtsFinalTotal + usbsFinalTotal
  }
}
