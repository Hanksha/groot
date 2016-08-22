(function () {

    var app = angular.module('grootApp', []);

    app.controller('MainCtrl', function ($scope, $log, RootService) {

        $scope.model = {
            func: 'x - cos(x)',
            x0: -10,
            x1: 10,
            algo: 'bisection',
            options: {
                roundingMethod: 'roundOff'
            }
        };

        function onSuccess(data) {
            $log.debug(data);
            $scope.root = data.root;
            $scope.steps = data.steps;
        }

        $scope.execute = function () {
            switch ($scope.model.algo) {
                case 'bisection':
                    RootService.bisection($scope.model.func, $scope.model.x0, $scope.model.x1, $scope.model.options).success(onSuccess);
                    break;
                case 'newtonraphson':
                    RootService.newtonraphson($scope.model.func, $scope.model.x0, $scope.model.options).success(onSuccess);
                    break;
                case 'regulafalsi':
                    RootService.regulafalsi($scope.model.func, $scope.model.x0, $scope.model.x1, $scope.model.options).success(onSuccess);
                    break;
                case 'secant':
                    RootService.secant($scope.model.func, $scope.model.x0, $scope.model.x1, $scope.model.options).success(onSuccess);
                    break;
                default:
                    break;
            }
        };

        $scope.clear = function () {
            $scope.model = {};
            $scope.steps = null;
            $scope.root = null;
        }

    });

    app.factory('RootService', function ($http) {

        return {
            bisection: bisection,
            regulafalsi: regulafalsi,
            secant: secant,
            newtonraphson: newtonraphson
        };

        function bisection(func, x0, x1, options) {
            return $http.get('/groot/bisection', {
                params: {
                    func: func, x0: x0, x1: x1,
                    maxIter: options.maxIter, precision: options.precision,
                    interval: options.interval, roundingMethod: options.roundingMethod
                }
            });
        }

        function regulafalsi(func, x0, x1, options) {
            return $http.get('/groot/regula-falsi', {
                params: {
                    func: func, x0: x0, x1: x1,
                    maxIter: options.maxIter, precision: options.precision,
                    interval: options.interval, roundingMethod: options.roundingMethod
                }
            });
        }

        function secant(func, x0, x1, options) {
            return $http.get('/groot/secant', {
                params: {
                    func: func, x0: x0, x1: x1,
                    maxIter: options.maxIter, precision: options.precision,
                    interval: options.interval, roundingMethod: options.roundingMethod
                }
            });
        }

        function newtonraphson(func, x, options) {
            return $http.get('/groot/newton-raphson', {
                params: {
                    func: func, x: x,
                    maxIter: options.maxIter, precision: options.precision,
                    interval: options.interval, roundingMethod: options.roundingMethod
                }
            });
        }
    });

})();
