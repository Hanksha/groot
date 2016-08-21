package com.hanksha.groot

import com.hanksha.groot.math.Expression
import com.hanksha.groot.math.Tokenizer
import org.junit.Test

/**
 * Created by vivien on 8/20/16.
 */
class ExpressionTests {

    @Test
    void testTokenizer() {
        def tokens = Tokenizer.tokenize('(3 + x^2) * 7e-2 + -10 / cos(x) % max(3, x)')
        assert tokens == ['(', '3', '+', 'x', '^', '2', ')', '*', '7e-2', '+', '-10', '/', 'cos', '(', 'x', ')', '%', 'max', '(', '3', ',', 'x', ')']
    }

    @Test
    void testPlus() {
        def exp = new Expression('4 + x')
        def x = 2
        assert exp.eval(x) == 4 + x
    }

    @Test
    void testMinus() {
        def exp = new Expression('4 - x')
        def x = 2
        assert exp.eval(x) == 4 - x
    }

    @Test
    void testMul() {
        def exp = new Expression('4 * x')
        def x = 2
        assert exp.eval(x) == 4 * x
    }

    @Test
    void testDiv() {
        def exp = new Expression('4 / x')
        def x = 2
        assert exp.eval(x) == 4 / x
    }

    @Test
    void testMod() {
        def exp = new Expression('4 % x')
        def x = 2
        assert exp.eval(x) == 4 % x
    }

    @Test
    void testPower() {
        def exp = new Expression('x^2')
        def x = 2
        assert exp.eval(x) == Math.pow(x, 2)
    }

    @Test
    void testCos() {
        def exp = new Expression('cos x')
        def x = Math.PI
        assert exp.eval(x) == Math.cos(x)
    }

    @Test
    void testSin() {
        def exp = new Expression('sin x')
        def x = Math.PI
        assert exp.eval(x) == Math.sin(x)
    }

    @Test
    void testTan() {
        def exp = new Expression('tan x')
        def x = Math.PI
        assert exp.eval(x) == Math.tan(x)
    }

    @Test
    void testMax() {
        def exp = new Expression('max(x, 2)')
        def x = 4
        assert exp.eval(x) == Math.max(x, 2)
    }

    @Test
    void testMin() {
        def exp = new Expression('min(x, 2)')
        def x = 4
        assert exp.eval(x) == Math.min(x, 2)
    }

    @Test
    void testLog() {
        def exp = new Expression('log x')
        def x = 4
        assert exp.eval(x) == Math.log(x)
    }

    @Test
    void testAbs() {
        def exp = new Expression('abs x')
        def x = -4
        assert exp.eval(x) == Math.abs(x)
    }

    @Test
    void testShortExpression() {
        def exp = new Expression('2 + x * 8 / x')
        def x = 4
        assert exp.eval(x) == 2 + x * 8 / x
    }

    @Test
    void testNegativeWithFunction() {
        def exp = new Expression('2 - cos x + 10')
        def x = Math.PI
        assert exp.eval(x) == 2 - Math.cos(x) + 10
    }

    @Test
    void testParenthesis() {
        def exp = new Expression('(3 + x) * 7 + (10 / x) / 2')
        def x = 2
        assert exp.eval(x) == (3 + x) * 7 + (10 / x) / 2
    }

    @Test
    void testExpressionWithFunction() {
        def exp = new Expression('4 + max(x,3) - abs(x)')
        def x = -4
        assert exp.eval(x) == 4 + Math.max(x, 3) - Math.abs(x)
    }

    @Test
    void testExpression() {
        def exp = new Expression('4/x + 3 * 7 - max(1, 2)')
        def x = 2
        assert exp.eval(x) == 4/x + 3 * 7 - Math.max(1, 2)
    }

    @Test
    void testLongExpression() {
        def exp = new Expression('(3 + x^2) * 7e-2 + (-10 / max(x, 3)) / cos(x)')
        def x = 4
        assert exp.eval(x) == (3 + Math.pow(x,2)) * 7e-2 + -10 / Math.max(x, 3) / Math.cos(x)
    }

    @Test
    void testVeryLongExpression() {
        def exp = new Expression('(sin(max(x, 3) / 3 * 3.1415) / x - tanx + 10) * abs(x^3) + x / 5^x')
        def x = -3
        assert exp.eval(x) == (Math.sin(Math.max(x, 3) / 3 * 3.1415) / x - Math.tan(x) + 10) * Math.abs(Math.pow(x,3)) + x / Math.pow(5,x)
    }
}
