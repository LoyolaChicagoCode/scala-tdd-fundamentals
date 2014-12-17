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

  "Arithmetic" should "perform addition" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1 + r2 should === (new Rational(36, 64))
    r1 + r2 should be (new Rational(36, 64))
  }

  it should "perform subtraction" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1 - r2 should be (new Rational(58, 64))
  }

  it should "perform multiplication" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1 * r2 should be (new Rational(47 * -11, 64 * 64))
  }

  it should "perform division" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1 / r2 should be (new Rational(47, -11))
  }

  it should "perform the reciprocal" in {
    val r1 = new Rational(47, 64)
    r1.reciprocal() should be (new Rational(64, 47))
  }

  it should "perform negation" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1.negate() should be (new Rational(-47, 64))
  }

  "Comparisons" should "perform ==" in {
    val r1 = new Rational(2, 4)
    val r2 = new Rational(1, 2)
    assert(r1 == r2)
    info("numeric equality (==) works")
    assert(r1.equals(r2))
    info("object equality works, e.g. equals()")
    assert(r1.hashCode() == r2.hashCode())
    info("hashcode() works")

  }
  it should "perform <" in {
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    assert(r1 < r2)
    info("operator < works")
  }

  it should "perform >" in {
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    assert(r2 > r1)
    info("operator > works")
  }

  it should "perform <= for something < and for something =" in {
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    val r3 = new Rational(1, 2)
    assert(r1 <= r2)
    info("operator <= works for < case")
    assert(r2 <= r3)
    info("operator <= works for == case")
  }

  it should "perform >= for something > and for something =" in {
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    val r3 = new Rational(1, 2)
    assert(r2 >= r1)
    info("operator >= works for > case")
    assert(r2 >= r3)
    info("operator >= works for == case")
  }

  // End-Tests
}