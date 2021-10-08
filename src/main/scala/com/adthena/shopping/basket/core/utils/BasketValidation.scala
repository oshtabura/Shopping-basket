package com.adthena.shopping.basket.core.utils

import com.adthena.shopping.basket.core.Validation

class BasketValidation(products: List[String], formatter: Formatter) extends Validation[Iterable[String]] {

  override def verify(basket: Iterable[String]): Unit = {
    for {
      item <- basket if !products.contains(item)
    } println(formatter.missingItemMessage(item))
  }
}
