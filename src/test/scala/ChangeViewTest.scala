import org.scalatest.{FlatSpec, Matchers}

class ChangeViewTest extends FlatSpec with Matchers {

  "ChangeView" should "map a denomination with value under 100 to a to a pence presentation denomination" in {
    val displayDenomination = ChangeView.mapToDisplayDenomination(Denomination(1), DisplayFormat.ukFormat)
    displayDenomination.originalDenomination should be(Denomination(1))
    displayDenomination.toString should be("1p")
  }

  it should "map a denomination with a value over 100 to a pounds presentation denomination" in {
    val displayDenomination = ChangeView.mapToDisplayDenomination(Denomination(200), DisplayFormat.ukFormat)
    displayDenomination.originalDenomination should be(Denomination(200))
    displayDenomination.toString should be("£2.00")
  }

  it should "map a denomination with a value of 100 to a pounds presentation denomination" in {
    val displayDenomination = ChangeView.mapToDisplayDenomination(Denomination(100), DisplayFormat.ukFormat)
    displayDenomination.originalDenomination should be(Denomination(100))
    displayDenomination.toString should be("£1.00")
  }

  it should "map a denomination with a value of 99 to a pence presentation denomination" in {
    val displayDenomination = ChangeView.mapToDisplayDenomination(Denomination(99), DisplayFormat.ukFormat)
    displayDenomination.originalDenomination should be(Denomination(99))
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
    new ChangeView().formatChangeAsDisplayString(change) should be("1 x £2.00, 2 x 20p")
  }

  it should "display dollar signs for dollar formatted change" in {
    assertFormatForLocale(DisplayFormat.usFormat, 99, 100, "99¢", "$1.00")
  }

  it should "display euro signs for euro formatted change with region that uses euro suffix" in {
    assertFormatForLocale(DisplayFormat.germanyFormat, 99, 100, "99c", "1,00 €")
  }

  private def assertFormatForLocale(format: DisplayFormat, cents: Int, whole: Int, expectedCents: String, expectedWhole: String) = {
    val centDenomination = ChangeView.mapToDisplayDenomination(Denomination(cents), format)
    centDenomination.originalDenomination should be(Denomination(cents))
    centDenomination.toString should be(expectedCents)

    val wholeDenominiation = ChangeView.mapToDisplayDenomination(Denomination(whole), format)
    wholeDenominiation.originalDenomination should be(Denomination(whole))
    wholeDenominiation.toString should be(expectedWhole)
  }
}
