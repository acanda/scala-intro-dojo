package dojo

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class EulerProblem1Suite extends FunSuite{

  test("The sum of all the multiples of 3 or 5 below 10 should be 23") {
    assert(EulerProblem1.sumOfMultiplesBelow(10) === 3 + 5 + 6 + 9)
  }

}
