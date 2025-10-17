package edu.luc.cs.laufer.cs371.shapes

import Shape.*
import com.typesafe.scalalogging.Logger

object boundingBox:
  private val logger = Logger("boundingBox")

  def apply(s: Shape): Location =
    ShapeValidator.validate(s)
    s match
      case shape: Rectangle =>
        val loc: Location = Location(0, 0, Rectangle(shape.width, shape.height))
        logger.debug(s"Rectangle bounding box: $loc")
        loc
      case shape: Ellipse =>
        val loc: Location = Location(-shape.x, -shape.y, Rectangle(shape.x * 2, shape.y * 2))
        logger.debug(s"Ellipse bounding box: $loc")
        loc
      case shape: Location =>
        val innerBox: Location = apply(shape.shape)  // call once, typed as Location
        val loc: Location = Location(
          shape.x + innerBox.x,
          shape.y + innerBox.y,
          innerBox.shape
        )
        logger.debug(s"Location offset applied: $loc")
        loc
      case shape: Group =>
        val boxes = shape.shapes.map(apply) // Seq[Location]
        val first = boxes.head
        val firstRect = first.shape match
          case r: Rectangle => r
          case _ => throw new RuntimeException("Bounding box must return a Rectangle")
        val init = (first.x, first.y, first.x + firstRect.width, first.y + firstRect.height)
        val (minX, minY, maxX, maxY) = boxes.tail.foldLeft(init) { case ((minX, minY, maxX, maxY), loc: Location) =>
          val Rectangle(w, h) = loc.shape match
            case r: Rectangle => r
            case _ => throw new RuntimeException("Bounding box must return a Rectangle")
          val right = loc.x + w
          val bottom = loc.y + h
          (
            math.min(minX, loc.x),
            math.min(minY, loc.y),
            math.max(maxX, right),
            math.max(maxY, bottom)
          )
        }
        val loc: Location = Location(minX, minY, Rectangle(maxX - minX, maxY - minY))
        logger.debug(s"Group bounding box: $loc")
        loc

