package foo

object Example{

  val usage = """
  Usage: patternmatching [--arg1 num] [--arg2 num] filename
"""
  def main(args: Array[String]) {
    if (args.length == 0) println(usage)

    def nextArg(map: Map[String, Any], list: List[String]): Map[String, Any] = {
      list match {
        case Nil => map
        case "--arg1" :: value :: tail =>
          nextArg(map ++ Map("arg1" -> value.toInt), tail)
        case "--arg2" :: value :: tail =>
          nextArg(map ++ Map("arg2" -> value.toInt), tail)
        case string :: Nil =>
          nextArg(map ++ Map("filename" -> string), list.tail)
        case unknown :: _ =>
          println("Unknown option " + unknown)
          exit(1)
      }
    }
    val options = nextArg(Map(), args.toList)
    println(options)

  def flexibleFizzBuzz(callback: String => Unit) = {
    for (i <- Range.inclusive(1, 100)) {
      callback( match (i%3, i%5) {
        case (0, 0) => "FizzBuzz"
        case (0, _) => "Fizz"
        case (_, 0) => "Buzz"
        case (_, _) => i.toString
      })
    }
  }

  class Msg(val id: Int, val parent: Option[Int], val txt: String)

  def printMessages(messages: Array[Msg]): Unit = {
    def printFrag(parent: Option[Int], indent: String): Unit = {
      for (msg <- messages if msg.parent == parent) {
        println(s"$indent#${msg.id} ${msg.txt}")
        printFrag(Some(msg.id), indent + "  ")
      }
    }
    printFrag(None, "")
  }


}