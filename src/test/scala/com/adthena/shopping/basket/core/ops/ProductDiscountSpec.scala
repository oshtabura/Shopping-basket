package com.adthena.shopping.basket.core.ops

import com.adthena.shopping.basket.core.UnitSpec
import com.adthena.shopping.basket.core.model.ProductDiscountData

class ProductDiscountSpec extends UnitSpec {

  "ProductDiscount" should "calculate product discount" in {
    //setup
    val productsDiscount = Map("apples" -> BigDecimal(0.1), "milk" -> BigDecimal(0.5))
    val groupedBasket    = Map("apples" -> 2, "bread" -> 1, "milk" -> 1)
    val products         = Map("apples" -> BigDecimal(1.0), "milk" -> BigDecimal(2.0), "bread" -> BigDecimal(0.8))
    val productDiscount  = new ProductDiscount(productsDiscount)

    //run
    val result = productDiscount.calculate(groupedBasket, products)

    //test
    result.length should equal(2)
    result should equal(
      List(ProductDiscountData(BigDecimal(0.2), "apples", BigDecimal(0.1)),
           ProductDiscountData(BigDecimal(1.0), "milk", BigDecimal(0.5))))
  }

  it should "not calculate discounts if they are empty" in {
    //setup
    val productsDiscount = Map[String, BigDecimal]()
    val groupedBasket    = Map("apples" -> 2, "bread" -> 1)
    val products         = Map("apples" -> BigDecimal(1.0), "bread" -> BigDecimal(0.8))
    val productDiscount  = new ProductDiscount(productsDiscount)

    //run
    val result = productDiscount.calculate(groupedBasket, products)

    //test
    result.length should equal(0)
  }

  it should "not calculate discounts if the were not selected" in {
    //setup
    val productsDiscount = Map("apples" -> BigDecimal(0.1))
    val groupedBasket    = Map("bread" -> 1)
    val products         = Map("apples" -> BigDecimal(1.0), "bread" -> BigDecimal(0.8))
    val productDiscount  = new ProductDiscount(productsDiscount)

    //run
    val result = productDiscount.calculate(groupedBasket, products)

    //test
    result.length should equal(0)
  }

  it should "not calculate discounts if product doesn't exist" in {
    //setup
    val productsDiscount = Map("apples" -> BigDecimal(0.1))
    val groupedBasket    = Map("bread" -> 1, "apples" -> 1)
    val products         = Map("bread" -> BigDecimal(0.8))
    val productDiscount  = new ProductDiscount(productsDiscount)

    //run
    val result = productDiscount.calculate(groupedBasket, products)

    //test
    result.length should equal(0)
  }
}
