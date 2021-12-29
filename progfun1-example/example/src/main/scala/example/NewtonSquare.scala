package example

object NewtonSquare:

  def abs(x:Double) = if (x < 0) -x else x

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  def isGoodEnough(guess: Double, x: Double) =
    val magnitude = java.math.BigDecimal(x).toPlainString.length - 1
    val divisor = if(magnitude > 3 && magnitude < 20) Math.pow(10, magnitude) else 1000000
    abs(guess * guess - x) <= x / divisor

  def improve(guess: Double, x: Double) = (guess + x / guess) / 2

  def sqrt(x: Double) = sqrtIter(1.0, x)
