package challenge

case class Cart(items: List[CartItem]) {
  def add(item: Item): Cart = copy(items = (CartItem(item) :: items))
}
