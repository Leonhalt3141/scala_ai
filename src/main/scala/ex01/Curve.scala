package ex01

import javafx.application._
import javafx.stage._
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.scene.canvas._
import javafx.scene.shape._
import javafx.scene.text._
import javafx.scene.effect._
import javafx.scene.control._
import javafx.scene.input.MouseEvent
import javafx.geometry._
import javafx.event.EventHandler


object CurveMain {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[CurveApp], args: _*)
  }
}

class CurveApp extends Application {
  val canvas = new Canvas(600, 600)
  val g: GraphicsContext = canvas.getGraphicsContext2D

  override def start(stage: Stage): Unit = {
    val pane = new StackPane
    pane.getChildren.add(canvas)
    stage.setScene(new Scene(pane))
    stage.show()
  }

  def draw(): Unit = {
    val c = new Curve(g)
    c.move(50, 300)
    c.draw(500, 0)
  }

  draw()

}

class Curve(val g: GraphicsContext) {

  var lastX, lastY = 0.0

  def move(x: Double, y: Double): Unit = {
    lastX = x
    lastY = y
  }

  def forward(len: Double, angle: Double): Unit = {
    val x = lastX + len * math.cos(angle)
    val y = lastY + len * math.sin(angle)
    g.strokeLine(lastX, lastY, x, y)
    move(x, y)
  }

  def draw(len: Double, angle: Double): Unit = {
    forward(len, angle)
  }

}
