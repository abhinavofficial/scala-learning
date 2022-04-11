# Functional Programming Using Scala

## Section 1
* [Functional Programming Paradigm](documents/programming-paradigms.md)
* [Elements of Programming](documents/elements-of-programming.md)
* [Conditionals and Value Definitions](documents/conditionals-and-value-def.md)
* [Blocks and Lexical Scope](documents/blocks-lexical-scope.md)
* [Recursions](documents/recursions.md)

### Summary

**Evaluating a Function Application**

One simple rule: One evaluates a function application, f(e1,..,en)
* by evaluating the expression e1,...,en resulting in values v1,...,vn
* by replacing the application with the body of the function f, in which
* the actual parameters v1,...vn replace the formal parameters of f (at the same time).

This is called substitution.

