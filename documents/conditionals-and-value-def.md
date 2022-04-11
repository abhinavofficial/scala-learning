# Conditionals and Value Definitions

## Conditional Expressions
To express choosing between two alternatives, Scala has conditional expression if-else. It looks like a if-else in Java, but is used for expressions, not statements. The boolean in the conditional expression is sometimes called predicate.

Boolean expressions b can be composed of
```
true false // constants
!b         // Negation
b && b     // Conjunction
b || b     // Disjunction
```
and of usual comparision operations: ```e <= e, e >= e, e < e, e > e, e == e, e != e```

Boolean expressions also follow reduction rule
```scala
// e is a boolean expression
!true      --> false
!false     --> true
true && e  --> e
false && e --> false
true || e  --> true
false || e --> e
```

Note that && and || do not always need their right operand to be evaluated. We say, these expressions use "short-circuit evaluation".

## Value Definitions
We have seen that function paramaters can be passed by value or passed by name. The same distinction applies to definitions. The ```def``` form is "by-name", its right hand side is evaluated on each use. There is also a val for, which is "by-val", its right-hand sode is evaluated at the point of the definition itself - afterwards, the name refers to the value. Based on this, you can imagine that when the right hand side does not terminate, def on that would be okay but val would lead to an infinite loop.

Recursive function is when its right-hand side calls itself (typically based on some condition)

> Recursive functions need an explicit return type in Scala. This is to help compile understand what would be return of the function. For non-recursive functions, the return type is optional though recursive.