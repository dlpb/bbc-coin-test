import org.scalatest.{FlatSpec, Matchers}

class ChangeCalculatorTest extends FlatSpec with Matchers {
  "ChangeCalculator" should "calculate change for 1p" in {
    val cc = new ChangeCalculator
    val change = cc.calculateChange(1)
    change should be(1)
  }git
}
