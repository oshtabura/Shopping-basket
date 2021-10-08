package com.adthena.shopping.basket.core.job

import com.adthena.shopping.basket.core.ops.{DiscountCalculator, SubtotalCalculator}
import com.adthena.shopping.basket.core.Job
import com.adthena.shopping.basket.core.utils.ResultBuilder

class PriceBasketJob(basket: List[String],
                     products: Map[String, BigDecimal],
                     subtotalCalculator: SubtotalCalculator,
                     discountCalculator: DiscountCalculator,
                     resultBuilder: ResultBuilder)
    extends Job {

  override def process(): String = {
    val groupedBasket = basket
      .map(_.toLowerCase)
      .groupBy(identity)
      .mapValues(_.length)

    resultBuilder
      .withSubtotal(subtotalCalculator.calculate(groupedBasket, products))
      .withDiscounts(discountCalculator.calculate(groupedBasket, products))
      .build()
  }
}
