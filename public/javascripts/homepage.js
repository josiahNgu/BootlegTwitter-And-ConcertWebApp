// eslint-disable-next-line no-undef
const app = angular.module("bootlegTwitter", ["ngResource", "ngRoute"]);

app.config([
  "$routeProvider",
  function($routeProvider) {
    $routeProvider
      .when("/", {
        templateUrl: "partial/home.html",
        controller: "loginCtrl"
      })
      .when("/add-comment", {
        templateUrl: "partial/addComment.html",
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

app.controller("addCommentCtrl", [
  "$scope",
  "$resource",
  "$location",
  function($scope, $resource, $location) {
    $scope.save = function() {
      console.log("comment button clicked");
      const Comments = $resource("/api/comments");
      Comments.save($scope.comment, function() {
        console.log($scope.comment.author);
        $location.path("/");
      });
    };
  }
]);

app.controller("loginCtrl", [
  "$scope",
  "$resource",
  "$location",
  "$routeParams",
  function($scope, $resource) {
    $scope.save = function() {
      console.log("login button clicked");
      // eslint-disable-next-line no-undef
      $scope.username = $scope.userName;
      const Comments = $resource(`/api/comments/${$scope.username}`);
      console.log(Comments);
      Comments.query(function(comments) {
        console.log(comments);
        $scope.comments = comments;
      });
    };
  }
]);
