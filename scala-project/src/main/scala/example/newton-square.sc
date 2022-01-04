def sum(f: Int => Int, a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if a > b then acc
    else loop(a + 1, acc + f(a))
  }
  loop(a, 0)
}

println(sum(x => x, 1, 5))
println(sum(x => x*x*x, 1, 5))
