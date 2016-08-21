package com.hanksha.groot.math.algorithm

import com.hanksha.groot.math.Expression

/**
 * Created by vivien on 8/20/16.
 */
class BisectionApproximator {

    static RootApproximation getRoot(Expression func, double x0, double x1, Map options = [:]) {
        def maxIter = options.maxIter?: Long.MAX_VALUE
        def precision = options.precision?:6
        def interval = options.interval?:0.000001
        def roundingMethod = options.roundingMethod == 'truncate' ?
                                        {double it -> it.trunc(precision)} :
                                        {double it -> it.round(precision)}

        def n = 1l
        def x2
        def prevX2
        def fx

        def steps = []

        while(n <= maxIter) {
            prevX2 = x2
            x2 = roundingMethod.call((x0 + x1) / 2)

            fx = roundingMethod.call(func.eval(x2))

            steps << "Step $n, x = $x2, fx = $fx".toString()

            println steps.last()

            if(prevX2 && Math.abs(x2 - prevX2) < interval)
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