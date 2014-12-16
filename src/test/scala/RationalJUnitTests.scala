package scalatddpackt;

import org.junit._
import org.junit.Test
import org.junit.Assert._


class RationalJUnitTests {

  @Test
  def testMathUtilities(): Unit = {
    assertEquals(3, MathUtility.gcd(3, 6))
    assertEquals(3, MathUtility.gcd(-3, 6))
    assertEquals(-3, MathUtility.gcd(-3, -6))
    assertEquals(5, MathUtility.gcd(0, 5));
    assertEquals(5, MathUtility.gcd(5, 0));
    assertEquals(1, MathUtility.gcd(1, 5));
    assertEquals(1, MathUtility.gcd(5, 1));
  }

  @Test
  def testInitialization(): Unit = {
    val r1 = new Rational(2, 4)
    assertEquals(1, r1.numerator)
    assertEquals(2, r1.denominator)

    val r2 = new Rational(-3, 6)
    assertEquals(-1, r2.numerator)
    assertEquals(2, r2.denominator)

    val r3 = new Rational(-3, -6)
    assertEquals(1, r3.numerator)
    assertEquals(2, r3.denominator)
  }

  @Test
  def TestArithmetic(): Unit = {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)

    assert(r1 + r2 == new Rational(36, 64))
    assert(r1 - r2 == new Rational(58, 64))
    assert(r1 * r2 == new Rational(47 * -11, 64 * 64))
    assert(r1 / r2 == new Rational(47, -11))
    assert(r2.reciprocal() == new Rational(64, -11))
    assert(r2.negate() == new Rational(11, 64))
  }
}
