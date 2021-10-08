package com.adthena.shopping.basket.core.utils

import com.adthena.shopping.basket.core.model.{DiscountData, ProductDiscountData, SpecialDiscountData}

import java.text.NumberFormat

class Formatter(currencyFormat: NumberFormat) {

  def subtotalMessage(subtotal: BigDecimal): String = {
    "Subtotal: %s".format(currencyFormat.format(subtotal))
  }

  def totalPriceMessage(totalPrice: BigDecimal): String = {
    "Total price: %s".format(currencyFormat.format(totalPrice))
  }

  def discountMessage(discountData: DiscountData): String = discountData match {
    case productDiscount: ProductDiscountData =>
      "%s %d%% off: %s".format(productDiscount.productName.capitalize,
                               (productDiscount.percentageOfDiscount * 100).intValue(),
                               currencyFormat.format(productDiscount.discount))
    case specialDiscount: SpecialDiscountData =>
      "Buy %d of %s and get %s for %d%% price: %s".format(
        specialDiscount.requiredCount,
        specialDiscount.requiredProduct.capitalize,
        specialDiscount.discountProduct.capitalize,
        (specialDiscount.percentageOfDiscount * 100).intValue(),
        currencyFormat.format(specialDiscount.discount)
      )
  }

  def noOfferMessage(): String = {
    "(No offers available)"
  }
}
