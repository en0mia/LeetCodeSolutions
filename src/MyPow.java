/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 05/08/22
    @copyright: Check the repository license.
*/

// Problem link: https://leetcode.com/problems/powx-n/
public class MyPow {
    public double myPow(double x, int n) {
        if (x == 1.0) {
            return 1.0;
        } else if (x == -1.0) {
            if (n % 2 == 0) {
                return 1.0;
            }
            return -1.0;
        }

        if (n == Integer.MIN_VALUE) {
            return 0.0;
        }

        if (n < 0) {
            x = 1/x;
            n *= -1;
        } else if (n == 0) {
            return 1.0;
        } else if (n == 1) {
            return x;
        }

        double base = x;

        while (n > 1) {
            x *= base;
            n--;
        }

        return x;
    }
}
