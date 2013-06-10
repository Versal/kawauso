package com.versal.kawauso.tests

class ArithmeticTest extends org.scalatest.FunSuite {

  import com.versal.kawauso.Arithmetic
  import com.versal.fireotter._

  val specs: Traversable[Seq[String]] = csv(resource("arithmetic.csv"))

  specs foreach { spec =>

    val input1: Int = spec(2).toInt
    val input2: Int = spec(3).toInt

    val output = spec(1) match {
      case "add"      => Arithmetic.add(input1, input2)
      case "subtract" => Arithmetic.subtract(input1, input2)
      case "multiply" => Arithmetic.multiply(input1, input2)
      case "divide"   => Arithmetic.divide(input1, input2)
    }

    val expectedOutput: Int = spec(4).toInt

    test(spec(0)) { assert(output === expectedOutput) }
  }

}
