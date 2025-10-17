package edu.luc.cs.laufer.cs371.shapes

import Shape.*
import com.typesafe.scalalogging.Logger

object Size:
  private val logger = Logger("Size")

  def apply(s: Shape): Int =
    logger.debug(s"Computing size of shape: $s")
    ShapeValidator.validate(s)
    s match
      case shape: Rectangle =>
        logger.debug("Rectangle size is 1")
        1
      case shape: Ellipse =>
        logger.debug("Ellipse size is 1")
        1
      case shape: Location =>
        val innerSize = apply(shape.shape)
        logger.debug(s"Location size: $innerSize")
        innerSize
      case shape: Group =>
        val childSizes = shape.shapes.map(apply)
        val total = childSizes.sum
        logger.debug(s"Group size: $total (sum of children: $childSizes)")
        total
