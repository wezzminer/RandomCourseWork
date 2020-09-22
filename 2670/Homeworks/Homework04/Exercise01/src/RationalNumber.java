


public class RationalNumber {

    private int numerator;
    private int denominator;

    public RationalNumber(int numerator, int denominator) {

        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator cannot be equal to zero!");
        } else if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        int absNumerator = Math.abs(numerator);
        int absDenominator = Math.abs(denominator);
        int greatestCommonDivisor = 1;

        if (absNumerator > absDenominator) {
            greatestCommonDivisor = findGCD(absNumerator, absDenominator);
        } else if (absNumerator < absDenominator) {
            greatestCommonDivisor = findGCD(absDenominator, absNumerator);
        }

        if (absNumerator == absDenominator) {
            this.numerator = 1;
            this.denominator = 1;
        } else if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else {
            this.numerator = numerator / greatestCommonDivisor;
            this.denominator = denominator / greatestCommonDivisor;
        }

    }

    public RationalNumber() {
        new RationalNumber(0, 1);
    }

    private int findGCD(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {return 0;}

        while (true) {
            int remainder = number1 % number2;

            if (remainder == 0) {return number2;}

            number1 = number2;
            number2 = remainder;
        }
    }

    @Override
    public String toString() {
        String fraction = "";

        if (denominator == 1) {
            fraction += numerator;
        } else if (numerator == 0) {
            fraction += 0;
        } else {
            fraction = numerator + " / " + denominator;
        }

        return fraction;
    }

    public void setRationalNumber(int numerator, int denominator) {

    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public RationalNumber add(RationalNumber fraction1) {
        if (fraction1.getNumerator() == 0) {
            return this;
        } else if (this.getNumerator() == 0) {
            return fraction1;
        }

        int newNumerator = (fraction1.getNumerator() * this.getDenominator()) +
                           (this.getNumerator() * fraction1.getDenominator());

        int newDenominator = (fraction1.getDenominator() * this.getDenominator()) +
                             (this.getDenominator() * fraction1.getDenominator());

        return new RationalNumber(newNumerator, newDenominator);
    }

}
