# Tips

## Function Values

Note that unlike methods, **function values cannot have optional arguments (i.e. with default values) and cannot take type parameters via the [T] syntax**. When a method is converted into a function value, any optional arguments must be explicitly included, and type parameters fixed to concrete types. Function values are also anonymous, which makes stack traces involving them less convenient to read than those using methods.

In general, you should prefer using methods unless you really need the flexibility to pass as parameters or store them in variables. But if you need that flexibility, function values are a great tool to have.

One common use case of function values is to pass them into methods that take function parameters. Such methods are often called **"higher order methods"**.

## Usage of underscore(_) in Scala
[Read](https://www.baeldung.com/scala/underscore)

## Basic Intro
[Introduction to Scala](https://www.baeldung.com/scala/scala-intro)

## Scala - by category
* https://www.baeldung.com/scala/category/scala-basics
* https://www.baeldung.com/scala/category/functional-programming
* https://www.baeldung.com/scala/category/type-level-programming
* https://www.baeldung.com/scala/category/libraries
* https://www.baeldung.com/scala/category/play-framework

## Placeholder syntax

The placeholder syntax for function literals also works for multi-argument functions, e.g. ```(x, y) => x + y``` can be written as ```_ + _```.

Any method that takes a function as an argument can also be given a method reference, as long as the method's signature matches that of the function type, here Int => Int: (See below)

```scala
class Box(var x: Int) {
  def update(f: Int => Int) = x = f(x)
  def printMsg(msg: String) = {
    println(msg + x)
  }
}
def increment(i: Int) = i + 1

val b = new Box(123)

b.update(increment) // Providing a method reference

b.update(x => increment(x)) // Explicitly writing out the function literal

b.update{x => increment(x)} // Methods taking a single function can be called with {}s

b.update(increment(_)) // You can also use the `_` placeholder syntax
```