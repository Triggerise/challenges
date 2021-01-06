package challenge

import challenge.db.ItemDB
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class CheckoutSpec extends AnyFlatSpec with Matchers {
  val pricingRules = List(
    new Discount("2For1", ItemDB.MUG),
    new Discount("Bulk", ItemDB.TSHIRT)
  )

  "The price for a empty shopping cart" must " be 0" in {
    Checkout(pricingRules).total must be(0)
  }

  "If the shopping cart does not contain any products with promotions the price" must "be the sum of the unit prices" in {
    Checkout(pricingRules)
      .scan("USBKEY")
      .scan("USBKEY")
      .scan("USBKEY")
      .total must be(3 * 10)
  }

  //Example 1 from the readme
  "If no promotions are valid the price " must "be the sum of the unit prices" in {
    Checkout(pricingRules)
      .scan("MUG")
      .scan("TSHIRT")
      .scan("USBKEY")
      .total must be(35.0)
  }

  //Example 2 from the readme
  "The 2-for-1 promotion for the MUG" must "be applied" in {
    Checkout(pricingRules)
      .scan("MUG")
      .scan("TSHIRT")
      .scan("MUG")
      .total must be(25.0)
  }

  //Example 3 from the readme
  "The buy 3 or more TSHIRT promotion" must "be applied" in {
    Checkout(pricingRules)
      .scan("TSHIRT")
      .scan("TSHIRT")
      .scan("TSHIRT")
      .scan("MUG")
      .scan("TSHIRT")
      .total must be(62.8)
  }

  //Example 4 from the readme
  "Even with several items in random order the price" must "be correct" in {
    Checkout(pricingRules)
      .scan("MUG")
      .scan("TSHIRT")
      .scan("MUG")
      .scan("MUG")
      .scan("USBKEY")
      .scan("TSHIRT")
      .scan("TSHIRT")
      .total must be(62.1)
  }
}
