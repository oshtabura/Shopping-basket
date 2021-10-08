package com.adthena.shopping.basket.core.ops

import com.adthena.shopping.basket.core.model.DiscountData
import com.adthena.shopping.basket.core.{Discount, UnitSpec}
import org.mockito.Mockito

class DiscountCalculatorSpec extends UnitSpec {
  "DiscountCalculator" should "calculate discounts" in {
    // setup
    val discountMock       = mock[Discount]
    val discountDataMock   = mock[DiscountData]
    val discountCalculator = new DiscountCalculator(List(discountMock))
    val groupedBasket      = Map("bread" -> 1, "milk" -> 2)
    val products           = Map("Bread" -> BigDecimal(1.3), "Milk" -> BigDecimal(1.3))
    Mockito.when(discountMock.calculate(groupedBasket, products)).thenReturn(List(discountDataMock))

    // run
    val actualResult = discountCalculator.calculate(groupedBasket, products)

    //test
    actualResult should equal(List(discountDataMock))
    Mockito.verify(discountMock).calculate(groupedBasket, products)
  }
}
