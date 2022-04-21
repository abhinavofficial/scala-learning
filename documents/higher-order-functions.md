# Higher Order Functions

## Definition
Functional languages treat functions as first-class values. This means, that like any other value, a function can be passed as a parameter and returned as a result. This provides a flexible way to compose programs. Functions that take other functions as parameters or that return functions as results are called higher order functions.

First order functions are the ones which work on primitive or complex data types like list, sets, etc.

## Function Type

The type ```A => B``` is a type of function that takes an argument of type A and returns a result of type B. So, ```Int => Int``` is a type of functions that map integers to integers.

```scala
def sum(f: Int => Int, a: Int, b:Int): Int =
  if (a > b) 0
  else f(a) + sum(f, a+1, b)

def sumIntegers(a: Int, b: Int)        = sum(id, a, b)
def sumCubes(a: Int, b: Int)           = sum(cube, a, b)
def sumFactorials(a: Int, b: Int)      = sum(fact, a, b)

//Where

def id(x: Int) : Int = x
def cube(x: Int): Int = x*x*x
def fact(x: Int): Int = if (x == 0) 1 else x * fact(x-1)
```

## Anonymous Functions
Passing functions as parameters leads to creation of many small functions. Sometimes, it is tedious to have to define (and name) these function using def. We would like to treat function like literals, which lets us write a function without giving it a name. These are called anonymous functions.

### Syntax

```scala
(x: Int) => x * x * x // Here (x: Int) is the parameter of the function and x * x * x is it's body.
```

The type of parameter can be omitted if it can be inferred by the compiler from the context. If there are several parameters, they are separated by commas.

Every anonymous function can be expressed using def. Example, ```(x1: T1, ... , xn: Tn) => E``` can be written as ```def f(x1: T1, ... , xn: Tn) = E; f``` where f is an arbitrary, fresh name (that's not yet used in the program). Occasionally, it can be wrapped into a block ```{..}``` to avoid any confusion on the name.

> One can therefore say that anonymous functions are *syntactic sugar*.

```scala
// We can now rewrite
def sumIntegers(a: Int, b: Int)        = sum(x => x, a, b)
def sumCubes(a: Int, b: Int)           = sum(x => x* x * x, a, b)
```

## Currying

Generally, function application associates to the left. This means, ```sum (cube)(1, 10) == (sum(cube)) (1, 10)``` sum of cube and then applied to (1, 10)

The definition of functions that return functions is so useful in functional programming that there is special syntax for it in Scala.

```scala
def sum(f: Int => Int)(a: Int, b: Int): Int = if (a > b) 0 else f(a) + sum(f)(a+1, b)
```

In general, a definition of a function with multiple parameter lists ```def f(args1)...(argsn) = E``` where n > 1, is equivalent to ```def f(args1)...(argsn-1) = {def g(argsn) = E; g}``` where g is a fresh identifier. Or for short: ```def f(args1)...(argsn-1) = (argsn) => E```

By repeating the process n times, ```def f(args1)...(argsn) = E``` is shown to be equivalent to ```def f = (args1 => (args2 => ... (argsn => E)...))```.

This style of definition in function application where essentially every function is mapped to an expression that consists of anonymous functions, nested anonymous functions, that each take one parameter, is called **currying**. It is named for its instigator, **Haskell Brooks Curry** (1900-1982), a twentieth century logician.

In fact, the idea goes back even further to _Schonfinkel_ and _Frege_ but the term "currying" has stuck.

Functional Types associate to the right. This means, ```Int => Int => Int``` is equivalent to ```Int => (Int => Int)```

