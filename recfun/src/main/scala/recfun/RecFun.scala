package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if(c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def getCount(count: Int, ch: Char): Int = {
      if(ch == '(') count + 1
      else if(ch == ')') count - 1
      else count
    }

    @tailrec
    def balanceWithCount(chars: List[Char], count: Int): Boolean = {
      if(chars.isEmpty) count == 0
      else if(count < 0) false
      else
        balanceWithCount(chars.tail, getCount(count, chars.head))
    }

    balanceWithCount(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    var total = 0
    def countIter(money: Int, coins: List[Int]): Unit = {
      if(coins.nonEmpty){
        if(money > coins.head){
          countIter(money - coins.head, coins)
          countIter(money, coins.tail)
        } else if(money < coins.head) {
          countIter(money, coins.tail)
        } else if(money - coins.head == 0){
          total += 1
        }
      }
    }
    countIter(money, coins.sorted)
    total
  }
