# Common Issues

## 1 Avoid Casts and Type Tests

Never use isInstanceOf or asInstanceOf - there’s always a better solution, both for the assignments, and also for any real-world Scala project. If you find yourself wanting to use casts, take a step back and think about what you’re trying to achieve.

## 2 Indentation

Make sure your code is properly indented, it becomes a lot more readable.

This might seem trivial and not very relevant for our exercises, but imagine yourself in the future being part of a team, working on the same files with other coders: it is very important that everybody respects the style rules to keep the code healthy.

If your editor does not do indentation the way you would like it to have, you should find out how to change its settings. In Scala, the standard is to indent using 2 spaces (no tabs).

## 3 Line Length and Whitespace

Make sure the lines are not too long, otherwise your code is very hard to read. Instead of writing very long lines, introduce some local value bindings. Using whitespace uniformly makes your code more readable.


## 4 Use local Values to simplify complex Expressions

When writing code in functional style, methods are often implemented as a combination of function calls. If such a combined expression grows too big, the code might become hard to understand.

In such cases it is better to store some arguments in a local value before passing them to the function. Make sure that the local value has a meaningful name.

## 5 Choose meaningful Names for Methods and Values

The names of methods, fields and values should be carefully chosen so that the source code is easy to understand. A method name should make it clear what the method does. No, temp is never a good name :-)

## 6 Common Subexpressions

You should avoid unnecessary invocations of computation-intensive methods. If each invocation is expensive (e.g. has to traverse an entire data structure) and does not have a side-effect, you can save one by introducing a local value binding.
This becomes even more important if the function is invoked recursively: in this case the method is not only invoked multiple times, but an exponential number of times.

## 7 Don’t Copy-Paste Code!

Copy-pasting code is always a warning sign for bad style! There are many disadvantages:

* The code is longer, it takes more time to understand it
* If the two parts are not identical, but very similar, it is very difficult to spot the differences
* Maintaining two copies and making sure that they remain synchronized is very error-prone
* The amount of work required to make changes to the code is multiplied

You should factor out common parts into separate methods instead of copying code around. 

## 8 Scala doesn’t require Semicolons

Semicolons in Scala are only required when writing multiple statements on the same line. Writing unnecessary semicolons should be avoided

## 9 Don’t publish Code with “print” Statements

You should clean up your code and remove all print or println statements before publishing it. The final code should be free of debugging statements.

## 10 Avoid using Return

In Scala, you often don’t need to use explicit returns because control structures such as if are expressions.

## 11 Avoid mutable local Variables

A purely functional style should be without using side-effecting operations. You can often rewrite code that uses mutable local variables to code with helper functions that take accumulators. 

## 12 Eliminate redundant “If” Expressions