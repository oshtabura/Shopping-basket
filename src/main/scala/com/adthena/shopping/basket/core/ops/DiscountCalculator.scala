package com.adthena.shopping.basket.core.ops

import com.adthena.shopping.basket.core.Discount
import com.adthena.shopping.basket.core.model.DiscountData

class DiscountCalculator(discounts: List[Discount]) {
  def calculate(groupedBasket: Map[String, Int], products: Map[String, BigDecimal]): List[DiscountData] = {
    discounts.flatMap(_.calculate(groupedBasket, products))
  }
}
