/**
 * Created by gkt on 1/5/15.
 */

package scalatddpackt;

import java.util.Arrays;

// begin-JavaRationalClass

public class JRational implements Comparable<JRational> {

    /* Python Fractions gcd() works as follows:
    Return the greatest common divisor of the integers a and b. If either a or b is nonzero, then the absolute
    value of gcd(a, b) is the largest integer that divides both a and b. gcd(a,b) has the same sign as b if b is
    nonzero; otherwise it takes the sign of a. gcd(0, 0) returns 0.

    Martin's implementation follows this.
    */

    // begin-JavaRationalMathUtility-gcd

    public static int gcd(int x, int y) {
        if (x == 0)
            return y;
        else if (x < 0)
            return gcd(-x, y);
        else if (y < 0)
            return -gcd(x, -y);
        else
            return gcd(y % x, x);
    }

    // end-JavaRationalMathUtility-gcd

    private int numerator, denominator, testQuotient;

    // JavaRationalClass.Initialization

    public JRational(int initialNumerator, int initialDenominator) throws ArithmeticException {
        int g = gcd(initialNumerator, initialDenominator);
        numerator = initialNumerator / g;
        denominator = initialDenominator / g;
        testQuotient = initialNumerator / initialDenominator;
    }

    // JavaRationalClass.Boilerplate

    public int getN() {
        return numerator;
    }

    public int getD() {
        return denominator;
    }

    // JavaRationalClass.Arithmetic

    public JRational add(JRational that) {
        return new JRational(numerator * that.denominator + that.numerator * denominator,
                denominator * that.denominator);
    }

    public JRational subtract(JRational that) {
        return new JRational(numerator * that.denominator - that.numerator * denominator,
                denominator * that.denominator);

    }

    public JRational multiply(JRational that) {
        return new JRational(numerator * that.numerator, denominator * that.denominator);
    }

    public JRational divide(JRational that) {
        return new JRational(numerator * that.denominator, denominator * that.numerator);

    }

    public JRational reciprocal() {
        return new JRational(denominator, numerator);
    }

    public JRational negate() {
        return new JRational(-numerator, denominator);
    }

    // JavaRationalClass.Comparisons
    @Override
    public int compareTo(JRational that) {
        return numerator * that.denominator - that.numerator * denominator;
    }


    // JavaRationalClass.Objects

    @Override
    public int hashCode() {
        int[] pair = { numerator, denominator};
        return Arrays.hashCode(pair);
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof JRational) {
            return compareTo( (JRational) that) == 0;
        } else
            return false;
    }
}

// end-JavaRationalClass

