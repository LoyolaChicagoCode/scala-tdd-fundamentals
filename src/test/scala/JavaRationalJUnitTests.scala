import org.junit.Assert._
import org.junit.Test

import scalatddpackt.JRational

/**
 * Created by gkt on 1/5/15.
 */


class JavaRationalJUnitTests {


  // RationalJUnitTests.gcd
  @Test
  def testMathUtilities(): Unit = {
    import JRational.gcd

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
    val r1 = new JRational(2, 4)
    assertEquals(1, r1.getN)
    assertEquals(2, r1.getD)

    val r2 = new JRational(-3, 6)
    assertEquals(-1, r2.getN)
    assertEquals(2, r2.getD)

    val r3 = new JRational(-3, -6)
    assertEquals(1, r3.getN)
    assertEquals(2, r3.getD)
  }

  // RationalJUnitTests.Initialization
  @Test
  def testInitialization2(): Unit = {
    val r1 = new JRational(2, 4)
    assertEquals(1, r1.getN)
    assertEquals(2, r1.getD)

    val r2 = new JRational(-3, 6)
    assertEquals(-1, r2.getN)
    assertEquals(2, r2.getD)

    val r3 = new JRational(-3, -6)
    assertEquals(1, r3.getN)
    assertEquals(2, r3.getD)

  }

  @Test(expected = classOf[ArithmeticException])
  def testZeroDenominator(): Unit = {
    val r4 = new JRational(1, 0)
    fail("Zero demoninator was accepted " + r4.getD)
  }
  // RationalJUnitTests.Arithmetic
  @Test
  def TestArithmetic(): Unit = {
    val r1 = new JRational(47, 64)
    val r2 = new JRational(-11, 64)

    assert(r1.add(r2) == new JRational(36, 64))
    assert(r1.subtract(r2) == new JRational(58, 64))
    assert(r1.multiply(r2) == new JRational(47 * -11, 64 * 64))
    assert(r1.divide(r2) == new JRational(47, -11))
    assert(r2.reciprocal() == new JRational(64, -11))
    assert(r2.negate() == new JRational(11, 64))
  }

  // RationalJUnitTests.Comparisons
  @Test
  def TestComparisons() {
    val r1 = new JRational(-3, 6)
    val r2 = new JRational(2, 4)
    val r3 = new JRational(1, 2)
    assert(r1.compareTo(r2) < 0)
    assert(r2.compareTo(r1) > 0)
    assert(r2.compareTo(r3) == 0)
  }

  // RationalJUnitTests.Done

}
