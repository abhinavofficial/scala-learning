# sbt
* Similar to Maven and Ivy
* Used by scala, Akka, PlayFramework, Slick and more
* Support by Lightbend and the Community

## Features
* zero to minimal configuration for a project
* Extensive Testing Support with framework like ScalaTest, ScalaCheck, Spec and Junit
* Incremental Compilation
* Modularization using subprojects
* Parallel Task Execution including parallel test execution
* Library Management support using in-line declarations, Ivy, or Maven configurations, or even manual configuration of your dependencies.

## Install 

## Running sbt commands

### via sbt-shell
* If a project has multiple main classes, sbt would ask which main class to run.
* To run multiple command, you can run using ```;<command1> ;<command2>```
* History Commands
```shell
#History commands:
   !!       # Execute the last command again
   !:       # Show all previous commands
   !:n      # Show the last n commands
   !n       # Execute the command with index n, as shown by the !: command
   !-n      # Execute the nth command before this one
   !string  # Execute the most recent command starting with 'string'
   !?string # Execute the most recent command containing 'string'
```
* We can access scala console by typing ```console``` - this provides a console with everything your project contains.
* To exit sbt server, use ```exit```

### via terminal
You can do almost everything but to the argument to sbt. Example -
```shell
$ sbt clean compile run
or
$ sbt "run A B C" # A B C are arguments
```
The only catch in this case is that sbt requires JVM and JIT spin up every single time, which is an overhead. It also logs this message to inform the user.

## Key terms for project organization
* baseDirectory
* sourceDirectories
* resourceDirectories
* test:sourceDirectories
* test:resourceDirectories
* build.sbt - definition file
* project/ - build support files
* target

## Project Configuration Setting
Project is configured using key value pairs. The syntax is divided into three parts:

### Key
Three kinds of keys:

| Key           | Separator                      | Example        |
|---------------|--------------------------------|----------------|
| SettingKey[T] | Computed Once per project load | Name           |
| TaskKey[T]    | Recomputed every time called   | package, clean |
| InputKey[T]   | Accepts command line arguments | run            |

To know what kind of key any attribute is, we use ```inspect <Key Name>```

sbt provides a set of built-in keys for developers to use. This can be imported using ```import sbt.Keys._```. These are imported implicitly in build.sbt. 

You can check the [sbt website for more details](https://www.scala-sbt.org/1.x/api/sbt/Keys$.html) on keys. There are other two ways to find this - one is using ```settings -V``` in sbt on terminal, and other is ```tasks -V``` to get the list of all the task available in the project that we can run.

### Body or setting body
### Separator which separates the key from the Body.

| Key  | Separator | Body           | Comment            |
|------|-----------|----------------|--------------------|
| Name | :=        | scala-learning | Setting Expression |


## Custom Configuration Setting
To define your custom setting, we first need to define a custom key and then write setting expression which is assigning the body. Example,
```shell
# in build.sbt

lazy val emotion = settingKey[String] ("How are you feeling")
emotion := "Fantastic"

val randomInt = taskKey[Int]("Give me random number")
randomInt := scala.util.Random.nextInt
```

A setting can depend on one or more settings. A task can depend on one or more tasks and settings. However, a setting cannot depend on a task. Example,
```shell
# in build.sbt

lazy val emotion = settingKey[String] ("How are you feeling")
emotion := "Fantastic"

lazy val status = settingKey[String] ("What is your current status?")
status :=
  val e = emotion.value
  s"Grateful and $e"
```
All the dependent settings are evaluated before and in no particular order.