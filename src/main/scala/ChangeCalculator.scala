import scala.collection.immutable.ListMap

class ChangeCalculator(availableDenominations: ListMap[Denomination, Int]) {

  def calculateChange(amount: Int): Map[Denomination, Int] = {

    def calculate(amount: Int, denominations: ListMap[Denomination, Int], change: Map[Denomination, Int]): Map[Denomination, Int] = {
      if(amount <= 0  || denominations.isEmpty) change
      else {
        val (highestAvailableDenomination, quantityOfDenomination) = getHighestAvailableDenomination(denominations)

        val expectedNumberOfDenomination = amount / highestAvailableDenomination.value
        val numberOfDenomination = if(quantityOfDenomination >= expectedNumberOfDenomination) expectedNumberOfDenomination else quantityOfDenomination

        val remainingAmount = amount - (numberOfDenomination * highestAvailableDenomination.value)
        val newChange =
          if(numberOfDenomination == 0) change
          else change + (highestAvailableDenomination -> numberOfDenomination)
        calculate(remainingAmount, denominations.tail, newChange)
      }
    }

    def getHighestAvailableDenomination(denominations: ListMap[Denomination, Int]): (Denomination, Int) = {
      denominations.maxBy(entry => entry._1.value)
    }

    calculate(amount, availableDenominations, Map())
  }
}

case class Denomination(value: Int)

object Denomination {
  val infiniteAmount = ListMap(
    Denomination(200)->Int.MaxValue,
    Denomination(100)->Int.MaxValue,
    Denomination(50)->Int.MaxValue,
    Denomination(20)->Int.MaxValue,
    Denomination(10)->Int.MaxValue,
    Denomination(5)->Int.MaxValue,
    Denomination(2)->Int.MaxValue,
    Denomination(1)->Int.MaxValue

  )
}
