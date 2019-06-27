import org.scalatest.{FlatSpec, Matchers}

class ChangeViewTest extends FlatSpec with Matchers {

  "ChangeView" should "map a denomination with value under 100 to a presentation to a pence denomination" in {
    val displayDenomination = ChangeView.map(Denomination(1))
    displayDenomination.originalDenomination should be(Denomination(1))
    displayDenomination.displayValue should be(1)
    displayDenomination.prefix should be(None)
    displayDenomination.suffix should be(Some("p"))
    displayDenomination.toString should be("1p")
  }

}
