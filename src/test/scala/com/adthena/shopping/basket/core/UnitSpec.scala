package com.adthena.shopping.basket.core

import org.mockito.ArgumentMatchersSugar
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatestplus.mockito.MockitoSugar

trait UnitSpec
    extends AnyFlatSpec
    with MockitoSugar
    with should.Matchers
    with BeforeAndAfter
    with ArgumentMatchersSugar {}
