package com.hanksha.groot

import com.hanksha.groot.math.Expression
import com.hanksha.groot.math.algorithm.BisectionApproximator
import org.junit.Test

/**
 * Created by vivien on 8/21/16.
 */
class AlgorithmTests {

    @Test
    void testBisection() {
        assert BisectionApproximator.getRoot(new Expression('x - cos(x)'), -10, 10).root == 0.739085
    }

}
