package challenge

case class CartItem(item: Item, discount: Option[Double] = None, usedInDiscount: Boolean = false)
