def mapReduce(f: Int => Int, combine: (Int, Int) => Int, digit: Int)(a: Int, b: Int): Int =
  def recur(a: Int): Int =
    if a > b then digit
    else combine(f(a), recur(a + 1))
  recur(a)

def product(f: Int => Int) = mapReduce(f, (x, y) => x * y, 1)
product(identity)(1,6)

def fact(n: Int) = product(x => x)(1, n)
fact(5)

def sum(f: Int => Int) = mapReduce(f, (x, y) => x + y, 0)
sum(fact)(1, 5)
sum(identity)(1,5)


