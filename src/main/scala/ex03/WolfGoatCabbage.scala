package ex03

class WolfGoatCabbage extends MissionariesAndCannibals {
  override def safe(st: State) = {
    !st.exists(x => !x.contains('A') && (x.contains('W') && x.contains('G') ||
      x.contains('G') && x.contains('C')))
  }

  override def start(): Unit = {
    opAll = List(List('A', 'W'), List('A', 'G'), List('A', 'C'), List('A')).map(_.sorted)

    val st = List(List('A', 'W', 'G', 'C').sorted, List())
    val history = List(List()::List('←')::st)
    val solution = solve(st, opAll, -1, history)
    println("Mover\t\tDirection\t\tResult (Left)\tResult (Right)")
    solution.reverse.foreach(x =>
      println(x.map(_.mkString).mkString("\t\t\t\t")))

  }
}

object WolfGoatCabbage extends App {
  new WolfGoatCabbage().start()

}
