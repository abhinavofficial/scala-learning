# Recursion

## Tail Recursion

Implementation Consideration: If a function calls itself as its last action, the function's stack can be reused. This is called *tail recursion*. Tail recursive functions are iterative process.

In general, if the last action of a function consists of calling a function (which may be the same) and if one stack frame would be sufficient for both functions, then we call such calls as *tail-calls*.

```scala
def gcd(a: Int, b: Int): Int = if (b==0) a else gcd(b, a % b)
```

However, factorial is not tail-recursive because there is an n * also present. This means that last action include something else other than calling the function itself.

```scala
def factorial(n: Int) : Int = if (n==0) 1 else n * factorial(n-1)
```

In Scala, only directly recursive calls to the current function are optimized. One can require that a function is tail-recursive using a ```@tailrec``` annotation:

```scala
@tailrec
def gcd(a: Int, b: Int): Int = ...
```

If the annotation is given, and the implementation of gcd were not tail recursive, an error would be issued.

> The interest of tail recursion is mostly to avoid very deep recursive chains. JVM, for example limits maximum depth of recursion to couple to 2000.

> As Donald Knuth has said, premature optimization is the source of all evil.
And that's the model that's very valuable to follow.