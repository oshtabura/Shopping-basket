package com.adthena.shopping.basket

import com.adthena.shopping.basket.core.job.PriceBasketJob
import com.adthena.shopping.basket.core.model.Special
import com.adthena.shopping.basket.core.ops.{DiscountCalculator, ProductDiscount, SpecialDiscount, SubtotalCalculator}
import com.adthena.shopping.basket.core.utils.{Formatter, ResultBuilder}
import com.adthena.shopping.basket.core.Job
import com.typesafe.config.ConfigFactory

import java.text.NumberFormat
import java.util
import java.util.Locale
import collection.JavaConverters._

object Factory {
  val config = ConfigFactory.load

  def createJob(args: Array[String]): Job = args(0) match {
    case "PriceBasket" => createPriceBasketJob(args.tail)
    case _             => throw new IllegalArgumentException(s"Unknown job name - ${args(0)}")
  }

  private def createPriceBasketJob(arguments: Array[String]): PriceBasketJob = {
    val specialDiscounts = config
      .getAnyRefList(Config.SpecialDiscounts)
      .asScala
      .map(_.asInstanceOf[util.HashMap[String, Any]])
      .map(map =>
        Special(
          map.get(Config.SpecialRequiredProduct).asInstanceOf[String],
          map.get(Config.SpecialRequiredCount).asInstanceOf[Int],
          map.get(Config.SpecialDiscountProduct).asInstanceOf[String],
          BigDecimal(map.get(Config.SpecialDiscountAmount).asInstanceOf[String])
      ))
      .toList

    new PriceBasketJob(
      arguments.toList,
      loadMap(Config.Products),
      new SubtotalCalculator(),
      new DiscountCalculator(
        List(
          new ProductDiscount(loadMap(Config.ProductDiscounts)),
          new SpecialDiscount(specialDiscounts)
        )),
      ResultBuilder(
        new Formatter(NumberFormat.getCurrencyInstance(
          new Locale(config.getString(Config.LocaleLanguage), config.getString(Config.LocaleCountry)))))
    )
  }

  private def loadMap(key: String) = {
    config
      .getConfig(key)
      .entrySet()
      .asScala
      .map(entry => (entry.getKey, BigDecimal(entry.getValue.unwrapped().asInstanceOf[String])))
      .toMap
  }
}
