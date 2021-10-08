package com.adthena.shopping.basket.core.ops

import com.adthena.shopping.basket.core.UnitSpec
import com.adthena.shopping.basket.core.model.{Special, SpecialDiscountData}

class SpecialDiscountSpec extends UnitSpec {

  "SpecialDiscount" should "calculate special discount" in {
    //setup
    val specials      = List(Special("apples", 2, "bread", BigDecimal(0.5)), Special("bread", 2, "milk", BigDecimal(0.2)))
    val groupedBasket = Map("apples" -> 5, "bread" -> 6, "milk" -> 1, "orange" -> 1)
    val products = Map("apples" -> BigDecimal(1.0),
                       "milk"   -> BigDecimal(2.0),
                       "bread"  -> BigDecimal(0.8),
                       "orange" -> BigDecimal(1.1))
    val specialDiscount = new SpecialDiscount(specials)

    //run
    val result = specialDiscount.calculate(groupedBasket, products)

    //test
    result should equal(
      List(SpecialDiscountData(BigDecimal(0.8), "apples", 2, "bread", BigDecimal(0.5)),
           SpecialDiscountData(BigDecimal(0.4), "bread", 2, "milk", BigDecimal(0.2))))
  }

  it should "not calculate discounts if they are empty" in {
    //setup
    val specials        = List()
    val groupedBasket   = Map("apples" -> 2, "bread" -> 2)
    val products        = Map("apples" -> BigDecimal(1.0), "bread" -> BigDecimal(0.8))
    val specialDiscount = new SpecialDiscount(specials)

    //run
    val result = specialDiscount.calculate(groupedBasket, products)

    //test
    result should equal(List.empty)
  }

  it should "not calculate discounts if the were not selected" in {
    //setup
    val specials        = List(Special("apples", 2, "bread", BigDecimal(0.5)))
    val groupedBasket   = Map("bread" -> 2)
    val products        = Map("apples" -> BigDecimal(1.0), "bread" -> BigDecimal(0.8))
    val specialDiscount = new SpecialDiscount(specials)

    //run
    val result = specialDiscount.calculate(groupedBasket, products)

    //test
    result should equal(List.empty)
  }

  it should "not calculate discounts if discount doesn't exist" in {
    //setup
    val specials        = List(Special("apples", 2, "bread", BigDecimal(0.5)))
    val groupedBasket   = Map("apples" -> 2, "bread" -> 2)
    val products        = Map("apples" -> BigDecimal(1.0))
    val specialDiscount = new SpecialDiscount(specials)

    //run
    val result = specialDiscount.calculate(groupedBasket, products)

    //test
    result should equal(List.empty)
  }

  it should "not calculate discounts if product doesn't exist" in {
    //setup
    val specials        = List(Special("apples", 2, "bread", BigDecimal(0.5)))
    val groupedBasket   = Map("apples" -> 2, "bread" -> 2)
    val products        = Map("bread" -> BigDecimal(0.8))
    val specialDiscount = new SpecialDiscount(specials)

    //run
    val result = specialDiscount.calculate(groupedBasket, products)

    //test
    result should equal(List.empty)
  }
}
