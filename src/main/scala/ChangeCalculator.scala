class ChangeCalculator {
  def calculateChange(amount: Int): Map[Int, Int] = {

    val denominations = List(200,100,50,20,10,5,2,1)

    def calculate(amount: Int, denominations: List[Int], change: Map[Int, Int]): Map[Int, Int] = {
      if(amount <= 0  || denominations.isEmpty) change
      else {
        val highestAvailableDenomination = denominations.head
        val numberOfDenomination = amount / highestAvailableDenomination
        val remainingAmount = amount - (numberOfDenomination * highestAvailableDenomination)

        val newChange =
          if(numberOfDenomination == 0) change
          else change + (highestAvailableDenomination -> numberOfDenomination)
        calculate(remainingAmount, denominations.tail, newChange)
      }
    }

    calculate(amount, denominations, Map())
  }
}
