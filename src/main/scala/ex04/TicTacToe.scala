package ex04

class TicTacToe {
  val bd: Array[Array[Char]] = Array.fill(3)(Array.fill(3)(' '))
  val pat: List[List[(Int, Int)]] = { val a = List(0, 1, 2)
    a.map(x => (x, x)) :: a.map(x => (2-x, x)) ::
    a.map(r => a.map(c => (r, c))) :::
    a.map(c => a.map(r => (r, c)))
  }

  var playing = true
  var winner = ' '

  def goal(p: Char): Boolean = {
    pat.exists(t => t.forall(a => bd(a._1)(a._2) == p))
  }

  def fin(): Boolean = {
    for (r <- 0 to 2; c <- 0 to 2 if bd(r)(c) == ' ') return false
    true
  }

  def computer(p: Char): Unit = {
    val free = for(r<-0 to 2; c<-0 to 2 if bd(r)(c) == ' ')
      yield (r, c)
    val n = free.length
    val (r, c) = free(scala.util.Random.nextInt(n))
    bd(r)(c) = p
    println("Computer:" + p + " = " + (r, c))
  }
}

object TicTacToe {


}
