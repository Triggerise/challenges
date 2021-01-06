package challenge

abstract class Discount(itemCode: String) {
  def apply(cart: Cart): Cart = {
    val (applicable, notApplicable) = cart.items.partition(ci => ci.item.code == itemCode && ci.usedInDiscount == false)
    Cart(List.concat(apply(applicable), notApplicable))
  }

  protected def apply(items: List[CartItem]): List[CartItem]
}

class XForY(x: Int, y: Int, itemCode: String) extends Discount(itemCode) {
  def apply(items: List[CartItem]): List[CartItem] =
    items.grouped(x).toList.map { group =>
      if(group.size == x) {
        val (left, right) = group.splitAt(x - y)
        List.concat(
          left.map(_.copy(discount = Some(1.0), usedInDiscount = true)),
          right.map(_.copy(usedInDiscount = true))
        )
      }
      else
        group
    }.flatten
}

class XBulk(x: Int, itemCode: String, discount: Double) extends Discount(itemCode) {
  def apply(items: List[CartItem]): List[CartItem] =
    if(items.size >= x) items.map(_.copy(discount = Some(discount), usedInDiscount = true))
    else items
}

case class TwoForOne(itemCode: String) extends XForY(2, 1, itemCode)
case class ThreeBulk(itemCode: String, discount: Double) extends XBulk(3, itemCode, discount)
