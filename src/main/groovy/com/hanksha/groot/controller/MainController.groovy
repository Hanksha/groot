package com.hanksha.groot.controller

import com.hanksha.groot.math.Expression
import com.hanksha.groot.math.algorithm.BisectionApproximator
import com.hanksha.groot.math.algorithm.RegulaFalsiApproximator
import com.hanksha.groot.math.algorithm.SecantApproximator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by vivien on 8/20/16.
 */

@RestController
@RequestMapping('/groot')
class MainController {

    @RequestMapping(value = '/bisection', method = RequestMethod.GET)
    ResponseEntity bisection(
            @RequestParam String func, @RequestParam double x0, @RequestParam double x1,
            @RequestParam(required = false) Integer maxIter,
            @RequestParam(required = false) Integer precision,
            @RequestParam(required = false) Double interval,
            @RequestParam(required = false) String roundingMethod) {

        def options = [:]

        if(maxIter) options.maxIter = maxIter
        if(precision) options.precision = precision
        if(interval) options.interval = interval
        if(roundingMethod) options.roundingMethod = roundingMethod

        def rootApp = BisectionApproximator.getRoot(new Expression(func), x0, x1, options)

        new ResponseEntity(rootApp, HttpStatus.OK)
    }

    @RequestMapping(value = '/newton-raphson', method = RequestMethod.GET)
    ResponseEntity newtonRaphson(@RequestParam String func, @RequestParam double x0, @RequestParam double x1) {

    }

    @RequestMapping(value = '/regula-falsi', method = RequestMethod.GET)
    ResponseEntity regulaFalsi(
            @RequestParam String func, @RequestParam double x0, @RequestParam double x1,
            @RequestParam(required = false) Integer maxIter,
            @RequestParam(required = false) Integer precision,
            @RequestParam(required = false) Double interval,
            @RequestParam(required = false) String roundingMethod) {

        def options = [:]

        if(maxIter) options.maxIter = maxIter
        if(precision) options.precision = precision
        if(interval) options.interval = interval
        if(roundingMethod) options.roundingMethod = roundingMethod

        def rootApp = RegulaFalsiApproximator.getRoot(new Expression(func), x0, x1, options)

        new ResponseEntity(rootApp, HttpStatus.OK)
    }

    @RequestMapping(value = '/secant', method = RequestMethod.GET)
    ResponseEntity secant(
            @RequestParam String func, @RequestParam double x0, @RequestParam double x1,
            @RequestParam(required = false) Integer maxIter,
            @RequestParam(required = false) Integer precision,
            @RequestParam(required = false) Double interval,
            @RequestParam(required = false) String roundingMethod) {

        def options = [:]

        if(maxIter) options.maxIter = maxIter
        if(precision) options.precision = precision
        if(interval) options.interval = interval
        if(roundingMethod) options.roundingMethod = roundingMethod

        def rootApp = SecantApproximator.getRoot(new Expression(func), x0, x1, options)

        new ResponseEntity(rootApp, HttpStatus.OK)
    }

}
