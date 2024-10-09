package com.google.foobar.fuel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 3.
 */
public class Solution {

    public static int[] solution(int[][] m) {

        // https://en.wikipedia.org/wiki/Absorbing_Markov_chain
        // In an absorbing Markov chain, the probabilities to end up in absorbing states can be calculated
        // using the matrix B = N * R, where:
        // * N is the fundamental matrix of the chain, computed as (I_t - Q)^{-1}
        // * t is the number of transient states
        // * r is the number of absorbing states
        // * I_t is an identity matrix of size t
        // * Q is the t*t transition matrix for transient states
        // * R is the t*r transition matrix from transient to absorbing states

        // number of states
        int n = m.length;

        // no states at all - empty result
        if (n == 0) {
            return new int[0];
        }

        // converting the array of ints to the array of rational probabilities
        Rational[][] probabilities = new Rational[n][n];
        // also calculating the list of absorbing states
        List<Integer> absorbingStates = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long total = 0;
            for (int j = 0; j < n; j++) {
                total += m[i][j];
            }
            boolean absorbingState = true;
            for (int j = 0; j < n; j++) {
                int frequency = m[i][j];
                if (i != j && frequency > 0) {
                    absorbingState = false;
                }
                probabilities[i][j] = new Rational(frequency, total);
            }
            if (absorbingState) {
                absorbingStates.add(i);
            }
        }

        // the number of absorbing states
        int r = absorbingStates.size();

        // if there's only one absorbing state, nothing to count here, the probability is always 1
        if (r == 1) {
            return new int[] {1, 1};
        }

        // the number of transient states
        int t = n - r;

        // no transient states - the system can only stay in the zero state
        if (t == 0) {
            int[] result = new int[n + 1];
            result[0] = 1;
            result[n] = 1;
            return result;
        }

        // converting the matrix to the canonical form - transient states should go first
        // making sure to keep the ordering of absorbing states
        for (int i = n - 1; i >= 0 && !absorbingStates.isEmpty(); i--) {
            int absorbingState = absorbingStates.get(absorbingStates.size() - 1);
            if (absorbingState != i) { // we have a wrong order
                Rational[] tempRow = probabilities[i];
                probabilities[i] = probabilities[absorbingState];
                probabilities[absorbingState] = tempRow;
                for (int j = 0; j < n; j++) {
                    Rational tempCell = probabilities[j][i];
                    probabilities[j][i] = probabilities[j][absorbingState];
                    probabilities[j][absorbingState] = tempCell;
                }
            }
            absorbingStates.remove(absorbingStates.size() - 1);
        }

        // finding the fundamental matrix N

