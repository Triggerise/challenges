package challenge

import challenge.db.ItemDB

case class Checkout(pricingRules: PricingRules) {
  var cart = Cart(List.empty[CartItem])

  def scan(code: String): Unit = {
    ItemDB.find(code).foreach { item =>
      cart = cart.add(item)
    }
  }

  def current: Cart = cart
  def total: Double = pricingRules.process(cart)
}
