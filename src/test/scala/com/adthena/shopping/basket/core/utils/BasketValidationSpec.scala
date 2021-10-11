package com.adthena.shopping.basket.core.utils

import com.adthena.shopping.basket.core.UnitSpec
import org.mockito.Mockito

class BasketValidationSpec extends UnitSpec {

  "BasketValidation" should "print error message when item from basket is wrong" in {
    //setup
    val products         = List("apples")
    val basket           = List("cup")
    val formatterMock    = mock[Formatter]
    val basketValidation = new BasketValidation(products, formatterMock)
    Mockito.when(formatterMock.missingItemMessage(basket.head)).thenReturn("error")

    //run
    val actualResult = basketValidation.verify(basket)

    //test
    actualResult should equal(List("error"))
  }
}