        Rational[][] fundamental = new Rational[t][t];

        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                if (i == j) {
                    fundamental[i][j] = Rational.ONE.add(probabilities[i][j].negate());
                }
                else {
                    fundamental[i][j] = probabilities[i][j].negate();
                }
            }
        }

        // finding the first row of the inverse matrix (we only need the first row for the answer)
        inverse1row(fundamental, t);

        // finding the probabilities to end up in absorbing states

        Rational[] absorbProbabilities = new Rational[n - t];

        for (int i = 0; i < r; i++) {
            Rational prob = Rational.ZERO;
            for (int j = 0; j < fundamental.length; j++) {
                prob = prob.add(fundamental[0][j].mul(probabilities[j][i + t]));
            }
            absorbProbabilities[i] = prob;
        }

        // finding the least common multiplier of all probability denominators

        long lcm = 1;
        for (Rational probability : absorbProbabilities) {
            if (!probability.isZero()) {
                lcm = Rational.lcm(lcm, probability.denom);
            }
        }

        // calculating the result in the required format

        int[] result = new int[absorbProbabilities.length + 1];
        for (int i = 0; i < absorbProbabilities.length; i++) {
            Rational probability = absorbProbabilities[i];
            if (probability.isZero()) {
                result[i] = 0;
            }
            else if (lcm == probability.denom) {
                result[i] = (int) probability.num;
            }
            else {
                result[i] = (int) (probability.num * (lcm / probability.denom));
            }
        }

        result[result.length - 1] = (int) lcm;

        return result;

    }

    // finding the determinant of a matrix (assuming the matrix is square, and the determinant is not 0),
    // used in inverse matrix calculation
    private static Rational det(Rational[][] matrix, int size) {
        Rational result = Rational.ZERO;

        if (size == 1) {
            return matrix[0][0];
        }

        boolean negative = false;

        Rational[][] cofactors = new Rational[size - 1][size - 1];

        for (int i = 0; i < size; i++) {
            getCofactors(matrix, cofactors, 0, i, size);
            Rational m = matrix[0][i];
            if (negative) {
                m = m.negate();
            }
            result = result.add(m.mul(det(cofactors, size - 1)));
            negative = !negative;
        }

        return result;
    }

    // finding cofactors for the matrix, used in inverse matrix calculation
    private static void getCofactors(Rational[][] matrix, Rational[][] cofactors, int p, int q, int size) {
        int i = 0;
        int j = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row != p && col != q) {
                    cofactors[i][j++] = matrix[row][col];
                    if (j == size - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    // finding adjoints for the first row of the matrix (we only need the first one for the task)
    // used in inverse matrix calculation
    private static void adjoint1row(Rational[][] matrix, Rational[][] adjoint) {

        int size = matrix.length;
        Rational[][] cofactors = new Rational[size - 1][size - 1];
        if (size == 1) {
            adjoint[0][0] = Rational.ONE;
        }
        else {
            for (int i = 0; i < size; i++) {
                getCofactors(matrix, cofactors, i, 0, size);
                adjoint[0][i] = det(cofactors, size - 1);
                if ((i & 1) == 1) {
                    adjoint[0][i] = adjoint[0][i].negate();
                }
            }
        }
    }

    // calculating the first row of the inverse matrix (we only need the first one for the task)
    private static void inverse1row(Rational[][] matrix, int size) {
        // calculate reciprocal of the matrix determinant
        Rational invDeterminant = det(matrix, size).reciprocal();

        Rational[][] adjoint = new Rational[size][size];

        adjoint1row(matrix, adjoint);

        for (int j = 0; j < size; j++) {
            matrix[0][j] = adjoint[0][j].mul(invDeterminant);
        }
    }

    private static class Rational {

        public static final Rational ONE = new Rational(1, 1);
        public static final Rational ZERO = new Rational(0, 0);

        private long num;

        private long denom;

        public Rational(long num, long denom) {
            this.num = num;
            this.denom = denom;
            this.simplify();
        }

        public Rational negate() {
            return new Rational(-this.num, this.denom);
        }

        public Rational reciprocal() {
            return new Rational(this.denom, this.num);
        }

        public Rational add(Rational another) {
            if (another.isZero()) {
                return this;
            }
            if (this.isZero()) {
                return another;
            }
            long lcm = lcm(this.denom, another.denom);
            return new Rational(this.num * lcm / denom + another.num * lcm / another.denom, lcm);
        }

        public Rational mul(Rational another) {
            if (another.isZero()) {
                return another;
            }
            if (another.num == 1 && another.denom == 1) {
                return this;
            }
            if (this.num == 1 && this.denom == 1) {
                return another;
            }
            return new Rational(this.num * another.num, this.denom * another.denom);
        }

        public static long lcm(long n1, long n2) {
            BigInteger bn1 = BigInteger.valueOf(n1);
            BigInteger bn2 = BigInteger.valueOf(n2);
            return bn1.multiply(bn2).divide(bn1.gcd(bn2)).longValue();
        }

        public static long gcd(long n1, long n2) {
            return BigInteger.valueOf(n1).gcd(BigInteger.valueOf(n2)).longValue();
        }

        public void simplify() {
            if (this.num == 0) {
                this.denom = 0;
            }
            else {
                long gcd = gcd(this.num, this.denom);
                if (gcd > 1) {
                    this.num = this.num / gcd;
                    this.denom = this.denom / gcd;
                }
            }
        }

        public boolean isZero() {
            return this.num == 0;
        }

    }

}
