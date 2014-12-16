package scalatddpackt

/*
 * This version of Rational is based on Martin Odersky's Scala by Example.
 * source: http://www.scala-lang.org/docu/files/ScalaByExample.pdf
 *
 * We include a number of additions/improvements based on Harrington and Thiruvathukal's C# Book,
 * which includes an elaboration of other needed methods and full unit tests.
 * source: http://introcs.cs.luc.edu
 */

// begin-Rational-gcd
object MathUtility {
  def gcd(x: Int, y: Int): Int = {
    if (x == 0) y
    else if (x < 0) gcd(-x, y)
    else if (y < 0) -gcd(x, -y)
    else gcd(y % x, x)
  }
}
// end-Rational-gcd

// begin-Rational
class Rational(n: Int, d: Int) {

  private val g = MathUtility.gcd(n, d)

  val numerator: Int = n / g
  val denominator: Int = d / g

  def +(that: Rational) =
    new Rational(numerator * that.denominator + that.numerator * denominator,
      denominator * that.denominator)

  def -(that: Rational) =
    new Rational(numerator * that.denominator - that.numerator * denominator,
      denominator * that.denominator)

  def *(that: Rational) =
    new Rational(numerator * that.numerator, denominator * that.denominator)

  def /(that: Rational) =
    new Rational(numerator * that.denominator, denominator * that.numerator)

  def compare(that: Rational) = numerator * that.denominator - that.numerator * denominator

  def ==(that: Rational) = compare(that) == 0

  def reciprocal() =
    new Rational(denominator, numerator)

  def negate() =
    new Rational(-numerator, denominator)

  def >(that: Rational) =
    compare(that) > 0

  def <(that: Rational) =
    compare(that) < 0

  def >=(that: Rational) =
    this > that || this == that

  def <=(that: Rational) =
    this < that || this == that

  // need proper object equality for matchers
  override def equals(o: Any) = o match {
    case that: Rational => this == that
    case _ => false
  }

  override def hashCode = (numerator.hashCode, denominator.hashCode) hashCode

}
// end-Rational
