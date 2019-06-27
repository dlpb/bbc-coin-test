import org.scalatest.{FlatSpec, Matchers}

class ChangeCalculatorTest extends FlatSpec with Matchers {
  "ChangeCalculator" should "calculate change for 1p" in {
    val cc = new ChangeCalculator
    val change = cc.calculateChange(1)
    change should be(Map(1->1))
  }
  it should "calcualte change for 3p using 2p and 1p" in {
    val cc = new ChangeCalculator
    val change = cc.calculateChange(3)
    change should be(Map(2->1,1->1))
  }
  it should "calculate change for £1.23" in {
    val cc = new ChangeCalculator
    val change = cc.calculateChange(123)
    change should be(Map(100->1, 20->1, 2->1, 1->1))
  }
}
