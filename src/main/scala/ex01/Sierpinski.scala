package ex01

import javafx.application.Application
import javafx.scene.canvas._


object  SierpinskiMain {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[SierpinskiApp], args: _*)
  }
}

class SierpinskiApp extends CurveApp {
  override def draw(): Unit = {
    val c = new Sierpinski(g)
    c.draw(6, 300, 300, 150)
  }
}

class Sierpinski(g: GraphicsContext) extends Curve(g) {

  def draw(n: Int, len: Double, x: Double, y: Double): Unit = {
    val l = len / 2
    val x1 = x -  l
    val x2 = x + l
    val y1 = y + l * math.sqrt(3)

    if (n == 1) {
      g.strokeLine(x, y, x1, y1)
      g.strokeLine(x1, y1, x2, y1)
      g.strokeLine(x2, y1, x, y)
    } else {
      val l2 = l / 2

      draw(n - 1, l, x, y)
      draw(n - 1, l, x - l2, y + l2 * math.sqrt(3))
      draw(n - 1, l, x + l2, y + l2 * math.sqrt(3))
    }
  }

}
