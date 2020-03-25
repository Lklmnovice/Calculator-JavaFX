package model;

import java.util.Arrays;

/**
 * A Class that simulate a Calculator
 */
public final class Calculator {
    private String exp;             //holds the expression
    private StringBuilder builder;
    private int pos;                //for parsing

    private static final char[] ops = new char[]{'(', ')', '*', '+', '-', '/'};


    public Calculator() {
        this.builder = new StringBuilder();
        this.pos = -1;
        this.exp = null;
    }

/*
    Functionality
    - AC
    - Delete
    - insert a digit
    - compute some operations: {+, - , * , \}
    - insert parentheses ()
    - eval
*/

    /**
     * Clear the memory
     */
    public void allClear() {
        this.builder = new StringBuilder();
        this.pos = -1;
        this.exp = null;
    }

    /**
     * Remove the last digit or the last operator
     */
    public void delete() {
        if (builder.length() > 0)
            builder.setLength(builder.length() - 1);
    }

    public void insertDigit(int n) {
        if (n < 0 || n > 9) throw new IllegalArgumentException("out of range");
        builder.append(n);
    }

    public void insertDot() {
        builder.append('.');
    }

    public void insertOperator(char op) {
        if (Arrays.binarySearch(ops, op) < 0) throw new IllegalArgumentException("wrong operator: " + op);
        builder.append(op);
    }

    public String getExpression() {
        this.exp = builder.toString();
        return exp;
    }

    /**
     * Evaluate the current expression
     * Credit to @see <a href="https://stackoverflow.com/a/26227947">original author</a>
     * @return
     */

    public double eval() {
        getExpression();
        if (!exp.isEmpty())
            pos = 0;

        return evalExpression();
    }

    private boolean checkIfPresent(char c) {
        if (pos < exp.length() && exp.charAt(pos) == c) {
            pos++;
            return true;
        } else
            return false;
    }

    private double evalExpression() {
        double left = evalTerm();
        if (checkIfPresent('+'))
            left += evalExpression();
        else if (checkIfPresent('-'))
            left -= evalExpression();

        return left;
    }

    private double evalTerm() {
        double left = evalFactor();
        if (checkIfPresent('*'))
            left *= evalTerm();
        else if (checkIfPresent('/'))
            left /= evalTerm();

        return left;
    }

    private double evalFactor() {
        double x;
        if (checkIfPresent('(')) {
            x = evalExpression();
            checkIfPresent(')');
        } else if (checkIfPresent('+'))
            x = evalDouble();
        else if (checkIfPresent('-'))
            x = -evalDouble();
        else
            x = evalDouble();
        return x;
    }

    private double evalDouble() {
        int start = pos;
        boolean onlyOneDot = true;
        while (pos < exp.length() && (
                (exp.charAt(pos) >= '0' && exp.charAt(pos) <= '9')
            || (onlyOneDot && exp.charAt(pos) == '.'))) {
            if (exp.charAt(pos) == '.')
                onlyOneDot = false;
            pos++;
        }
        String subString = exp.substring(start, pos);
        System.out.println(subString);
        return Double.parseDouble(subString);
    }

    public void insert(char c) {
        if (c >= '0' && c <= '9')
            insertDigit(c - '0');
        else if (c == '.')
            insertDot();
        else
            insertOperator(c);
    }

}
