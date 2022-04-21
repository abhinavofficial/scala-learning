# Functions and Data

Data Structure - In scala, we do this by defining a class.

## Class

```scala
class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y
}
```
The definition above introduces two entities:
* A new _type_, named Rational
* A _constructor_ Rational to create elements of this type

Scala keeps the names of types and values in _different namespaces_. So, there is no conflict between the two definitions of Rational.

We call the elements of a class type as **objects**. We create an object by prefixing an application of the constructor of the class with operator _new_. Example, ```new Rational(1, 2)```

Objects of the class Rational have two members, numer and denom. We select the members of an object with the infix operation '.'

Functions that are put in classes are called **methods**. We can have **private** members in a class which can only be accessed from inside the class.

As learnt before, def is calculated each time it is called. val is computed only once.

The ability to choose different implementations of the data without affecting clients is called **data abstraction**.

**this** is available in scala, similar to java. On the inside of the class, the name this represents the object on which the current method is executed (also known as self reference). Note that a simple name ```x```, which refers to another member of the class, is really an abbreviation of ```this.x```

Class also have a pre-defined function ```require``` which takes a condition and an optional message string. It is a test that is performed when the class is initialized. If the test fails, it would give you an IllegalArgument exception.

```scala
class Rational(x: Int, y: Int) {
  require(y != 0, "denominator cannot be zero")
}
```

Besides, require, there is also ```assert```. Assert also takes a condition and an optional message string as parameters. E.g. ```val x = sqrt(y); assert(x>0)```. Like require, a failing asset will also throw an exception, but it is different one, AssertionError.

There is difference in intent of usage:
* _require_ is used to enforce a precondition on the caller of a function
* _assert_ is used to check the code of the function itself.

## Constructors
In scala, a class implicitly introduces a constructor. This one is called the _primary constructor_ of the class. The primary constructor:
* takes the parameters of the class
* and executes all the statements in the class body (such as the _require_)

You can create more constructors. Please see below the syntax (use of _this_):
```scala
class Rational(x: Int, y: Int) {
  def this(x: Int) = this(x, 1)
}
```

Now let's understand how substitution model works with classes and objects.
* Qn: How is an instantiation of the class ```new C(e1,...,en)``` evaluated?
* Ans: The expression arguments e1,...,en are evaluated like the arguments of a normal function. That's it. The resulting expression, say, ```new C(v1,...,vn)``` is already a value
* Qn: Now supposed that we have a class definition class ```C(x1,...,xm){... def f(y1,...,yn)=b ...}``` where the formal parameters of the class are (x1,...,xm) and class defines a method f with formal parameters (y1,...,yn). How is the ```new C(v1,...,vm).f(w1,...,wn)``` evaluated?
* Ans: The expression ```new C(v1,...,vm).f(w1,...,wn)``` is rewritten to ```[w1/y1,...,wn/yn][v1/x1,...,vm/wm][new C(v1,...,vm)/this]b```. There are three substitutions at work here:
  * The substitution of the formal parameters (y1,...,yn) of the function f by arguments (w1,...,wn)
  * The substitution of the formal parameters (x1,...,xm) of the class C by the class arguments (v1,...,vm)
  * The substitution of the self reference this by the value of the object new C(v1,...,vm)

* Can we use Rational number like Int or Double with operators similar, like + or -
* To do this, scala follows two steps process.

**Step 1: Infix notation for methods**

Any method with a parameter can be used like an infix operator. It is therefore possible to write
* ```r add s``` in place of ```r.add(s)```
* ```r less s``` in place of ```r.less(s)```
* ```r max s``` in place of ```r.max(s)```

**Step 2: Relaxed operators** 

Operators can be used as identifiers. Thus, an identifier can be:
* _alphanumeric_: starting with a letter, followed by a sequence of letter or numbers
* _symbolic_: starting with an operator symbol, followed by other operator symbols.
* The underscore character '_' counts as a letter
* _alphanumeric_ identifiers can also end in an underscore followed by some operator symbols.
Examples of identifiers:
x1  *  +?%&  vector_++  counter_=

So, now we can write
```scala
class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y
  def + (that: Rational) = new Rational(numer * that.denom + denom * that.numer,
                                        denom * that.denom)
  def - (that: Rational) = this + -that
  // The prefix - is different that minus (-). In scala, we use unary_
  def unary_- :Rational = new Rational(-numer, denom)
}
```

**Precedence Rule**

The precedence of an operator is determined by its first character. The following table lists the characters in increasing order of priority precedence:
* (all letters)
* |
* ^
* &
* < >
* = !
* :
* "+" -
* / * %
* (all other special characters)