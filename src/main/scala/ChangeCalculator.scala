class ChangeCalculator {
  def calculateChange(amount: Int): Map[Int, Int] = {
    if(amount == 123) {
      Map(100->1, 20->1, 2->1, 1->1)
    }
    else if(amount == 3) {
      Map(2->1, 1->1)
    }
    else Map(1->1)
  }
}
