package ex01

import javafx.application.Application
import javafx.scene.canvas._

object TreeMain {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[TreeApp], args: _*)
  }
}

class TreeApp extends CurveApp {
  override def draw(): Unit ={
    val c = new Tree(g)
    c.move(300, 600)
    c.draw(7, 450, math.Pi * -0.5)
  }
}

class Tree (g: GraphicsContext) extends  Curve(g) {

  def save() = {
    (lastX, lastY)
  }

  def restore(x: Double, y: Double): Unit = {
    lastX = x
    lastY = y
  }

  def draw(n: Int, len: Double, angle: Double, sw: Int = 1): Unit = {
    val (x, y) = save()
      if (n == 1) {
        forward(len, angle)
      } else {
        val l = len / (2 / math.sqrt(2.0))
        val a = math.Pi * 0.15 * sw
        forward(l * 0.33, angle)
        draw(n - 1, l * 0.8, angle - a)

        forward(l * 0.33, angle)
        draw(n - 1, l * 0.7, angle + a * 1.5, -1)

        forward(l * 0.33, angle)
        draw(n - 1, l * 0.6, angle)
      }
    restore(x, y)
  }

}
