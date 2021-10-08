package com.adthena.shopping.basket.core.model

sealed trait DiscountData {
  def discount: BigDecimal
}

case class ProductDiscountData(discount: BigDecimal, productName: String, percentageOfDiscount: BigDecimal)
    extends DiscountData

case class SpecialDiscountData(discount: BigDecimal,
                               requiredProduct: String,
                               requiredCount: Int,
                               discountProduct: String,
                               percentageOfDiscount: BigDecimal)
    extends DiscountData
