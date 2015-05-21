/**
 * Created by gkt on 5/20/15.
 */

import org.scalatest._
import scalatddpackt.money._


class MoneyTest extends FlatSpec with Matchers {
  "factory methods" should "convert to cents properly" in {
    info("positive")
    getMoney(1, 25).cents should be (125L)
    getMoney(1.25).cents should be (125L)
    getMoney(1.256).cents should be (126L)
    getMoney(1.254).cents should be (125L)
    info("negative")
    getMoney(-1, 25).cents should be (-125L)
    getMoney(1, -25).cents should be (-125L)
    getMoney(-1, -25).cents should be (-125L)
    getMoney(-1.25).cents should be (-125L)
    getMoney(-1.256).cents should be (-126L)
    getMoney(-1.254).cents should be (-125L)

  }

  "relational operators" should "work as expected" in {
    val m1 = Money(125)
    val m2 = Money(135)
    val m3 = Money(125)
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
    val m1 = getMoney(1.25)
    val m2 = getMoney(0.25)
    val m3 = getMoney(1.26)

    info("addition and subtraction")
    m1 + m2 should be (getMoney(1.50))
    m1 - m2 should be (getMoney(1.00))

    info("multiplication")
    m1 * 5 should be (getMoney(6.25))

    info("division without remainder")
    m1 / 5 should be (getMoney(0.25))
    m1 % 5 should be (getMoney(0.00))

    info("division with remainder")
    val m = m3
    m3 / 5 should be (getMoney(0.25))
    m3 % 5 should be (getMoney(0.01))

    info("division involving Money")
    m1 / m2 should be (5L)
    m3 / m2 should be (5L)
    m3 % m2 should be (getMoney(0.01))
  }

  "Within collections" should "work" in {
    info("on Set[Money]")
    val m1 = getMoney(125)
    val m2 = getMoney(135)
    val m3 = getMoney(145)
    val m4 = getMoney(135)
    val s = Set(m1, m2, m3, m4)
    assert(s.size == 3)
  }

}
