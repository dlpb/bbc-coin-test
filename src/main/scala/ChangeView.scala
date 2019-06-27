class ChangeView {
  def map(change: Map[Denomination, Int]): String = change map {
    denomination => s"${denomination._2}x${ChangeView.map(denomination._1)}"
  } mkString ", "
}

case class DisplayDenomination(originalDenomination: Denomination, displayValue: Int, prefix: Option[String], suffix: Option[String]) {
  override def toString: String = {
    s"${prefix.getOrElse("")}${displayValue}${suffix.getOrElse("")}"
  }
}

object ChangeView {
  def map(denomination: Denomination): DisplayDenomination = {
    if(denomination.value >= 100) {
      DisplayDenomination(denomination, denomination.value / 100, Some("£"), None)
    } else {
      DisplayDenomination(denomination, denomination.value, None, Some("p"))
    }
  }
}
