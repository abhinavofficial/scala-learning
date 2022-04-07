# Set up

```shell
$ curl -fL https://github.com/coursier/launchers/raw/master/cs-x86_64-pc-linux.gz | gzip -d > cs
$ chmod +x cs
$ ./cs setup
Checking if a JVM is installed
Found a JVM installed under /usr/lib/jvm/java-11-openjdk-amd64.

Checking if ~/.local/share/coursier/bin is in PATH
Should we add ~/.local/share/coursier/bin to your PATH via ~/.profile? [Y/n] Y

Checking if the standard Scala applications are installed
Installed ammonite
Installed cs
Installed coursier
Installed scala
Installed scalac
Installed sbt
Installed sbtn
Installed scalafmt
```