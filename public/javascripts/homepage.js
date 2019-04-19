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
      .when("/delete-comment/:id", {
        templateUrl: "partial/deleteComment.html",
        controller: "deleteMovieCtrl"
      })
      .otherwise({
        redirectTo: "/"
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
      // eslint-disable-next-line no-const-assign
      console.log("login button clicked");
      // eslint-disable-next-line no-undef
      $scope.username = $scope.userName;
      const Comments = $resource(`/api/comments/user/${$scope.userName}`);
      console.log(Comments);
      Comments.query(function(comments) {
        console.log(comments);
        $scope.comments = comments;
      });
    };
  }
]);

app.controller("deleteMovieCtrl", [
  "$scope",
  "$resource",
  "$location",
  "$routeParams",
  function($scope, $resource, $location, $routeParams) {
    const Comment = $resource("/api/comments/:id");
    Comment.get({ id: $routeParams.id }, function(comment) {
      $scope.comment = comment;
    });
    $scope.delete = function() {
      Comment.delete({ id: $routeParams.id }, function(comment) {
        $location.path("/");
      });
    };
  }
]);
