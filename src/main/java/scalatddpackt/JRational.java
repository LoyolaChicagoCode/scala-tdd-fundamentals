/**
 * Created by gkt on 1/5/15.
 */

package scalatddpackt;

import java.util.Arrays;

// begin-JavaRationalClass

public class JRational implements Comparable<JRational> {

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

    private int _n, _d, _q;

    public JRational(int n, int d) throws ArithmeticException {
        int g = gcd(n, d);
        _n = n / g;
        _d = d / g;
        _q = n / d;
    }

    // JavaRationalClass.Boilerplate

    public int getN() {
        return _n;
    }

    public int getD() {
        return _d;
    }

    // JavaRationalClass.Arithmetic

    public JRational add(JRational that) {
        return new JRational(_n * that._d + that._n * _d,
                _d * that._d);
    }

    public JRational subtract(JRational that) {
        return new JRational(_n * that._d - that._n * _d,
                _d * that._d);

    }

    public JRational multiply(JRational that) {
        return new JRational(_n * that._n, _d * that._d);
    }

    public JRational divide(JRational that) {
        return new JRational(_n * that._d, _d * that._n);

    }

    public JRational reciprocal() {
        return new JRational(_d, _n);
    }

    public JRational negate() {
        return new JRational(-_n, _d);
    }

    // JavaRationalClass.Comparisons
    @Override
    public int compareTo(JRational that) {
        return _n * that._d - that._n * _d;
    }


    // JavaRationalClass.Objects

    @Override
    public int hashCode() {
        int[] pair = { _n, _d };
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

