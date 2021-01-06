package challenge

object Challenge extends App {
  val pricingRules = PricingRules(
    List(
      TwoForOne("TICKET"),
      ThreeBulk("HOODIE", 0.05)
    )
  )

  val co = Checkout(pricingRules)
  co.scan("TICKET")
  co.scan("TICKET")
  co.scan("TICKET")
  co.scan("TICKET")
  co.scan("HOODIE")
  co.scan("HOODIE")
  co.scan("HOODIE")

  val price = co.total
  println(price)
}
