package dojo

import org.scalatest.FunSuite

class EulerProblem1Suite extends FunSuite{

  test("The sum of all the multiples of 3 or 5 below 10 should be 23") {
    assert(EulerProblem1.sumOfMultiplesBelow(10) === 3 + 5 + 6 + 9)
  }

}