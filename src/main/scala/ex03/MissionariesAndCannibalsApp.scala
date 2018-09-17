package ex03

class MissionariesAndCannibals {
  type State = List[List[Char]]
  type Op = List[Char]
  var opAll: List[Op] = Nil

  def move(from: List[Char], to: List[Char], op: Op): State = {
    val from1 = from.diff(op)
    if (from1.length == from.length - op.length)
      List(from1, op:::to)
    else List(from, to)
  }

  def solve(st: State, ops: List[Op], boat: Int, history: List[State]): List[State] = {
    ops match {
      case Nil => Nil
      case op::opTail =>
        val (dir, stNew) = st match {
          case List(l, r) =>
            if (boat == -1) (List('→'), move(l, r, op).map(_.sorted))
            else (List('←'), move(r, l, op).reverse.map(_.sorted))
        }

        if (goal(stNew)) (op::dir::stNew)::history
        else if (stNew==st|| !safe(stNew) || history.exists(_.tail==dir::stNew))
          solve(st, opTail, boat, history)
        else {
          val ret = solve(stNew, opAll, -boat, (op::dir::stNew)::history)
          if (ret != Nil) ret
          else solve(st, opTail, boat, history)
        }
    }
  }

  def goal(st: State): Boolean = {
    st.head == Nil
  }

  def safe(st: State): Boolean = {
    st.forall(x => x.count(_ == 'S') == 0 || x.count(_ == 'S') >= x.count(_ == 'M'))
  }

  def start(): Unit = {
    opAll = List(List('S', 'S'), List('S', 'M'), List('M', 'M'), List('S'), List('M')).map(_.sorted)
    val st = List(List('S', 'S', 'S', 'M', 'M', 'M'), List()).map(_.sorted)
    val history = List(Nil::List('←')::st)
    val solution = solve(st, opAll, -1, history)
    println("Mover\t\tDirection\tResult (Left)\t\tResult (Right)")
    solution.reverse.foreach(x =>
      println(x.map(_.mkString).mkString("\t\t\t\t"))
    )
  }
}

object MissionariesAndCannibalsApp extends App {
  new MissionariesAndCannibals().start()
}
