/**
 * Created by gkt on 5/18/15.
 */

package scalatddpackt

import math.abs

package object money {

  def getMoney(dollars: Long, cents: Long): Money =
    if (dollars < 0 || cents < 0)
      Money(-(100 * abs(dollars) + abs(cents)))
    else
      Money(100 * dollars + cents)

  def getMoney(dollars: Double): Money = {
    val sign = if (dollars < 0) -1 else 1
    val cents = 100L * (abs(dollars) + 0.0049)
    Money(sign * cents.toLong)
  }

  case class Money(cents: Long) extends Ordered[Money] {

    def +(that: Money) = Money(cents + that.cents)

    def -(that: Money) = Money(cents - that.cents)

    def *(n: Long) = Money(cents * n)

    def /(n: Long) = Money(cents / n)

    def %(n: Long) = Money(cents % n)

    override def equals(o: Any) = o match {
      case that: Money => compare(that) == 0
      case _ => false
    }

    def compare(that: Money) = (cents - that.cents).toInt

    override def hashCode = cents.hashCode()

    override def toString(): String = {
      val d = cents / 100
      val c = cents % 100

      f"Money($d.$c%02d)"
    }
  }
}
