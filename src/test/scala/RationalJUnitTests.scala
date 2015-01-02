package scalatddpackt

import org.junit._
import org.junit.Test
import org.junit.Assert._

import rational._


class RationalJUnitTests {

  // RationalJUnitTests.gcd
  @Test
  def testMathUtilities(): Unit = {
    assertEquals(3, gcd(3, 6))
    assertEquals(3, gcd(-3, 6))
    assertEquals(-3, gcd(-3, -6))
    assertEquals(5, gcd(0, 5))
    assertEquals(5, gcd(5, 0))
    assertEquals(1, gcd(1, 5))
    assertEquals(1, gcd(5, 1))
  }

  // RationalJUnitTests.Initialization
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

    try {
      val r4 = new Rational(1, 0)
      assertEquals(0, r4.denominator)
    } catch {
      case ArithmeticException => assertTrue(true)
        _ => ??? // won't happen
    }

  }

  // RationalJUnitTests.Arithmetic
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

  // RationalJUnitTests.Comparisons
  @Test
  def TestComparisons() {
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    val r3 = new Rational(1, 2)
    assert(r1 < r2)
    assert(r1 <= r2)
    assert(r2 > r1)
    assert(r2 >= r1)
    assert(r2 == r3)
    assert(r2 <= r3)
    assert(r3 >= r2)
  }

  // RationalJUnitTests.Done
}
