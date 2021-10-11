package com.adthena.shopping.basket.core

trait Validation[T] {
  def verify(obj: T): Iterable[String]
}
