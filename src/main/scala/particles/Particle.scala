package particles

import core.Direction.*
import core.Position.*
import core.Types.Coordinates
import core.Direction
import scalafx.beans.property.ObjectProperty
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

import java.lang.Math.*
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class Particle(x: Int, y: Int, r: Int, direction: Direction, color: Color) {

  val diameter: Int = r * 2

  def draw: Circle =
    new Circle {
      centerX = x
      centerY = y
      radius = r
      fill = color
    }

  def move(particles: Map[Coordinates, List[Particle]], boardWidth: Int, boardHeight: Int): Particle = {
    val (nextX: Int, nextY: Int)     = nextPosition(x, y, direction, boardWidth, boardHeight)
    val neighbours: Seq[Coordinates] = neighboursOf(x, y, diameter)
    val collision: Boolean           = neighbours.exists(particles.contains)
    val newDirection: Direction      = if (collision) Direction.random else direction

    copy(x = nextX, y = nextY, direction = newDirection)
  }

}
