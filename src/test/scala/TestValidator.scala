package edu.luc.cs.laufer.cs371.shapes

import org.scalatest.funsuite.AnyFunSuite
import Shape.*

class TestValidator extends AnyFunSuite:

  test("valid rectangle passes validation") {
    val rect = Rectangle(10, 20)
    ShapeValidator.validate(rect) // should not throw
  }

  test("invalid rectangle throws exception") {
    val rect = Rectangle(0, 20)
    assertThrows[IllegalArgumentException] {
      ShapeValidator.validate(rect)
    }
  }

  test("valid ellipse passes validation") {
    val ell = Ellipse(5, 15)
    ShapeValidator.validate(ell) // should not throw
  }

  test("invalid ellipse throws exception") {
    val ell = Ellipse(0, 10)
    assertThrows[IllegalArgumentException] {
      ShapeValidator.validate(ell)
    }
  }

  test("valid group passes validation") {
    val grp = Group(Rectangle(1,1), Ellipse(2,2))
    ShapeValidator.validate(grp) // should not throw
  }

  test("empty group throws exception") {
    val grp = Group()
    assertThrows[IllegalArgumentException] {
      ShapeValidator.validate(grp)
    }
  }

  test("nested invalid shape in location throws exception") {
    val loc = Location(10, 10, Rectangle(0, 5))
    assertThrows[IllegalArgumentException] {
      ShapeValidator.validate(loc)
    }
  }

  test("nested group with invalid shape throws exception") {
    val grp = Group(Rectangle(10, 10), Location(5, 5, Ellipse(0, 10)))
    assertThrows[IllegalArgumentException] {
      ShapeValidator.validate(grp)
    }
  }

end TestValidator
