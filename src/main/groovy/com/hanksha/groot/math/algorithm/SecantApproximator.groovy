package com.hanksha.groot.math.algorithm

import com.hanksha.groot.math.Expression

/**
 * Created by vivien on 8/22/16.
 */
class SecantApproximator {

    static RootApproximation getRoot(Expression func, double x0, double x1, Map options = [:]) {
        def maxIter = options.maxIter?: RootApproximation.MAX_NUM_ITER
        def precision = options.precision?:6
        def interval = options.interval?:0.000001
        def roundingMethod = options.roundingMethod == 'truncate' ?
                {double it -> it.trunc(precision)} :
                {double it -> it.round(precision)}

        def n = 1l
        def x2 = 0d
        def fx0 = 0d
        def fx1 = 0d
        def fx2 = 0d
        def prevX2 = 0d

        def secantFunc = { x1 - fx1 * ((x1 - x0)/(fx1 - fx0)) }

        def steps = []

        while(n <= maxIter) {
            prevX2 = x2

            fx0 = roundingMethod(func.eval(x0))
            fx1 = roundingMethod(func.eval(x1))

            x2 = roundingMethod(secantFunc())

            fx2 = roundingMethod(func.eval(x2))


            steps << "Step $n, x = $x2, fx = $fx2".toString()

            println steps.last()

            if(n != 1 && (Math.abs(x2 - prevX2) < interval || fx2 == fx1))
                break

            x0 = x1
            x1 = x2

            n++
        }

        new RootApproximation(root: x2, steps: steps)
    }

}
