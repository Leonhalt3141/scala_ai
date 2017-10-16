package ex01

/** Class of Tower of Hanoi
  *  @param n Number of disk
  */
class TowersOfHanoi(val n: Int) {

  /** Move disks of Tower of Hanoi
    *
    * @param m Number of moving target
    * @param from The source of movement
    * @param to The target of movement
    * @param work The working place of movement
    * @param s Array of disks
    */
  def hanoi(m: Int, from: Int, to: Int, work: Int, s: Array[List[Int]]): Unit = {

    if (m == 1) {
      s(to) = s(from).head::s(to)
      s(from) = s(from).tail
      println(disp(s.toList.map(_.reverse)))
    } else {
      hanoi(m-1, from, work, to, s)
      hanoi(1, from, to, work, s)
      hanoi(m-1, work, to, from, s)
    }
  }

  def disp(a: List[List[Int]]): String = {

    if (a == List(Nil, Nil, Nil)) {
      "--" * (n * 2 * 3) + "\n"
    } else {
      disp(a.map(x => if (x == Nil) Nil else x.tail)) + "\n" + a.map(x => if (x == Nil) 0 else x.head).map(
        x => " " * (n - x) + "OO" * x + " " * (n - x) + " ").mkString
    }
  }

  def start(): Unit = {
    val s = Array(Range(1, n + 1).toList, Nil, Nil)
    hanoi(n, 0, 2, 1, s)
  }
}


object TowersOfHanoiApp extends App {
  new TowersOfHanoi(5).start()
}
