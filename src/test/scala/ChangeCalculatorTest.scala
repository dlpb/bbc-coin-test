import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.ListMap

class ChangeCalculatorTest extends FlatSpec with Matchers {
  "ChangeCalculator" should "calculate change for 1p" in {
    val cc = new ChangeCalculator(Denomination.infiniteAmount)
    val change = cc.calculateChange(1)
    change should be(Map(Denomination(1) -> 1))
  }

  it should "calculate change for 3p using 2p and 1p" in {
    val cc = new ChangeCalculator(Denomination.infiniteAmount)
    val change = cc.calculateChange(3)
    change should be(Map(Denomination(2) -> 1, Denomination(1) -> 1))
  }

  it should "calculate change for £1.23" in {
    val cc = new ChangeCalculator(Denomination.infiniteAmount)
    val change = cc.calculateChange(123)
    change should be(
      Map(
        Denomination(100) -> 1,
        Denomination(20) -> 1,
        Denomination(2) -> 1,
        Denomination(1) -> 1
      )
    )
  }
  it should "calculate change for £9.99" in {
    val cc = new ChangeCalculator(Denomination.infiniteAmount)
    val change = cc.calculateChange(999)
    change should be(
      Map(
        Denomination(200) -> 4,
        Denomination(100) -> 1,
        Denomination(50) -> 1,
        Denomination(20) -> 2,
        Denomination(5) -> 1,
        Denomination(2) -> 2
      )
    )
  }

  it should "calculate change for £1.23 when there are no £1 coins available" in {
    val availableDenominations = ListMap(
      Denomination(200)->Int.MaxValue,
      Denomination(100)->0,
      Denomination(50)->Int.MaxValue,
      Denomination(20)->Int.MaxValue,
      Denomination(10)->Int.MaxValue,
      Denomination(5)->Int.MaxValue,
      Denomination(2)->Int.MaxValue,
      Denomination(1)->Int.MaxValue
    )

    val cc = new ChangeCalculator(availableDenominations)
    val change = cc.calculateChange(123)
    change should be(
      Map(
        Denomination(50) -> 2,
        Denomination(20) -> 1,
        Denomination(2) -> 1,
        Denomination(1) -> 1
      )
    )
  }

  //interesting edge case - what if you can't make change?
}
