package edu.luc.cs.laufer.cs371.shapes

import org.scalatest.funsuite.AnyFunSuite
import Shape.*
import TestFixtures.*

class TestScale extends AnyFunSuite:

  def testScale(description: String, s: Shape, factor: Int, expected: Shape): Unit =
    test(description):
      assert(Scale(s, factor) == expected)

  testScale("simple ellipse x2", simpleEllipse, 2, Ellipse(100, 60))
  testScale("simple rectangle x3", simpleRectangle, 3, Rectangle(240, 360))
  testScale("simple location x2", simpleLocation, 2, Location(140, 60, Rectangle(160, 240)))
  testScale("basic group x2", basicGroup, 2, Group(Ellipse(100, 60), Rectangle(40, 80)))
  testScale("simple location x3", simpleLocation, 3, Location(210, 90, Rectangle(240, 360)))
  testScale("simple group x4", simpleGroup, 4, Group(Location(800, 400, Ellipse(200, 120)),
    Location(1600, 1200, Rectangle(400, 200))))


end TestScale