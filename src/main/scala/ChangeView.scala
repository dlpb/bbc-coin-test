class ChangeView {

}

case class DisplayDenomination(originalDenomination: Denomination, displayValue: Int, prefix: Option[String], suffix: Option[String]) {
  override def toString: String = {
    s"${prefix.getOrElse("")}${displayValue}${suffix.getOrElse("")}"
  }
}

object ChangeView {
  def map(denomination: Denomination): DisplayDenomination = {
    DisplayDenomination(denomination, denomination.value, None, Some("p"))
  }
}
