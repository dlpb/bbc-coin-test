class ChangeView {
  def formatChangeAsDisplayString(change: Map[Denomination, Int]): String = change map {
    denomination => s"${denomination._2} x ${ChangeView.mapToDisplayDenomination(denomination._1)}"
  } mkString ", "
}

case class DisplayDenomination(originalDenomination: Denomination, displayValue: Int, prefix: Option[String], suffix: Option[String]) {
  override def toString: String = {
    s"${prefix.getOrElse("")}${displayValue}${suffix.getOrElse("")}"
  }
}

object ChangeView {
  def mapToDisplayDenomination(denomination: Denomination): DisplayDenomination = {
    if(denomination.value >= 100) {
      DisplayDenomination(denomination, denomination.value / 100, Some("£"), None)
    } else {
      DisplayDenomination(denomination, denomination.value, None, Some("p"))
    }
  }
}
