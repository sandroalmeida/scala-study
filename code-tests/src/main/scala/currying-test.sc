def product(f: Int => Int)(a: Int, b: Int): Int =
  if(a > b) 1 else f(a) * product(f)(a + 1, b)

product(x => x * x)(1, 5)

def fact(n: Int) = product(x => x)(1, n)

fact(5)

def mapReduce(f: Int => Int, combine: (Int, Int) => Int, digit: Int)(a: Int, b: Int): Int = {
  def recur(a: Int): Int = {
    if(a < b) digit
    else combine(f(a), recur(a + 1))
  }
  recur(a)
}

