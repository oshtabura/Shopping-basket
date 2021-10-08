package com.adthena.shopping.basket

object Application {
  def main(args: Array[String]): Unit = {
    require(args.length >= 1, "Please prove arguments for the job")

    val job = Factory.createJob(args)

    try {
      print(job.process())
    } catch {
      case e: Exception => println(s"Job failed, error message: ${e.getMessage}")
    }
  }
}
