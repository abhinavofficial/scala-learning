package example

object Lists {

  /**
   * This method computes the sum of all elements in the list xs. There are
   * multiple techniques that can be used for implementing this method, and
   * you will learn during the class.
   *
   * For this example assignment you can use the following methods in class
   * `List`:
   *
   *  - `xs.isEmpty: Boolean` returns `true` if the list `xs` is empty
   *  - `xs.head: Int` returns the head element of the list `xs`. If the list
   *    is empty an exception is thrown
   *  - `xs.tail: List[Int]` returns the tail of the list `xs`, i.e. the the
   *    list `xs` without its `head` element
   *
   * ''Hint:'' instead of writing a `for` or `while` loop, think of a recursive
   * solution.
   *
   * @param xs A list of natural numbers
   * @return The sum of all elements in `xs`
   */
  def sum(xs: List[Int]): Int = {
    if (xs.isEmpty)
      0
    else
      xs.head + sum(xs.takeRight(xs.length - 1))
  }


  /**
   * This method returns the largest element in a list of integers. If the
   * list `xs` is empty it throws a `java.util.NoSuchElementException`.
   *
   * You can use the same methods of the class `List` as mentioned above.
   *
   * ''Hint:'' Again, think of a recursive solution instead of using looping
   * constructs. You might need to define an auxiliary method.
   *
   * @param xs A list of natural numbers
   * @return The largest element in `xs`
   * @throws java.util.NoSuchElementException if `xs` is an empty list
   */
  def max(xs: List[Int]): Int = {
    if (xs.isEmpty)
      throw new IllegalArgumentException("No max in empty list")
    else if (xs.length == 1)
      xs(0)
    else {
      val m = max(xs.takeRight(xs.length - 1))
      if (m > xs(0)) m else xs(0)
    }
  }


  def and(x: Boolean, y: => Boolean): Boolean = {
    if (x) y else false
  }

  def or(x: Boolean, y: => Boolean): Boolean = {
    if (x) true else y
  }

  def sqrt(x: Double) = {

    def isGoodEnough(guess: Double): Boolean = {
      math.abs((guess * guess) - x) / x < 0.001
    }

    def improve(guess: Double): Double = (guess + x / guess) / 2

    def sqrIter(guess: Double): Double = {
      if (isGoodEnough(guess)) guess
      else sqrIter(improve(guess))
    }

    sqrIter(1.0)
  }

  def factorial_non_tail(n: Int) : Int = {
    if (n==0) 1 else n * factorial_non_tail(n -1 )
  }

  def factorial_tail(n: Int) : Int = {
    def loop(acc: Int, n: Int) : Int =
      if (n==0) acc
      else loop( acc * n, n-1)

    loop(1, n)
  }

  def pascal(c: Int, r: Int): Int = {
    if (c > r) 0
    else if (c <= 0 || r <= 0 || c == r) 1
    else pascal(c-1, r - 1) + pascal(c, r - 1)
  }

  def balance(chars: List[Char]): Boolean = {
    def incrementalBalance(acc: Int, subChars: List[Char]) : Int = {
      println(s"acc:$acc; List:$subChars")
      if (subChars.isEmpty) acc else {
        val distinction = (if (subChars.head == ')') -1
            else if (subChars.head == '(' && acc >= 0)  1
            else 0)
        incrementalBalance(acc + distinction, subChars.tail)
      }
    }
    if (incrementalBalance(0, chars) == 0) true else false
  }

  def countChange(money: Int, coins: List[Int]): Int = {

  }

}
