# Scala Syntax Summary

We have seen language elements to express types, expressions and definitions.

Below, we give their context-free syntax in Extended Backus-Naur form (EBNF), where
* | denotes an alternative,
* [...] an option (0 or 1)
* {...} a repetition (0 or more)

## Types
```
Type          = SimpleType | FunctionType
FunctionType  = SimpleType '=>' Type | '('[Types]')' '=>' Type
SimpleType    = Identifier
Types         = Type {',' Type}
```
A type can be
* A _numeric_ type: Int, Double (and Byte, Short, Char, Long, Float)
* A _Boolean_ type with values true and false
* A _String_ type
* A _function_ type like Int => Int, (Int, Int) => Int

## Expressions

```
Expr           = InfixExpr | FunctionExpr | if '(' Expr ')' Expr else Expr
InfixExpr      = PrefixExpr | InfixExpr Operator InfixExpr
Operator       = Identfier
PrefixExpr     = ['+' | '-' | '!' | '~'] SimpleExpr
SimpleExpr     = Identifier | literal | SimpleExpr '.' Identifier | Block
FunctionExpr   = Bindings '=>' Expr
Bindings       = Identifier [':' SimpleType] | '(' [Binding {',' Binding}] ')'
Binding        = Identifier [':' Type]
Block          = '{' {Def ';'} Expr '}' 
```

An expression can be:
* An _Identifier_ such as x, isGoodEnough, etc.
* A _literal_ such as 0, 1.0, "abc", etc.
* A _function application_ such as sqrt(x), etc.
* An _operator application_ such as -x, y + x. etc.
* A _selection_ such as math.abs, etc.
* A _conditional expression_ such as if (x < 0) -x else x, etc.
* A _block_ such as { val x = math.abs(y); x * 2 }, etc.
* An _anonymous function_ such as x => x + 1, etc.

## Definitions

```
Def         = FunDef | ValDef
FunDef      = def Identifier {'(' [Parameters] ')'} [':' Type] '=' Expr
ValDef      = val Identifier [':' Type] '=' Expr
Parameter   = Identifier ':' [ '=>' ] Type
Parameters  = Parameter {',' Parameter }
```

A definition can be:
* A _function definition_ such as def square(x: Int) = x * x
* A _value definition_ such as val y = square(2)

A parameter can be:
* A _call-by-value_ parameter such as (x: Int)
* A _call-by-name_ parameter such as (y: => Double)