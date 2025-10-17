package edu.luc.cs.laufer.cs371.shapes

import Shape.*
import com.typesafe.scalalogging.Logger

object Height:
  private val logger = Logger("Height")

  def apply(s: Shape): Int =
    logger.debug(s"Computing height of shape: $s")
    ShapeValidator.validate(s)
    s match
      case shape: Rectangle =>
        logger.debug("Rectangle height is 1")
        1
      case shape: Ellipse =>
        logger.debug("Ellipse height is 1")
        1
      case shape: Location =>
        val innerHeight = apply(shape.shape)
        val total = 1 + innerHeight
        logger.debug(s"Location height: $total")
        total
      case shape: Group =>
        val childHeights = shape.shapes.map(apply)
        val total = 1 + childHeights.max
        logger.debug(s"Group height: $total")
        total
