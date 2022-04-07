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

```
src/
  main/
    resources/
       <files to include in main jar here>
    scala/
       <main Scala sources>
    java/
       <main Java sources>
  test/
    resources
       <files to include in test jar here>
    scala/
       <test Scala sources>
    java/
       <test Java sources>
Other directories in src/ will be ignored. Additionally, all hidden
directories will be ignored.
```

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

> You might have noticed "ThisBuild" in sbt.build - this refers to the build level settings for our project. It can therefore also imply that sbt first checks if certain settings have project level settings. It found, it picks that. If not, it looks for the build level settings referred here. If not, it would look for global settings.

## Creating project and subproject in sbt

Three methods to issue command specific to the subproject.
* first method is to issue the command specific to subproject, e.g. ```calculators/compile```
* second method is to switch to that subproject and then issue the command as regular.
```shell
sbt:scala-learning> project calculators
# [info] set current project to calculators (in build file:/home/abhinavofficial/Work/scala-learning/)
sbt:scala-learning> ;clean ;compile
```
* finally, we can do the same by defining the relationships between the (sub)projects. The relationship is hierarchical and the parent node is called the aggregator. We can do this by calling aggregate on it and telling what all projects it aggregates on. This way, any command issue to the root is broadcast to all of its aggregated subprojects. This is most commonly used for building and shipping out project.

> If there are multiple subprojects, and you do not create an aggregate relationship, sbt creates one for you.
> To run the main method, you would still need to do it as first method explained above.


## Scope
A scope is a combination of three dimensions; think of them as a cube, X, Y and Z. The axis are known as scope axis. Now, any point inside the cube is a unique combination of these three values. Keys in sbt are similar to a point definition within these dimensions, which are called, **project**, **config** and **task** within sbt. A project in sbt are also known as multi-modules in sbt. A config is the context of the build and the most common values of this are **Compile**, **Test** and **Runtime**. A task is the function in the build definition that executes everything it is called, e.g. run, package, clean, etc.

Please note that all the three are optional with sbt's default behaviour to search **current project** for project, **Compile** and then **Test** (in the same order) for config, tasks only if provided.

We can also explicitly tell which Main class to run using runMain task. Example
```shell
sbt:scala-learning> calculators/runMain CompoundInterest 5000 5 10
```

## Testing
Using ScalaTest
use ```;test``` to run test or ```~test``` watch for changes and run tests automatically.

## Managing Dependencies and Duplications
Keeping dependencies inside build.sbt poses maintenance challenges. sbt does not allow any class or object level definition inside the build.sbt file. This is where project folder comes into picture. Project folder can contain other scala files that sbt scans and uses in order to build the project.

## Packaging the project
We will use sbt native packager.
A plugin extends the pull definition by adding new settings. With plugins, we can utilize the open source work done by others to accomplish common tasks, such as packaging, etc. 

### How to use sbt native packager
* Add plugin to project/plugins.sbt under project folder ```addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.8.1")```
* Specify the type of packaging format required. For creating the jar files, we need to enable the plugin with the JavaAppPackaging format. ```enablePlugins(JavaAppPackaging)``` in sbt.build within each project settings
* We need to run the settings provided by the plugin to create the packaging. ```;clean ;stage```

Another packaging that sbt-native-packager can provide is wrapping your project in docker. Use  ```enablePlugins(DockerPlugin)``` in sbt.built for each project. You also need to add docker commands to define the entry point.

```shell
dockerCommands := dockerCommands.value.filterNot {
      case ExecCmd("ENTRYPOINT", _) => true
      case _ => false
    },
    dockerCommands ++= Seq(ExecCmd("ENTRYPOINT", "/opt/docker/bin/net-worth"))
```

## Continuous Integration and Continuous Deployment
There are many sources available, but we are going to use Travis.ci in our project for Continuous Integration and bintray as artifact distribution platform.

More details on this [here](cicd.md)
