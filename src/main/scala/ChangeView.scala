class ChangeView {
  def formatChangeAsDisplayString(change: Map[Denomination, Int])(implicit displayFormat: DisplayFormats = DisplayFormats.poundsFormat): String = change map {
    denomination => s"${denomination._2} x ${ChangeView.mapToDisplayDenomination(denomination._1, displayFormat)}"
  } mkString ", "
}

case class DisplayDenomination(originalDenomination: Denomination, displayValue: Int, prefix: Option[String], suffix: Option[String]) {
  override def toString: String = {
    s"${prefix.getOrElse("")}${displayValue}${suffix.getOrElse("")}"
  }
}

object ChangeView {
  def mapToDisplayDenomination(denomination: Denomination, displayFormat: DisplayFormats): DisplayDenomination = {
    if (denomination.value >= 100) {
      DisplayDenomination(denomination, denomination.value / 100, displayFormat.wholeUnitSymbol, None)
    } else {
      DisplayDenomination(denomination, denomination.value, None, displayFormat.decimalUnitSymbol)
    }
  }
}
object DisplayFormats {
  def poundsFormat = DisplayFormats(Some("£"), Some("p"))
  def euroFormat = DisplayFormats(Some("€"), None)

  implicit def defaultFormat: DisplayFormats = poundsFormat
}

case class DisplayFormats(wholeUnitSymbol: Option[String], decimalUnitSymbol: Option[String], separator: String = ", ", numberOfUnitString: String = "×")
