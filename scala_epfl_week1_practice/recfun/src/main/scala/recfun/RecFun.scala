package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c <= 0 || r <= 0 || c == r) 1
    else pascal(c-1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def incrementalBalance(acc: Int, subChars: List[Char]) : Int = {
      if (subChars.isEmpty) acc else {
        val distinction = if (subChars.head == ')') -1
        else if (subChars.head == '(' && acc >= 0)  1
        else 0
        incrementalBalance(acc + distinction, subChars.tail)
      }
    }
    if (incrementalBalance(0, chars) == 0) true else false
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0)
      1
    else if (money > 0 && coins.nonEmpty) {
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
    else
      0
  }
}
