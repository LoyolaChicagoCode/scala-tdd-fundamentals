package scalatddpackt

package object rational {


/*
 * This version of Rational is based on Martin Odersky's Scala by Example.
 * source: http://www.scala-lang.org/docu/files/ScalaByExample.pdf
 *
 * We include a number of additions/improvements based on Harrington and Thiruvathukal's C# Book,
 * which includes an elaboration of other needed methods and full unit tests.
 * source: http://introcs.cs.luc.edu
 */

// begin-RationalMathUtility-gcd
  def gcd(x: Int, y: Int): Int = {
    if (x == 0) y
    else if (x < 0) gcd(-x, y)
    else if (y < 0) -gcd(x, -y)
    else gcd(y % x, x)
  }
// end-RationalMathUtility-gcd

  // making it look like case class but totally orthogonal/optional
  object Rational {
    def apply(n: Int, d: Int) = new Rational(n, d)
    def unapply(r: Rational): Option[(Int, Int)] = Some((r.numerator, r.denominator))
  }

// begin-RationalClass
  class Rational(n: Int, d: Int) extends Ordered[Rational] {

    private val g = gcd(n, d)

    // RationalClass.Initialization
    val numerator: Int = n / g
    val denominator: Int = d / g
    if (denominator == 0)
      throw new ArithmeticException()

    // RationalClass.Arithmetic
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

    def reciprocal() =
      new Rational(denominator, numerator)

    def negate() =
      new Rational(-numerator, denominator)

    // RationalClass.Comparisons
    def compare(that: Rational) = numerator * that.denominator - that.numerator * denominator

    // RationalClass.Objects
    override def equals(o: Any) = o match {
      case that: Rational => compare(that) == 0
      case _ => false
    }

    override def hashCode = (numerator.hashCode, denominator.hashCode) hashCode

    // RationalClass.Done
  }
  // end-RationalClass

}
