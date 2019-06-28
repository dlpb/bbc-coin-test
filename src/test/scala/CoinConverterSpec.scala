import org.scalatest.{FlatSpec, Matchers, WordSpec}

class CoinConverterSpec extends FlatSpec with Matchers {

  "Coin Converter" should "return the correct amount" in {
      val actual = CoinConverter.convertToCoins(123)
      assert(actual ==  "1 x £1.00, 1 x 20p, 1 x 2p, 1 x 1p")
  }

}
