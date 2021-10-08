package com.adthena.shopping.basket.core.utils

import com.adthena.shopping.basket.core.UnitSpec
import org.mockito.Mockito

import java.io.ByteArrayOutputStream

class BasketValidationSpec extends UnitSpec {

  "BasketValidation" should "print error message when item from basket is wrong" in {
    //setup
    val products         = List("apples")
    val basket           = List("cup")
    val formatterMock    = mock[Formatter]
    val basketValidation = new BasketValidation(products, formatterMock)
    val stream           = new ByteArrayOutputStream()
    Mockito.when(formatterMock.missingItemMessage(basket.head)).thenReturn("error")

    //run
    Console.withOut(stream) {
      basketValidation.verify(basket)
    }

    //test
    stream.toString should equal("error\n")
  }
}
