package com.hanksha.groot.math.algorithm

import com.hanksha.groot.math.Expression

/**
 * Created by vivien on 8/22/16.
 */
class NewtonRaphsonApproximator {

    static RootApproximation getRoot(Expression func, double x, Map options = [:]) {
        def maxIter = options.maxIter?: RootApproximation.MAX_NUM_ITER
        def precision = options.precision?:6
        def interval = options.interval?:0.000001
        def roundingMethod = options.roundingMethod == 'truncate' ?
                {double it -> it.trunc(precision)} :
                {double it -> it.round(precision)}

        def n = 1l
        def fx = 0d
        def dfx = 0d
        def prevX = 0d

        def derivative = { (func.eval(x + fx) - fx) / fx }

        def steps = []

        while(n <= maxIter) {
            prevX = x

            fx = func.eval(x)
            dfx = derivative()

            x = roundingMethod(x - (fx / dfx))

            steps << [n: n, x: x, fx: fx]

            println steps.last()

            if(n != 1 && (Math.abs(x - prevX) <= interval))
                break

            n++
        }

        new RootApproximation(root: x, steps: steps)
    }

}
