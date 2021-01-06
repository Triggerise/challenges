import challenge.db.ItemDB
import challenge.{Checkout, Discount}

object Main extends App {
  val pricingRules = List(
    new Discount("2For1", ItemDB.MUG),
    new Discount("Bulk", ItemDB.TSHIRT)
  )

  val co = Checkout(pricingRules)
    .scan("MUG")
    .scan("MUG")

    .scan("TSHIRT")
  val price = co.total

  println(s"Total basket price is: $price")
}
