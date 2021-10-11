package com.adthena.shopping.basket.core.utils

import com.adthena.shopping.basket.core.model.DiscountData

import java.util.StringJoiner

case class ResultBuilder(formatter: Formatter,
                         subtotal: BigDecimal = Utils.Zero,
                         discounts: List[DiscountData] = List.empty,
                         errors: Iterable[String] = List.empty) {

  def withSubtotal(subtotal: BigDecimal): ResultBuilder = copy(subtotal = subtotal)

  def withDiscounts(discounts: List[DiscountData]): ResultBuilder = copy(discounts = discounts)

  def withErrors(errors: Iterable[String]): ResultBuilder = copy(errors = errors)

  def build(): String = {
    val result = new StringJoiner("\n", "", "\n")
    errors.foreach(result.add)
    result.add(subtotalMessage())

    discountMessages().foreach(result.add)

    result
      .add(totalPriceMessage())
      .toString
  }

  private def subtotalMessage(): String = formatter.subtotalMessage(subtotal)

  private def discountMessages(): List[String] = discounts match {
    case Nil  => List(formatter.noOfferMessage())
    case list => list.map(formatter.discountMessage)
  }

  private def totalPriceMessage(): String = {
    val discount =
      discounts.foldLeft(Utils.Zero)((totalDiscount, discountData) => totalDiscount + discountData.discount)
    formatter.totalPriceMessage(subtotal - discount)
  }
}
