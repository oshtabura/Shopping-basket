package com.adthena.shopping.basket.core.ops

import com.adthena.shopping.basket.core.utils.Utils

class SubtotalCalculator {
  def calculate(groupedBasket: Map[String, Int], products: Map[String, BigDecimal]): BigDecimal = {
    groupedBasket
      .foldLeft(Utils.Zero) {
        case (totalPrice, (productName, productCount)) =>
          products.getOrElse(productName, Utils.Zero) * productCount + totalPrice
      }
  }
}
