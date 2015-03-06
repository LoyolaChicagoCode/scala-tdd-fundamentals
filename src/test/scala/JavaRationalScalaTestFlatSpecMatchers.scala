import org.scalatest._
import scalatddpackt._
import scalatddpackt.JRational.gcd


class JRationalScalaTestFlatSpecMatchers extends FlatSpec
with Matchers {

  // JRationalFlatSpec.GCD
  import scalatddpackt.JRational.gcd

  "GCD involving 0" should "give y for gcd(0, y)" in {
    gcd(0, 5) should be(5)
  }

  it should "give x for gcd(x, 0)" in {
    gcd(0, 5) should be(5)
  }

  "GCD not involving 0" should "be 3" in {
    gcd(3 * 1, 3 * 3) should be(3)
  }

  it should "be 5" in {
    gcd(5 * 1, 5 * 5) should be(5)
  }
  // JRationalFlatSpec.Initializing
  "Initializing" should "reduce fractions (+,+)" in {
    val r1 = new JRational(2, 4)
    r1.getN should be(1)
    r1.getD should be(2)
  }

  it should "reduce fractions (-,+)" in {
    val r1 = new JRational(-2, 4)
    r1.getN should be(-1)
    r1.getD should be(2)
  }

  it should "reduce fractions (-,-)" in {
    val r1 = new JRational(-3, -6)
    r1.getN should be(1)
    r1.getD should be(2)
  }

  it should "prohibit zero denominator" in {
    intercept[ArithmeticException] {
      new JRational(3, 0)
    }
  }

  // JRationalFlatSpec.Arithmetic
  "Arithmetic" should "perform addition" in {
    val r1 = new JRational(47, 64)
    val r2 = new JRational(-11, 64)
    r1.add(r2) should ===(new JRational(36, 64))
    r1.add(r2) should be(new JRational(36, 64))
  }

  it should "perform subtraction" in {
    val r1 = new JRational(47, 64)
    val r2 = new JRational(-11, 64)
    r1.subtract(r2) should be(new JRational(58, 64))
  }

  it should "perform multiplication" in {
    val r1 = new JRational(47, 64)
    val r2 = new JRational(-11, 64)
    r1.multiply(r2) should be(new JRational(47 * -11, 64 * 64))
  }

  it should "perform division" in {
    val r1 = new JRational(47, 64)
    val r2 = new JRational(-11, 64)
    r1.divide(r2) should be(new JRational(47, -11))
  }

  it should "perform the reciprocal" in {
    val r1 = new JRational(47, 64)
    r1.reciprocal() should be(new JRational(64, 47))
  }

  it should "perform negation" in {
    val r1 = new JRational(47, 64)
    val r2 = new JRational(-11, 64)
    r1.negate() should be(new JRational(-47, 64))
  }

  // JRationalFlatSpec.Comparisons

  "Comparisons" should "perform ==" in {
    val r1 = new JRational(2, 4)
    val r2 = new JRational(1, 2)
    info("numeric equality (==) works")
    assert(r1 == r2)
    info("object equality works, e.g. equals()")
    assert(r1.equals(r2))
    info("hashcode() works")
    assert(r1.hashCode() == r2.hashCode())

  }
  it should "perform <" in {
    val r1 = new JRational(-3, 6)
    val r2 = new JRational(2, 4)
    info("operator < works")
    assert(r1.compareTo(r2) < 0)
  }

  it should "perform >" in {
    val r1 = new JRational(-3, 6)
    val r2 = new JRational(2, 4)
    info("operator > works")
    assert(r2.compareTo(r1) > 0)
  }


  // JRationalFlatSpec.Collectionss
  "Within collections" should "work" in {
    info("on Set[JRational]")
    val r1 = new JRational(-3, 6)
    val r2 = new JRational(2, 4)
    val r3 = new JRational(1, 2)
    val s = Set(r1, r2, r3)
    assert(s.size == 2)
  }

  // JRationalFlatSpec.End

}