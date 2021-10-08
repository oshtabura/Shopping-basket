package com.adthena.shopping.basket.core

import com.adthena.shopping.basket.core.model.DiscountData

trait Discount {
  def calculate(groupedBasket: Map[String, Int], products: Map[String, BigDecimal]): List[DiscountData]
}
