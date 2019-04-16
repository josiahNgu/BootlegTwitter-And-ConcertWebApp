// eslint-disable-next-line no-undef
const app = angular.module("bootlegTwitter", ["ngResource", "ngRoute"]);

app.config([
  "$routeProvider",
  function($routeProvider) {
    $routeProvider
      .when("/", {
        templateUrl: "partial/home.html",
        controller: "addCommentCtrl"
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
app.controller("loginCtrl", [
  "$scope",
  "$resource",
  "$location",
  function($scope, $resource, $location) {
    $scope.save = function() {
      console.log("login button clicked");
      console.log($scope.userName);
    };
  }
]);

app.controller("addCommentCtrl", [
  "$scope",
  "$resource",
  "$location",
  function($scope, $resource, $location) {
    const Comments = $resource("/api/comments");
    Comments.query(function(comments) {
      console.log(comments);
      $scope.comments = comments;
    });
    $scope.save = function() {
      console.log("comment button clicked");
      if ($scope.comment.content) {
        Comments.save($scope.comment, function() {
          console.log($scope.comment.content);
          console.log($scope.comment.author);
          $location.path("/");
        });
      }
    };
  }
]);
