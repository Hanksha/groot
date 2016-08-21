package com.hanksha.groot.math

import groovy.transform.ToString

import static com.hanksha.groot.math.Operator.*
import static com.hanksha.groot.math.Function.*

/**
 * Created by vivien on 8/20/16.
 */
class Expression {

    double x

    List<Token> output

    Expression(String input) {
        def tokens = Tokenizer.tokenize(input)

        def opStack = new Stack()

        output = []

        for(token in tokens) {
            // if it's a number add to output
            if(token.isNumber())
                output << new NumberToken(token.toDouble())
            // if it's the variable x add to output
            else if(token == 'x')
                output << new VarToken()
            // if if it's a closing parenthesis
            else if(token == ')') {
                def op
                // pop the operators/functions from the op stack and add them to the output
                // until it reaches the opening parenthesis
                while((op = opStack.pop()) != '(') {
                    if(isOperator(op))
                        output << new OperatorToken(getOperator(op))
                    else if(isFunction(op))
                        output << new FunctionToken(getFunction(op))
                }
            }
            // if it's a opening parenthesis add to the op stack
            else if(token == '(')
                opStack.push(token)
            // if it's a function just push to the stack
            else if(isFunction(token))
                opStack.push(token)
            // if the token is an operator (o1)
            else if(isOperator(token)) {
                if(!opStack.isEmpty()) {

                    // if the top of the stack is a function, pop it and add to output
                    if(isFunction(opStack.peek()))
                        output << new FunctionToken(getFunction(opStack.pop()))

                    // if there is an operator (o2) on the top of the op stack
                    // if o1 is left associative and less or equal precedence than 02
                    // or if O1 is right associative and less precedence than o2
                    // pop o2 and add it to the output
                    if(isOperator(opStack.peek())) {
                        def op
                        def lastOp
                        while(!opStack.isEmpty() && isOperator(opStack.peek())) {
                            op = getOperator(token)
                            lastOp = getOperator(opStack.peek())
                            if((op.leftAssoc && op.precedence <= lastOp.precedence) ||
                                (!op.leftAssoc && op.precedence < lastOp.precedence)) {
                                output << new OperatorToken(lastOp)
                                opStack.pop()
                            }
                            else
                                break
                        }
                    }
                }

                // push the token to the op stack
                opStack.push(token)
            }

            println token
            println output
            println opStack
        }

        // when no more token add the remaining operators in the op stack to the output
        def op
        while(!opStack.isEmpty()) {
            op = opStack.pop()
            if(isOperator(op))
                output << new OperatorToken(getOperator(op))
            else if(isFunction(op))
                output << new FunctionToken(getFunction(op))
        }

        println output
    }

    double eval(double x) {
        this.x = x

        Stack stack = new Stack()

        for(token in output) {
            println "Operation: $token"
            token.operate(stack)
            println stack
        }

        stack.pop()
    }

    private interface Token {
        void operate(Stack stack)
    }

    private class OperatorToken implements Token {
        Operator operator

        OperatorToken(Operator operator) {
            this.operator = operator
        }

        void operate(Stack stack) {
            def arg2 = stack.pop()
            def arg1 = stack.pop()

            stack.push(operator.eval(arg1, arg2))
        }

        @Override
        String toString() {
            operator.symbol
        }
    }

    private class FunctionToken implements Token {
        Function function

        FunctionToken(Function function) {
            this.function = function
        }

        void operate(Stack stack) {
            def args = []

            1.upto(function.eval.maximumNumberOfParameters) {args << stack.pop()}

            stack.push(function.eval(*args.reverse()))
        }

        @Override
        String toString() {
            function.name
        }
    }

    private class NumberToken implements Token {

        double value

        NumberToken(double value) {
            this.value = value
        }

        void operate(Stack stack) {
            stack.push(value)
        }

        @Override
        String toString() {
            value.toString()
        }
    }

    private class VarToken implements Token {
        void operate(Stack stack) {
            stack.push(x)
        }

        @Override
        String toString() {
            "x = $x"
        }
    }
}
