/**
 * Created by gkt on 5/18/15.
 */

package scalatddpackt

import math.{abs, pow}

package object money {

  def getMoney(dollars: Long, cents: Long, precision : Int): Money = {
    require(precision > 0)
    val decimalMultiplier = math.pow(10, precision).toLong
    if (dollars < 0 || cents < 0)
      Money(-(decimalMultiplier * abs(dollars) + abs(cents)), precision)
    else
      Money(decimalMultiplier * dollars + cents, precision)
  }

  // TODO: Allow for money to be constructed from any level of precision
  def getDecimalMoney(dollars: Double, precision : Int): Money = {
    require(precision > 0)
    val decimalMultiplier = math.pow(10, precision).toLong // DRY (fix later)
    val doubleFudgeFactor = 49 / math.pow(10, precision + 2)
    print(s"multipler $decimalMultiplier and fudge $doubleFudgeFactor")
    val sign = if (dollars < 0) -1 else 1
    val cents = decimalMultiplier * (abs(dollars) + doubleFudgeFactor)
    Money(sign * cents.toLong, precision)
  }

  case class Money(cents: Long, precision : Int) extends Ordered[Money] {

    require(precision > 0)

    def decimalMultiplier = math.pow(10, precision).toLong

    def dollarsOnly = cents / decimalMultiplier

    def centsOnly = cents % decimalMultiplier

    def maxPrecision(that: Money) = math.max(precision, that.precision)

    def balance(that : Money) : (Money, Money, Int) = {
      val lhs = this.toPrecision(maxPrecision(that))
      val rhs = that.toPrecision(maxPrecision(that))
      (lhs, rhs, maxPrecision(that))
    }

    def +(that: Money) = {
      balance(that) match {
        case (left, right, precision) => Money(left.cents + right.cents, precision)
      }
    }

    def -(that: Money) = {
      balance(that) match {
        case (left, right, precision) => Money(left.cents - right.cents, precision)
      }
    }

    def *(n: Long) = Money(cents * n, precision)

    def /(n: Long) = Money(cents / n, precision)

    def /(that : Money) : Long = {
      balance(that) match {
        case (left, right, precision) => left.cents / right.cents
      }
    }

    def %(n: Long) = Money(cents % n, precision)

    def %(that : Money) = {
      balance(that) match {
        case (left, right, precision) => Money(left.cents % right.cents, precision)
      }
    }

    override def equals(o: Any) = o match {
      case that: Money => compare(that) == 0
      case _ => false
    }

    def compare(that: Money) = (cents - that.cents).toInt

    override def hashCode = cents.hashCode()

    override def toString(): String = {
      val decimalMultiplier = math.pow(10, precision).toLong
      val d = cents / decimalMultiplier
      val c = cents % decimalMultiplier
      f"Money($d $c/$decimalMultiplier)"
    }

    def toPrecision(newPrecision : Int) : Money = {

      if (newPrecision == precision)
        this
      if (newPrecision >= precision)
        getMoney(dollarsOnly, centsOnly * math.pow(10, newPrecision - precision).toLong, newPrecision)
      else
        getMoney(dollarsOnly, centsOnly / math.pow(10, precision - newPrecision).toLong, newPrecision)
    }
  }
}
