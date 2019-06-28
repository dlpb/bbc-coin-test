object CoinConverter {

  def convertToCoins(amount: Int): String = {
    import DisplayFormat.defaultFormat

    val change = new ChangeCalculator(Denomination.infiniteAmount).calculateChange(amount)
    val view = new ChangeView().formatChangeAsDisplayString(change)
    view
  }

}
