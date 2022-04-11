# Blocks and Lexical Scope

It is good functional programming style to split up a task into many small functions. But the names like ```sqrtIter```, ```improve```, and ```isGoodEnough``` matter only for the implementation of sqrt, not for its usage. Normally we would like users to access these functions directly. One way we can achieve this and at the same time avoid "namespace pollution" by putting the auxillary function inside ```sqrt```, also sometimes called nesting.

```scala
def sqrt(x: Double) = {
  def isGoodEnough(guess: Double, x: Double): Boolean = {
    math.abs((guess * guess) - x) / x < 0.001
  }

  def improve(guess: Double, x: Double): Double = (guess + x/guess)/2

  def sqrIter(guess: Double, x: Double) : Double ={
    if(isGoodEnough(guess, x)) guess
    else sqrIter(improve(guess, x), x)
  }

  sqrIter(1.0, x)
}
```
What we did above, could be done using block.
**A block** is delimited by braces { ... }. It contains a sequence of definitions or expressions. The last element of a block is an expression that defines its value. This return expression can be preceded by auxiliary definitions. Blocks are themselves expression; a block may appear everywhere an expression can. There are two simple rules for visibility for a block.
* The definitions inside a block are only visible from within the block. 
* The definitions inside a block shadow definitions of the same names outside the block.

Based on above, definition of outer blocks are visible inside a block unless they are shadowed. Hence the above program can be rewritten.

```scala
def sqrt(x: Double) = {
  def isGoodEnough(guess: Double): Boolean = {
    math.abs((guess * guess) - x) / x < 0.001
  }

  def improve(guess: Double): Double = (guess + x/guess)/2

  def sqrIter(guess: Double) : Double ={
    if(isGoodEnough(guess)) guess
    else sqrIter(improve(guess))
  }

  sqrIter(1.0)
}
```

## Use of semicolon

In scala, semicolons at the end of lines are in most cases optional. On the other hand, if there are more than one statements/expressions on a line, they need to be separated by semicolons.

## Semicolons and infix operators

One issue with Scala's semicolon convention is how to write expressions that span several lines. For example, 
```
someLongExpression
    + someOtherExpression
```
will be interpreted as two expressions
```
someLongExpression;
+ someOtherExpression
``` 

Two ways to fix this -
* Multi-line expression can be written in parentheses, because semicolons are not inserted in them.
```
(someLongExpression
    + someOtherExpression)
```
* Use an operator on the first fine, because it tells the compiler that the expression is not yet finished. 
```
someLongExpression + 
someOtherExpression
```