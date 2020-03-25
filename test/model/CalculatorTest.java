package model;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @org.junit.jupiter.api.Test
    void testAllClear() {
        Calculator c = new Calculator();
        c.insertDigit(1);
        c.insertDigit(2);
        c.insertDigit(3);
        c.insertDigit(4);
        c.insertOperator('+');
        c.insertDigit(1);
        c.insertDigit(2);
        c.allClear();
        assertEquals("", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testBasicAddition() {
        Calculator c = new Calculator();
        c.insertDigit(1);
        c.insertDigit(2);
        c.insertDigit(3);
        c.insertDigit(4);
        c.insertOperator('+');
        c.insertDigit(1);
        c.insertDigit(2);
        assertEquals(1246,  c.eval());
        assertEquals("1234+12", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testBasicAdditionFloatingPoints() {
        Calculator c = new Calculator();
        c.insertDigit(1);
        c.insertDigit(2);
        c.insertDot();
        c.insertDigit(1);
        c.insertOperator('+');
        c.insertDigit(1);
        c.insertDot();
        c.insertDigit(2);
        double result = c.eval();
        double expected = 13.3d;
        assertTrue(Math.abs(result - expected) <= Math.ulp(expected));
        assertEquals("12.1+1.2", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testBasicSubtraction() {
        Calculator c = new Calculator();
        c.insertDigit(1);
        c.insertDigit(2);
        c.insertDigit(3);
        c.insertDigit(4);
        c.insertOperator('-');
        c.insertDigit(1);
        c.insertDigit(2);
        assertEquals(1222,  c.eval());
        assertEquals("1234-12", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testBasicSubtractionFloatingPoints1() {
        Calculator c = new Calculator();
        c.insertDigit(1);
        c.insertDigit(2);
        c.insertDot();
        c.insertDigit(1);
        c.insertOperator('-');
        c.insertDigit(1);
        c.insertDot();
        c.insertDigit(2);
        double result = c.eval();
        double expected = 10.9d;
        assertTrue(Math.abs(result - expected) <= Math.ulp(expected));
        assertEquals("12.1-1.2", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testBasicSubtractionFloatingPoints2() {
        Calculator c = new Calculator();
        c.insertDot();
        c.insertDigit(1);
        c.insertOperator('-');
        c.insertDigit(1);
        c.insertDot();
        c.insertDigit(2);
        double result = c.eval();
        double expected = -1.1d;
        assertTrue(Math.abs(result - expected) <= Math.ulp(expected));
        assertEquals(".1-1.2", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testBasicMultiplication() {
        Calculator c = new Calculator();
        c.insertDigit(4);
        c.insertDigit(5);
        c.insertDigit(6);
        c.insertOperator('*');
        c.insertDigit(3);
        c.insertDigit(2);
        assertEquals(14592,  c.eval());
        assertEquals("456*32", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testBasicMultiplicationFloating() {
        Calculator c = new Calculator();
        c.insertDigit(4);
        c.insertDigit(5);
        c.insertDot();
        c.insertDigit(6);
        c.insertOperator('*');
        c.insertDigit(3);
        c.insertDot();
        c.insertDigit(2);
        double result = c.eval();
        double expected = 145.92d;
        assertTrue(Math.abs(result - expected) <= Math.ulp(expected));
        assertEquals("45.6*3.2", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testBasicDivision() {
        Calculator c = new Calculator();
        c.insertDigit(4);
        c.insertDigit(5);
        c.insertOperator('/');
        c.insertDigit(9);

        assertEquals(5,  c.eval());
        assertEquals("45/9", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testBasicDivisionFloating() {
        Calculator c = new Calculator();
        c.insertDigit(4);
        c.insertDot();
        c.insertDigit(5);
        c.insertOperator('/');
        c.insertDot();
        c.insertDigit(9);
        double result = c.eval();
        double expected = 5d;
        assertTrue(Math.abs(result - expected) <= Math.ulp(expected));
        assertEquals("4.5/.9", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testDivisionByZeroInfinity() {
        Calculator c = new Calculator();
        c.insertDigit(4);
        c.insertDot();
        c.insertDigit(5);
        c.insertOperator('/');
        c.insertDigit(0);
        assertEquals(Double.POSITIVE_INFINITY, c.eval());
        assertEquals("4.5/0", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testDivisionByZeroNaN() {
        Calculator c = new Calculator();
        c.insertDigit(0);
        c.insertOperator('/');
        c.insertDigit(0);
        assertEquals(Double.NaN, c.eval());
        assertEquals("0/0", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testUsageBrackets1() {
        // 12 * (2 - 3)
        Calculator c = new Calculator();
        c.insertDigit(1);
        c.insertDigit(2);
        c.insertOperator('*');
        c.insertOperator('(');
        c.insertDigit(2);
        c.insertOperator('-');
        c.insertDigit(3);
        c.insertOperator(')');
        assertEquals(-12, c.eval());
        assertEquals("12*(2-3)", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testUsageBrackets2() {
        // 12 * (2 - 3)
        Calculator c = new Calculator();
        c.insertDigit(1);
        c.insertDigit(2);
        c.insertOperator('*');
        c.insertOperator('(');
        c.insertOperator('-');
        c.insertDigit(2);
        c.insertOperator('-');
        c.insertDigit(3);
        c.insertOperator(')');
        assertEquals(-60, c.eval());
        assertEquals("12*(-2-3)", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testUsageBrackets3() {
        // 12 * (2 - 3)
        Calculator c = new Calculator();
        c.insertDigit(1);
        c.insertDigit(2);
        c.insertOperator('*');
        c.insertOperator('(');
        c.insertOperator('(');
        c.insertOperator('-');
        c.insertDigit(2);
        c.insertOperator('-');
        c.insertDigit(1);
        c.insertDigit(2);
        c.insertOperator(')');
        c.insertOperator('*');
        c.insertDigit(3);
        c.insertOperator(')');
        assertEquals(-504, c.eval());
        assertEquals("12*((-2-12)*3)", c.getExpression());
    }

    @org.junit.jupiter.api.Test
    void testUselessBrackets() {
        Calculator c = new Calculator();
        c.insertDigit(1);
        c.insertOperator(')');
        c.insertOperator(')');
        c.insertOperator(')');
        assertEquals(1, c.eval());
        assertEquals("1)))", c.getExpression());
    }


}