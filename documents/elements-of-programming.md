# Elements of Programming

Every non-trivial programming language provides:
* primitive expressions representing the simplest elements
* ways to *combine* expressions
* ways to *abstract* expressions, which introduce a name for an expression by which it can then be referred to.

Functional programming is a bit like using a calculator.

An interactive shell (or REPL Read-Eval-Print-Loop) lets one write expressions and responds with their value.

The scala REPL can be started by simply typing ```scala``` or ```sbt console```

## Evaluation

**A non-primitive** expression is evaluated as follows:
1. Take the leftmost , subject to the rule of precedence
2. Evaluate its operands (left before right)
3. Apply the operator to the operands.

**A name** is evaluated by replacing it with the right hand side of its definition. The evaluation process stops once it results in a value.

Definitions can have paramaters. Function parameters come with their type, which is given after a colon. If a return type is given, it follows the parameter list. For example, ```def square(x: Double) = x * x```

Primitive types are same as in Java, but are written capitalized, e.g.
```
Int      32-bit integers
Double   64-bit floating point numbers
Boolean  boolean values true and false
```

**Application of parameterized functions** are evaluated in a similar way as operators:
* Evaluate all function arguments, from left to right
* Replace the function application by function's right-hand side, and at the same time
* Replace the formal parameters of the function by the actual arguments

This scheme of expression evaluation is called the **substitution model**. The idea underlying this model is that all evaluation does is *reduce an expression to a value*. It can be applied to all expressions, as long as they have no side effects. The substitution model is formalized in the lambda-calculus, which gives a foundation for functional programming.

The interpreter reduces function arguments to values before rewriting the function application. One could apply the function to unreduced arguments.

> Not all expression reduce to a value in a finite number of steps (or terminate). Example: ```def loop: Int = loop```

||Reduced arguments|Unreduced arguments|
|--|-----------------|-------------------|
|Reduction Stategy|sumOfSquares(3, 2+2) </br> sumOfSquares(3, 4) </br> square(3) + square(4) </br> 3 * 3 + square(4) </br> 9 + square(4) </br> 9 + 4 * 4 </br> 9 + 16 </br> 25|sumOfSquares(3, 2+2) </br> square(3) + square(2+2) </br> 3*3 + square(2+2) </br> 9+ square(2+2) </br> 9 + (2+2) * (2+2) </br> 9 + 2 * (2+2) </br> 9 + 4 * 4 </br> 25|
|Known as| call-by-value (CBV)|call-by-name(CBN)|
|Advantage| It evaluates every function argument only once| Function argument is not evaluated if the corresponding parameter is unused in the evaluation of the body|

Both strategy reduce to the same final values as long as
* the reduced expression consists of pure functions, and
* both evaluation terminate

If CBV evaluation of an expression ```e``` terminates, then CBN evaluation of ```e``` terminates, too. However, vice versa is not true.

In Scala, we normally use call-by-value. Call-by-value is exponentially more efficient that call-by-name because it avoid repeated recomputations of argument expressions that call-by-name entails. The other argument for call by value is that it plays much nicer with, imperative
effects and side effects because you tend to know much better when expressions will
be evaluated.

But if the type of a function parameter starts with => it uses call-by-name. Example: ```def constOne(x: Int, y: => Int) = 1```
