package example

object NewtonSquare:

  def sqrt(x: Double) = {
    def abs(x:Double) = if (x < 0) -x else x

    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double) =
      val magnitude = java.math.BigDecimal(x).toPlainString.length - 1
      val divisor = if(magnitude > 3 && magnitude < 20) Math.pow(10, magnitude) else 1000000
      abs(guess * guess - x) <= x / divisor

    def improve(guess: Double) = (guess + x / guess) / 2

    sqrtIter(1.0)
  }


