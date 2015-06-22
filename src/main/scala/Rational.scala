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

  // begin-RationalObject
  object Rational {
    def apply(n: Int, d: Int) = new Rational(n, d)

    def unapply(r: Rational): Option[(Int, Int)] = Some((r.num, r.den))
  }

  // end-RationalObject

  // begin-RationalClass
  class Rational(n: Int, d: Int) extends Ordered[Rational] {

    private val g = gcd(n, d)

    // these short names will help with formatting for the book

    // RationalClass.Initialization

    val num: Int = n / g
    val den: Int = d / g

    // perform test quotient for possible ArithmeticException
    val testQuotient = num / den

    // RationalClass.Arithmetic
    def +(that: Rational) =
      new Rational(num * that.den + that.num * den, den * that.den)

    def -(that: Rational) =
      new Rational(num * that.den - that.num * den, den * that.den)

    def *(that: Rational) =
      new Rational(num * that.num, den * that.den)

    def /(that: Rational) =
      new Rational(num * that.den, den * that.num)

    def reciprocal() =
      new Rational(den, num)

    def negate() =
      new Rational(-num, den)

    // RationalClass.Comparisons
    def compare(that: Rational) = num * that.den - that.num * den

    // RationalClass.Objects
    override def equals(o: Any) = o match {
      case that: Rational => compare(that) == 0
      case _ => false
    }

    override def hashCode = (num.hashCode, den.hashCode).hashCode

    // RationalClass.Done

    override def toString(): String = s"Rational($num/$den; $g)"
  }

  // end-RationalClass

  // TODO Add unit tests for these.
  // begin-RationalContext
  implicit class RationalContext(val sc: StringContext) extends AnyVal {
    def frac(args: Any*): Rational = {
      val text = sc.parts.mkString
      val fraction = """(\-?)(\d+)/(\d+)""".r
      text match {
        case fraction(sign, n, d) => {
          val nSigned = if (sign == "-") -n.toInt else n.toInt
          Rational(nSigned, d.toInt)
        }
      }
    }

    def dec(args: Any*): Rational = {
      val text = sc.parts.mkString
      val decimal = """(\-?)(\d+).(\d+)""".r
      text match {
        case decimal(sign, whole, fractional) => {
          val signMultiplier = if (sign == "-") -1 else 1
          val wr = Rational(signMultiplier * whole.toInt, 1)
          val fr = Rational(signMultiplier * fractional.toInt, math.pow(10, fractional.length).toInt)
          wr + fr
        }
      }
    }
  }
  // end-RationalContext

}

