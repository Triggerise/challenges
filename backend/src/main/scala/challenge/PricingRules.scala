package challenge

case class PricingRules(discounts: List[Discount]) {
  def process(cart: Cart): Double = {
    applyDiscounts(cart).items.map { cartItem =>
      cartItem.item.price * (1.0 - cartItem.discount.getOrElse(0.0))
    }.sum
  }

  private def applyDiscounts(cart: Cart) =
    discounts.foldLeft(cart) { (currentCart, discount) => discount.apply(currentCart) }
}
