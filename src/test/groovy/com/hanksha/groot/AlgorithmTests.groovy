package com.hanksha.groot

import com.hanksha.groot.math.Expression
import com.hanksha.groot.math.algorithm.BisectionApproximator
import com.hanksha.groot.math.algorithm.NewtonRaphsonApproximator
import com.hanksha.groot.math.algorithm.RegulaFalsiApproximator
import com.hanksha.groot.math.algorithm.SecantApproximator
import org.junit.Test

/**
 * Created by vivien on 8/21/16.
 */
class AlgorithmTests {

    @Test
    void testBisection() {
        assert BisectionApproximator.getRoot(new Expression('x - cos(x)'), -10, 10).root == 0.739085
        assert BisectionApproximator.getRoot(new Expression('x^3 + 3 * x^2 + 12 * x + 8'), -5, 5).root == -0.778978
    }

    @Test
    void testRegulaFalsi() {
        assert RegulaFalsiApproximator.getRoot(new Expression('x - cos(x)'), -10, 10).root == 0.739085
        assert RegulaFalsiApproximator.getRoot(new Expression('x^3 + 3 * x^2 + 12 * x + 8'), -5, 5).root == -0.778981
    }

    @Test
    void testSecant() {
        assert SecantApproximator.getRoot(new Expression('x - cos(x)'), -10, 10).root == 0.739085
        assert SecantApproximator.getRoot(new Expression('x^3 + 3 * x^2 + 12 * x + 8'), -5, 5).root == -0.778977
    }

    @Test
    void testNewtonRaphson() {
        assert NewtonRaphsonApproximator.getRoot(new Expression('x - cos(x)'), 1).root == 0.739085
        assert NewtonRaphsonApproximator.getRoot(new Expression('x^3 + 3 * x^2 + 12 * x + 8'), 1).root == -0.778977
    }
}
