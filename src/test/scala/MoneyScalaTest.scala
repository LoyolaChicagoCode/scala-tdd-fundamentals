/**
 * Created by gkt on 5/20/15.
 */

import org.scalatest._
import scalatddpackt.money._


class MoneyTest extends FlatSpec with Matchers {
  "factory methods" should "convert to cents properly" in {
    info("positive")
    getMoney(1, 25, 2).cents should be (125L)
    getDecimalMoney(1.25, 2).cents should be (125L)
    getDecimalMoney(1.256, 2).cents should be (126L)
    getDecimalMoney(1.254, 2).cents should be (125L)
    info("negative")
    getMoney(-1, 25, 2).cents should be (-125L)
    getMoney(1, -25, 2).cents should be (-125L)
    getMoney(-1, -25, 2).cents should be (-125L)
    getDecimalMoney(-1.25, 2).cents should be (-125L)
    getDecimalMoney(-1.256, 2).cents should be (-126L)
    getDecimalMoney(-1.254, 2).cents should be (-125L)

  }

  "relational operators" should "work as expected" in {
    val m1 = Money(125, 2)
    val m2 = Money(135, 2)
    val m3 = Money(125, 2)
    info("less than/less than or equal")
    assert(m1 < m2)
    assert(m1 <= m2)
    info("greater than/greater than or equal")
    assert(m2 > m1)
    assert(m2 >= m1)
    info("equality")
    assert(m1 == m3)
    assert(m1 != m2)
    assert(m2 != m3)
  }

  "arithmetic" should "work as expected" in {
    val m1 = getDecimalMoney(1.25, 2)
    val m2 = getDecimalMoney(0.25, 2)
    val m3 = getDecimalMoney(1.26, 2)

    info("addition and subtraction")
    m1 + m2 should be (getDecimalMoney(1.50, 2))
    m1 - m2 should be (getDecimalMoney(1.00, 2))

    info("multiplication")
    m1 * 5 should be (getDecimalMoney(6.25, 2))

    info("division without remainder")
    m1 / 5 should be (getDecimalMoney(0.25, 2))
    m1 % 5 should be (getDecimalMoney(0.00, 2))

    info("division with remainder")
    val m = m3
    m3 / 5 should be (getDecimalMoney(0.25, 2))
    m3 % 5 should be (getDecimalMoney(0.01, 2))

    info("division involving Money")
    m1 / m2 should be (5L)
    m3 / m2 should be (5L)
    m3 % m2 should be (getDecimalMoney(0.01, 2))
  }

  "microtransactions with smaller fractions" should "also be supported" in {
    info("using whole dollars and frations")
    // 0.315
    val m1 = getMoney(0, 315, 3)
    // 0.3
    val m2 = getMoney(0, 3, 1)
    // sum has 3 digits precision, i.e. max(3, 1)
    (m1 + m2).precision should be(3)
    // sum has 615 "cents"; 0.615
    (m1 + m2).cents should be (615L)

    info("using decimals")
    val m3 = getDecimalMoney(1.125, 3)
    val m4 = getDecimalMoney(1.5, 1)
    (m3 + m4).precision should be (3)
    (m3 + m4).cents should be (2625L)

  }

  "Within collections" should "work" in {
    info("on Set[Money]")
    val m1 = getDecimalMoney(1.25, 2)
    val m2 = getDecimalMoney(1.35, 2)
    val m3 = getDecimalMoney(1.45, 2)
    // m4 == m2
    val m4 = getDecimalMoney(1.35, 2)
    // m5 == m2
    val m5 = getDecimalMoney(1.350, 3)
    // m6 == m1
    val m6 = getDecimalMoney(1.2500, 4)
    val s = Set(m1, m2, m3, m4, m5, m6)
    assert(s.size == 3)
  }
  
  "String contexts" should "parse currencies" in {
    info("US Dollars ($)")
    usd"$$1.25" + usd"$$1.50" should be (getMoney(2, 75, 2))
    info("Euros (€)")
    euro"€1,35" + euro"2,05" should be (getMoney(3, 40, 2))
  }

}
