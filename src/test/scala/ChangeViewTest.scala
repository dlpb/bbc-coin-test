import org.scalatest.{FlatSpec, Matchers}

class ChangeViewTest extends FlatSpec with Matchers {

  "ChangeView" should "map a denomination with value under 100 to a to a pence presentation denomination" in {
    val displayDenomination = ChangeView.mapToDisplayDenomination(Denomination(1), DisplayFormats.poundsFormat)
    displayDenomination.originalDenomination should be(Denomination(1))
    displayDenomination.displayValue should be(1)
    displayDenomination.prefix should be(None)
    displayDenomination.suffix should be(Some("p"))
    displayDenomination.toString should be("1p")
  }

  it should "map a denomination with a value over 100 to a pounds presentation denomination" in {
    val displayDenomination = ChangeView.mapToDisplayDenomination(Denomination(200), DisplayFormats.poundsFormat)
    displayDenomination.originalDenomination should be(Denomination(200))
    displayDenomination.displayValue should be(2)
    displayDenomination.prefix should be(Some("£"))
    displayDenomination.suffix should be(None)
    displayDenomination.toString should be("£2")
  }

  it should "map a denomination with a value of 100 to a pounds presentation denomination" in {
    val displayDenomination = ChangeView.mapToDisplayDenomination(Denomination(100), DisplayFormats.poundsFormat)
    displayDenomination.originalDenomination should be(Denomination(100))
    displayDenomination.displayValue should be(1)
    displayDenomination.prefix should be(Some("£"))
    displayDenomination.suffix should be(None)
    displayDenomination.toString should be("£1")
  }

  it should "map a denomination with a value of 99 to a pence presentation denomination" in {
    val displayDenomination = ChangeView.mapToDisplayDenomination(Denomination(99), DisplayFormats.poundsFormat)
    displayDenomination.originalDenomination should be(Denomination(99))
    displayDenomination.displayValue should be(99)
    displayDenomination.prefix should be(None)
    displayDenomination.suffix should be(Some("p"))
    displayDenomination.toString should be("99p")
  }

  it should "map a map of change of 1 coin to a string of coins" in {
    val change = Map(
      Denomination(1) -> 1
    )
    new ChangeView().formatChangeAsDisplayString(change) should be("1 x 1p")
  }

  it should "map a map of change of 2 coin to a string of coins" in {
    val change = Map(
      Denomination(1) -> 2
    )
    new ChangeView().formatChangeAsDisplayString(change) should be("2 x 1p")
  }

  it should "map a map of change of 2 types coin to a string of coins" in {
    val change = Map(
      Denomination(200) -> 1,
      Denomination(20) -> 2
    )
    new ChangeView().formatChangeAsDisplayString(change) should be("1 x £2, 2 x 20p")
  }

  it should "display euro signs for euro formatted change" in {
    val centDenomination = ChangeView.mapToDisplayDenomination(Denomination(99), DisplayFormats.euroFormat)
    centDenomination.originalDenomination should be(Denomination(99))
    centDenomination.displayValue should be(99)
    centDenomination.prefix should be(None)
    centDenomination.suffix should be(None)
    centDenomination.toString should be("99")

    val wholeDenominiation = ChangeView.mapToDisplayDenomination(Denomination(100), DisplayFormats.euroFormat)
    wholeDenominiation.originalDenomination should be(Denomination(100))
    wholeDenominiation.displayValue should be(1)
    wholeDenominiation.prefix should be(Some("€"))
    wholeDenominiation.suffix should be(None)
    wholeDenominiation.toString should be("€1")
  }
}
