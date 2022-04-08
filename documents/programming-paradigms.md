# Programming Paradigms

Paradigm: In science, a paradigm describes distinct concepts or thought pattern in some scientific discipline. Main programming paradigms:
## Imperative programming (C or Java)

Imperative programming is about
* Modifying mutable variables
* Using assignments
* and control structures such as if-then-else, loops, break, continue, return

The most common informal wat to understand imperative programs is as instructure sequence for a Von Neumann computer.

There is a strong correspondence between

```
Mutuable variables     ~  memory cells
Variable dereferences  ~  load instructions
Variable assignments   ~  store instructions
Control structures     ~  jumps
```


Interaction between processor and memory is managed by bus, which reads both instructions and data. Width of this bus is about a ```machine word``` which is either 32bits or 64bits. This immediately brings to the question of scaling beyond word, as raised by John Backus (inventor of fortran). We needed other techniques for defining high-level abstractions such as collections, polynomials, geometric shapes, strings, documents, etc. - and hence ideally develop theories about them.

In mathematics, a theory consists of
* One or more data types
* Operations on these types
* Laws that describes the relationships between values and operations.

Normally, a theory does not describe mutations!!!

This means, if we want to implement high-level concepts following their mathematical theories, there is no place for mutation. Theories do not admit it and mutations can destroy useful laws in the theories. Therefore, let's
* concentrate on defining theories for operators expressed as functions
* avoid mutations
* have powerful ways to abstract and compose functions.

This leads to the next programming paradigm.

## Functional programming (Lisp or Scala or Clojure or Haskell)

In a *restricted* sense, functional programming (FP) means programming without mutable variables, assignments, loops and other imperative control structures.

In a *wilder* sense, FP means focusing on functions.

In particular, functions can be values that are produced, consumed, and composed. All this becomes easier in a functional language. In the restricted sense, a functual programming language is one which does not have an immutable variables assignments or imperative control structure and in the wider sense the functual programming language is one with that enables the construction of elegant programs that focus on the functions. In particular **functions in a functional programming language is first class citizens**. It means that essentially you can do with a function that you could do with any other piece of data so.
* Like you can define a string anywhere you should be able to define a function anywhere including inside other functions. 
* Like any other value, you should be able to pass a function as a parameter to another function and return it as a result from a function
* And as for other values, there will be a set of operators to compose functions into greater functions.  

Lets see a bit of history of FP languages.

|Year|FP Languages|
|----|------------|
|1959|Lisp|
|1975-77|ML, FP, Scheme|
|1978|SmallTalk|
|1986|Standard ML|
|1990|Haskell, Erlang|
|1999|XSLT|
|2000|OCaml|
|2003|Scala, XQuery|
|2005|F#|
|2007|Clojure|


Parallel Programming   -  Execute programs faster on parallel hardware
Concurrent Programming -  Manage concurrent execution threads explicitly

### The root of the problem
* Non-deterministic caused by concurrent threads accessing shared mutable stable
* It helps to ensulated state in actors or transactions, but the fundamental problem stays the same.
* So, non-determinism = parallel processing + Mutable state
* To get deterministic processing, avoid the mutable state.
* Avoiding mutable state means programming functionally.

In Imperative program, you think time wise. First need to do this, then this and then that. In functional program, you think space wise. I will first construct this and then this and using these, that.

![](Space-Time-theory.png)

Look at all the entanglement in time based model (imperative).

### Scala is a Unifier
```

        Agile, with lighweight syntax     
                       |
                       |    Parallel
                       v
Object-Oriented ---> Scala <--- Functional
                       ^
        Sequential     |
                       |
     Safe and performant, with strong static typing
```

#### Parallelism:
* Collections - Fundamental Collections, Parallel Collections and Distributed Collection

* Parallel DSLs

```scala
val people : Array[Person]
val (minor, adults) = people partition (_.age < 18)

// Going parallel
val people : Array[Person]
val (minor, adults) = people.par partition (_.age < 18)
```

#### Concurrency (using Akka):
* Actors
  * Simple message-oriented programming model for multi-threading
  * Serializes access to shared resources using queues and function passing
  * Easier for programmers to create reliable concurrent processing
  * Many sources of contention, races, locking and dead-locks removed
  * Works similarly even if communication is over the internet across clusters, etc. 

```scala
class Person (val name: String, val age: Int)

actor {
    receive {
        case people: Set[Person] => 
            val (minor, adults) = people partition (_.age < 18)
            Facebook ! minors // Sends minors to Facebooks
            LinkedIn ! adults // Sends adults to LinkedIn
    }
}
```

* Software transactional memory
* Futures


## Logic programming

Orthogonal to it: Object-oriented programming.

## Further study

* [Structure and Interpretation of Computer Programs, by Harold Ibelson and Gerald Susman](https://mitpress.mit.edu/sites/default/files/sicp/full-text/book/book.html)
* [Programming in Scala by Martin Odersky, Lex Spoon, and Bill Venners]()
* [Scala for the impatient by Cay Horstmann]()
* [Scala in Depth by Josureth]() - Advanced

* https://www.lightbend.com/ebooks

* Scala parallel DSL
![](DSL-Parallel.png)

10.5%