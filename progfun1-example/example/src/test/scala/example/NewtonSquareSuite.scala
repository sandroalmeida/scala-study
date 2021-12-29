package example

class NewtonSquareSuite extends munit.FunSuite:

  import NewtonSquare.*

  test("square of 2") {
    val value: BigDecimal = 1.4142
    val result = BigDecimal(sqrt(2)).setScale(4, BigDecimal.RoundingMode.HALF_UP)
    assertEquals(result, value)
  }

  test("square small number 0.001") {
    val value: BigDecimal = 0.0316
    val result = BigDecimal(sqrt(0.001)).setScale(4, BigDecimal.RoundingMode.HALF_UP)
    assertEquals(result, value)
  }

  test("large number") {
    val value: BigDecimal = 1000000000
    val result = BigDecimal(sqrt(1000000000000000000)).setScale(4, BigDecimal.RoundingMode.HALF_UP)
    assertEquals(result, value)
  }