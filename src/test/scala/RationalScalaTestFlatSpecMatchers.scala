package scalatddpackt

import org.scalatest._

/**
 * Created by gkt on 12/16/14.
 */
class RationalScalaTestFlatSpecMatchers extends FlatSpec with Matchers {

  "GCD involving 0" should "give y for gcd(0, y)" in {
    MathUtility.gcd(0, 5) should be (5)
  }

  it should "give x for gcd(x, 0)" in {
    MathUtility.gcd(0, 5) should be (5)
  }

  "Initializing" should "reduce 2/4 to 1/2" in {
    val r1 = new Rational(2, 4)
    r1.numerator should be (1)
    r1.denominator should be (2)
  }

  it should "reduce -2/4 to -1/2" in {
    val r1 = new Rational(-2, 4)
    r1.numerator should be (-1)
    r1.denominator should be (2)
  }

  it should "reduce -3/-6 to 1/2" in {
    val r1 = new Rational(-3, -6)
    r1.numerator should be (1)
    r1.denominator should be (2)
  }
}