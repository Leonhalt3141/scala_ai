package ex01

import javafx.application. Application
import javafx.scene.canvas._

object DragonMain {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[DragonApp], args: _*)
  }
}

class DragonApp extends CurveApp {
  override def draw(): Unit = {
    val c = new Dragon(g)
    c.move(150, 300)
    c.draw(15, 300, 0, 1)
  }
}

class Dragon(g: GraphicsContext) extends Curve(g) {

  def draw(n: Int, len: Double, angle: Double, sw: Int): Unit = {
    if (n == 1) {
      forward(len, angle)
    } else {
      val l = len / (2 / math.sqrt(2.0))
      val a = math.Pi * 0.25 * sw
      draw(n - 1, l, angle - a, 1)
      draw(n - 1, l, angle + a, -1)
    }
  }

}
