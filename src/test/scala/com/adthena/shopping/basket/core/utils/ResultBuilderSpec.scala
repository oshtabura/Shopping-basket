package com.adthena.shopping.basket.core.utils

import com.adthena.shopping.basket.core.UnitSpec
import com.adthena.shopping.basket.core.model.DiscountData
import org.mockito.Mockito

class ResultBuilderSpec extends UnitSpec {
  "ResultBuilder" should "build result" in {
    //setup
    val formatterMock    = mock[Formatter]
    val discountDataMock = mock[DiscountData]
    val resultBuilder    = ResultBuilder(formatterMock)
    val subtotal         = BigDecimal(5.1)
    val discount         = BigDecimal(1.1)
    Mockito.when(formatterMock.subtotalMessage(subtotal)).thenReturn(subtotal.toString())
    Mockito.when(formatterMock.discountMessage(discountDataMock)).thenReturn("discount")
    Mockito.when(discountDataMock.discount).thenReturn(discount)
    Mockito.when(formatterMock.totalPriceMessage(subtotal - discount)).thenReturn("total")

    //run
    val actualResult = resultBuilder
      .withErrors(List("Warning: missing product"))
      .withDiscounts(List(discountDataMock))
      .withSubtotal(subtotal)
      .build()

    //test
    actualResult should equal("Warning: missing product\n5.1\ndiscount\ntotal\n")
  }

  it should "show now discounts" in {
    //setup
    val formatterMock = mock[Formatter]
    val resultBuilder = ResultBuilder(formatterMock)
    val subtotal      = BigDecimal(5.1)
    Mockito.when(formatterMock.subtotalMessage(subtotal)).thenReturn(subtotal.toString())
    Mockito.when(formatterMock.noOfferMessage()).thenReturn("no discount")
    Mockito.when(formatterMock.totalPriceMessage(subtotal)).thenReturn("total")

    //run
    val actualResult = resultBuilder
      .withDiscounts(List.empty)
      .withSubtotal(subtotal)
      .build()

    //test
    actualResult should equal("5.1\nno discount\ntotal\n")
  }
}
