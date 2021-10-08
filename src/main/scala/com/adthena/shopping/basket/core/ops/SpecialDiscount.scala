package com.adthena.shopping.basket.core.ops

import com.adthena.shopping.basket.core.Discount
import com.adthena.shopping.basket.core.model.{DiscountData, Special, SpecialDiscountData}

class SpecialDiscount(specials: List[Special]) extends Discount {

  override def calculate(groupedBasket: Map[String, Int], products: Map[String, BigDecimal]): List[DiscountData] = {

    for {
      special              <- specials
      productCount         <- groupedBasket.get(special.requiredProduct) if products.contains(special.requiredProduct)
      discountProductCount <- groupedBasket.get(special.discountProduct)
      discountProductPrice <- products.get(special.discountProduct)
      countOfSpecials  = productCount / special.requiredCount if countOfSpecials > 0
      countOfDiscounts = calculateCountOfDiscounts(discountProductCount, countOfSpecials)
      discount         = countOfDiscounts * discountProductPrice * special.percentageOfDiscount
    } yield
      SpecialDiscountData(discount,
                          special.requiredProduct,
                          special.requiredCount,
                          special.discountProduct,
                          special.percentageOfDiscount)
  }

  private def calculateCountOfDiscounts(discountProductCount: Int, countOfSpecials: Int): BigDecimal = {
    if ((countOfSpecials - discountProductCount) <= 0) {
      BigDecimal(countOfSpecials)
    } else {
      BigDecimal(discountProductCount)
    }
  }
}
