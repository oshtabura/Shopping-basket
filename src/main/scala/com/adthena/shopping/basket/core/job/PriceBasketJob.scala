package com.adthena.shopping.basket.core.job

import com.adthena.shopping.basket.core.ops.{DiscountCalculator, SubtotalCalculator}
import com.adthena.shopping.basket.core.utils.ResultBuilder
import com.adthena.shopping.basket.core.{Job, Validation}

class PriceBasketJob(basket: List[String],
                     products: Map[String, BigDecimal],
                     basketValidation: Validation[Iterable[String]],
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
      .withErrors(basketValidation.verify(groupedBasket.keys))
      .withSubtotal(subtotalCalculator.calculate(groupedBasket, products))
      .withDiscounts(discountCalculator.calculate(groupedBasket, products))
      .build()
  }
}
