package com.adthena.shopping.basket.core.job

import com.adthena.shopping.basket.core.ops.{DiscountCalculator, SubtotalCalculator}
import com.adthena.shopping.basket.core.utils.ResultBuilder
import com.adthena.shopping.basket.core.{UnitSpec, Validation}
import org.mockito.Mockito

class PriceBasketJobSpec extends UnitSpec {
  "PriceBasketJob" should "process products" in {
    // setup
    val basket                 = List("Bread", "Milk", "Milk")
    val products               = Map("Bread" -> BigDecimal(1.3), "Milk" -> BigDecimal(1.3))
    val subtotalCalculatorMock = mock[SubtotalCalculator]
    val discountCalculatorMock = mock[DiscountCalculator]
    val resultBuilderMock      = mock[ResultBuilder]
    val basketValidationMock   = mock[Validation[Iterable[String]]]
    val subtotal               = 0.3
    val discounts              = List.empty
    val expectedResult         = "result"
    val groupedBasket          = Map("bread" -> 1, "milk" -> 2)

    Mockito
      .when(resultBuilderMock.withSubtotal(subtotal))
      .thenReturn(resultBuilderMock)
    Mockito.when(basketValidationMock.verify(groupedBasket.keys)).thenReturn(List.empty)
    Mockito.when(resultBuilderMock.withErrors(List.empty)).thenReturn(resultBuilderMock)
    Mockito.when(resultBuilderMock.withDiscounts(discounts)).thenReturn(resultBuilderMock)
    Mockito.when(resultBuilderMock.build()).thenReturn(expectedResult)
    Mockito.when(subtotalCalculatorMock.calculate(groupedBasket, products)).thenReturn(subtotal)
    Mockito.when(discountCalculatorMock.calculate(groupedBasket, products)).thenReturn(discounts)

    val job = new PriceBasketJob(basket,
                                 products,
                                 basketValidationMock,
                                 subtotalCalculatorMock,
                                 discountCalculatorMock,
                                 resultBuilderMock)

    // run
    val actualResult = job.process()

    // test
    actualResult should equal(expectedResult)
    Mockito.verify(subtotalCalculatorMock).calculate(groupedBasket, products)
    Mockito.verify(discountCalculatorMock).calculate(groupedBasket, products)
    Mockito.verify(resultBuilderMock).withSubtotal(subtotal)
    Mockito.verify(resultBuilderMock).withDiscounts(discounts)
    Mockito.verify(resultBuilderMock).withErrors(List.empty)
    Mockito.verify(resultBuilderMock).build()
    Mockito.verify(basketValidationMock).verify(groupedBasket.keys)
    Mockito.verifyNoMoreInteractions(subtotalCalculatorMock, discountCalculatorMock, resultBuilderMock)
  }
}
