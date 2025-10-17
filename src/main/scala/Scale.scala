package edu.luc.cs.laufer.cs371.shapes

import Shape.*
import com.typesafe.scalalogging.Logger

object Scale:
  private val logger = Logger("Scale")

  def apply(s: Shape, factor: Double): Shape =
    logger.debug(s"Scaling shape: $s by factor $factor")
    ShapeValidator.validate(s)
    s match
      case shape: Rectangle =>
        val scaled = Rectangle((shape.width * factor).toInt, (shape.height * factor).toInt)
        logger.debug(s"Rectangle scaled to: $scaled")
        scaled
      case shape: Ellipse =>
        val scaled = Ellipse((shape.x * factor).toInt, (shape.y * factor).toInt)
        logger.debug(s"Ellipse scaled to: $scaled")
        scaled
      case shape: Location =>
        val scaled = Location(
          (shape.x * factor).toInt,
          (shape.y * factor).toInt,
          apply(shape.shape, factor) // recursive call
        )
        logger.debug(s"Location scaled to: $scaled")
        scaled
      case shape: Group =>
        val scaled = Group(shape.shapes.map(apply(_, factor)) *)
        logger.debug(s"Group scaled to: $scaled")
        scaled
