package com.adthena.shopping.basket.core.model

case class Special(requiredProduct: String,
                   requiredCount: Int,
                   discountProduct: String,
                   percentageOfDiscount: BigDecimal)
