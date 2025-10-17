package edu.luc.cs.laufer.cs371.shapes

import Shape.*
import com.typesafe.scalalogging.Logger

object ShapeValidator:
  private val logger = Logger("ShapeValidator")

  def validate(s: Shape): Unit =
    s match
      case shape: Rectangle =>
        logger.debug(s"Validating Rectangle: $shape")
        require(shape.width > 0 && shape.height > 0, "Rectangle dimensions must be positive")
      case shape: Ellipse =>
        logger.debug(s"Validating Ellipse: $shape")
        require(shape.x > 0 && shape.y > 0, "Ellipse dimensions must be positive")
      case shape: Group =>
        logger.debug(s"Validating Group with ${shape.shapes.size} shapes: $shape")
        require(shape.shapes.nonEmpty, "Group must contain at least one shape")
        shape.shapes.foreach(validate)  // recursively validate children
      case shape: Location =>
        logger.debug(s"Validating Location: $shape")
        validate(shape.shape)  // recursively validate contained shape
