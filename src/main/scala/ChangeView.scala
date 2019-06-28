import java.text.NumberFormat
import java.util.Locale

class ChangeView {
  def formatChangeAsDisplayString(change: Map[Denomination, Int])(implicit displayFormat: DisplayFormat = DisplayFormat.ukFormat): String = change map {
    denomination => s"${denomination._2} ${displayFormat.numberOfUnitString} ${ChangeView.mapToDisplayDenomination(denomination._1, displayFormat)}"
  } mkString displayFormat.separator
}

case class DisplayDenomination(originalDenomination: Denomination, displayValue: String) {
  override def toString: String = {
   displayValue
  }
}

object ChangeView {
  def mapToDisplayDenomination(denomination: Denomination, displayFormat: DisplayFormat): DisplayDenomination = {
    val currencyFormat = NumberFormat.getCurrencyInstance(displayFormat.locale)

    DisplayDenomination(denomination, s"${formatCurrency(denomination.value, currencyFormat, displayFormat)}")
  }

  def formatCurrency(decimalValue: Int, currencyFormat: NumberFormat, displayFormat: DisplayFormat): String = {
    if(decimalValue >= 100) currencyFormat.format(decimalValue/ 100d)
    else s"${decimalValue}${displayFormat.decimalUnitSymbol.getOrElse("")}"
  }
}
object DisplayFormat {
  def ukFormat = DisplayFormat(Locale.UK, Some("p"))
  def usFormat = DisplayFormat(Locale.US,  Some("¢"))
  def germanyFormat = DisplayFormat(Locale.GERMANY, Some("c"))

  implicit def defaultFormat: DisplayFormat = ukFormat
}

case class DisplayFormat(
                           locale: Locale,
                           decimalUnitSymbol: Option[String],
                           separator: String = ", ",
                           numberOfUnitString: String = "x"
                         )
