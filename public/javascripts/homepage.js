/* eslint-disable func-names */

// eslint-disable-next-line no-undef
const app = angular.module("bootlegTwitter", ["ngResource", "ngRoute"]);

app.config([
  "$routeProvider",
  function($routeProvider) {
    $routeProvider
      .when("/", {
        templateUrl: "viewControllers/home.html",
        controller: "CommentCtrl"
      })
      .otherwise({
        redirectTo: "/"
      });
  }
]);

app.controller("CommentCtrl", [
  "$scope",
  "$resource",
  function($scope, $resource) {
    const Comments = $resource("/api/comments");
    Comments.query(function(comments) {
      console.log(comments);
      $scope.comments = comments;
    });
  }
]);
