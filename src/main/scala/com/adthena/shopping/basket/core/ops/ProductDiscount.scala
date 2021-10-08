package com.adthena.shopping.basket.core.ops

import com.adthena.shopping.basket.core.Discount
import com.adthena.shopping.basket.core.model.{DiscountData, ProductDiscountData}

class ProductDiscount(productsDiscount: Map[String, BigDecimal]) extends Discount {

  override def calculate(groupedBasket: Map[String, Int], products: Map[String, BigDecimal]): List[DiscountData] = {
    (for {
      (product, percentageOfDiscount) <- productsDiscount
      productCount                    <- groupedBasket.get(product)
      productPrice                    <- products.get(product)
      productDiscount = productCount * productPrice * percentageOfDiscount
    } yield ProductDiscountData(productDiscount, product, percentageOfDiscount)).toList
  }
}
