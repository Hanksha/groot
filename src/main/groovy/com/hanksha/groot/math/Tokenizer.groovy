package com.hanksha.groot.math

/**
 * Created by vivien on 8/20/16.
 */

abstract class Tokenizer {

    private Tokenizer() {}

    static final String REGEX

    static {
        def pattern = '^\\d+$|[+*/%\\^]|-(?!\\d)|,|\\(|\\)|(?![a-z])x(?![a-z])|cos|sin|tan|log|max|min|abs'
        REGEX = "((?<=$pattern)|(?=$pattern))"
    }

    static List<String> tokenize(String input) {
        input.replace(' ', '').toLowerCase().split(REGEX)
    }
}
