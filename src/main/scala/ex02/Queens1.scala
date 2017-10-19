package ex02

class Queens1(n: Int) extends Queens(n) {
  type Ret = List[List[(Int, Int)]]
  var level = 0
  val indicator = "- "

  def trace(fname: String, arg: Any*)(body: => Ret): Ret = {
    val args = arg.mkString(",")
    println((indicator * level) + level + ":" + fname + " (" + args + ")")

    level += 1
    val ret = body
    level -= 1
    println((indicator * level) + level + ":" + fname + " = List(")
    ret.foreach(x => println(indicator * (level + 1) + x))
    println(indicator * level + ")")
    ret

  }

  override def queen(r: Int): List[List[(Int, Int)]] = {
    trace("queen", r) {
      if (r == 0) List(Nil)
      else for (p<-queen(r-1); c<-1 to n if check(r, c, p)) yield (r, c)::p
    }
  }

}

object Queens1App extends App {
  new Queens1(4).start()
}
