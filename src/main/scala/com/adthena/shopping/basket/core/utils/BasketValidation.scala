package com.adthena.shopping.basket.core.utils

import com.adthena.shopping.basket.core.Validation

class BasketValidation(products: List[String], formatter: Formatter) extends Validation[Iterable[String]] {

  override def verify(basket: Iterable[String]): Iterable[String] = {
    for {
      item <- basket if !products.contains(item)
    } yield formatter.missingItemMessage(item)
  }
}
