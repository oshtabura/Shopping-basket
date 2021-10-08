package com.adthena.shopping.basket.core.utils

import com.adthena.shopping.basket.core.UnitSpec
import com.adthena.shopping.basket.core.model.{ProductDiscountData, SpecialDiscountData}

import java.text.NumberFormat
import java.util.Locale

class FormatterSpec extends UnitSpec {
  val CurrencyFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.UK)
  var formatter: Formatter         = _

  before {
    formatter = new Formatter(CurrencyFormat)
  }

  after {
    formatter = null
  }

  "Formatter" should "show correct message for no offer" in {
    formatter.noOfferMessage() should equal("(No offers available)")
  }

  it should "show correct message for subtotal" in {
    formatter.subtotalMessage(BigDecimal(1.1)) should equal("Subtotal: £1.10")
  }

  it should "show correct message for total price" in {
    formatter.totalPriceMessage(BigDecimal(2.1)) should equal("Total price: £2.10")
  }

  it should "show correct message for product discount" in {
    formatter.discountMessage(ProductDiscountData(BigDecimal(1.1), "apples", BigDecimal(0.7))) should equal(
      "Apples 70% off: £1.10")
  }

  it should "show correct message for special discount" in {
    formatter.discountMessage(SpecialDiscountData(BigDecimal(1.1), "apples", 2, "orange", BigDecimal(0.5))) should equal(
      "Buy 2 of Apples and get Orange for 50% price: £1.10")
  }
}
