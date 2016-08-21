package com.hanksha.groot.math

/**
 * Created by vivien on 8/20/16.
 */

enum Operator {

    PLUS('+', 2, true, { double v1, double v2 ->  v1 + v2}),
    MINUS('-', 2, true, { double v1, double v2 ->  v1 - v2}),
    TIMES('*', 3, true, { double v1, double v2 ->  v1 * v2}),
    DIVIDE('/', 3, true, { double v1, double v2 ->  v1 / v2}),
    MOD('%', 3, true, { double v1, double v2 ->  v1 % v2}),
    POWER('^', 4, false, { double v1, double v2 ->  v1.power(v2)})

    final String symbol

    final int precedence

    final boolean leftAssoc

    final Closure eval

    static final Map symbolToOperator = [
            '+': PLUS,
            '-': MINUS,
            '*': TIMES,
            '/': DIVIDE,
            '%': MOD,
            '^': POWER
    ]

    private Operator(String symbol, int precedence, boolean leftAssoc, Closure eval) {
        this.symbol = symbol
        this.precedence = precedence
        this.leftAssoc = leftAssoc
        this.eval = eval
    }

    static boolean isOperator(String symbol) {
        symbolToOperator.containsKey(symbol)
    }

    static Operator getOperator(String symbol) {
        if(!symbolToOperator[symbol])
            println 'null ' + symbol

        symbolToOperator[symbol]
    }
}