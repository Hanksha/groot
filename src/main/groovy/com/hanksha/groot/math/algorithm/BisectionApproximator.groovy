package com.hanksha.groot.math.algorithm

import com.hanksha.groot.math.Expression

/**
 * Created by vivien on 8/20/16.
 */
class BisectionApproximator {

    static RootApproximation getRoot(Expression func, double x0, double x1, Map options = [:]) {
        def maxIter = options.maxIter?: RootApproximation.MAX_NUM_ITER
        def precision = options.precision?:6
        def interval = options.interval?:0.000001
        def roundingMethod = options.roundingMethod == 'truncate' ?
                                        {double it -> it.trunc(precision)} :
                                        {double it -> it.round(precision)}

        def n = 1l
        def x2 = 0d
        def prevX2 = 0d
        def fx = 0d

        def steps = []

        while(n <= maxIter) {
            prevX2 = x2
            x2 = roundingMethod((x0 + x1) / 2)

            fx = roundingMethod(func.eval(x2))

            steps << [n: n, x:x2, fx: fx]

            println steps.last()

            if(n != 1 && Math.abs(x2 - prevX2) <= interval)
                break

            if(fx < 0)
                x0 = x2
            else if(fx > 0)
                x1 = x2

            n++
        }

        new RootApproximation(root: x2, steps: steps)
    }

}