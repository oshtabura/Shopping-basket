package com.adthena.shopping.basket.core.ops

import com.adthena.shopping.basket.core.UnitSpec
import com.adthena.shopping.basket.core.utils.Utils

class SubtotalCalculatorSpec extends UnitSpec {
  "SubtotalCalculator" should "calculate subtotal" in {
    // setup
    val groupedBasket = Map("apples" -> 2, "bread" -> 1, "milk" -> 1)
    val products =
      Map("apples" -> BigDecimal(1.0), "milk" -> BigDecimal(2.0), "bread" -> BigDecimal(0.8), "soup" -> BigDecimal(3.0))
    val subtotalCalculator = new SubtotalCalculator()

    //run
    val actual = subtotalCalculator.calculate(groupedBasket, products)

    //test
    actual should equal(BigDecimal(4.8))
  }

  it should " return zero if basket is empty" in {
    // setup
    val groupedBasket      = Map[String, Int]()
    val products           = Map("apples" -> BigDecimal(1.0))
    val subtotalCalculator = new SubtotalCalculator()

    //run
    val actual = subtotalCalculator.calculate(groupedBasket, products)

    //test
    actual should equal(Utils.Zero)
  }
}
