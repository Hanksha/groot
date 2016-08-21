package com.hanksha.groot.math

import static java.lang.Math.*

/**
 * Created by vivien on 8/20/16.
 */
enum Function {

    COS('cos', {double v -> cos(v)}),
    SIN('sin', {double v -> sin(v)}),
    TAN('tan', {double v -> tan(v)}),
    MAX('max', {double v1, double v2 -> max(v1, v2)}),
    MIN('min', {double v1, double v2 -> min(v1, v2)}),
    LOG('log', {double v -> log(v)}),
    ABS('abs', {double v -> abs(v)})

    final String name

    final Closure eval

    static final Map nameToFunction = [
            'cos': COS,
            'sin': SIN,
            'tan': TAN,
            'max': MAX,
            'min': MIN,
            'log': LOG,
            'abs': ABS
    ]

    private Function(String name, Closure eval) {
        this.name = name
        this.eval = eval
    }

    static boolean isFunction(String name) {
        nameToFunction.containsKey(name)
    }

    static Function getFunction(String name) {
        nameToFunction[name]
    }
}