import org.scalatest.{FlatSpec, Matchers}

class ChangeViewTest extends FlatSpec with Matchers {

  "ChangeView" should "map a denomination with value under 100 to a to a pence presentation denomination" in {
    val displayDenomination = ChangeView.map(Denomination(1))
    displayDenomination.originalDenomination should be(Denomination(1))
    displayDenomination.displayValue should be(1)
    displayDenomination.prefix should be(None)
    displayDenomination.suffix should be(Some("p"))
    displayDenomination.toString should be("1p")
  }

  it should "map a denomination with a value over 100 to a pounds presentation denomination" in {
    val displayDenomination = ChangeView.map(Denomination(200))
    displayDenomination.originalDenomination should be(Denomination(200))
    displayDenomination.displayValue should be(2)
    displayDenomination.prefix should be(Some("£"))
    displayDenomination.suffix should be(None)
    displayDenomination.toString should be("£2")
  }

  it should "map a denomination with a value of 100 to a pounds presentation denomination" in {
    val displayDenomination = ChangeView.map(Denomination(100))
    displayDenomination.originalDenomination should be(Denomination(100))
    displayDenomination.displayValue should be(1)
    displayDenomination.prefix should be(Some("£"))
    displayDenomination.suffix should be(None)
    displayDenomination.toString should be("£1")
  }

  it should "map a denomination with a value of 99 to a pence presentation denomination" in {
    val displayDenomination = ChangeView.map(Denomination(99))
    displayDenomination.originalDenomination should be(Denomination(99))
    displayDenomination.displayValue should be(99)
    displayDenomination.prefix should be(None)
    displayDenomination.suffix should be(Some("p"))
    displayDenomination.toString should be("99p")
  }

}
