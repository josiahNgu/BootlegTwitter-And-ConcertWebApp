// eslint-disable-next-line no-undef
const app = angular.module("bootlegTwitter", ["ngResource", "ngRoute"]);
app.config([
  "$routeProvider",
  function($routeProvider) {
    $routeProvider
      .when("/", {
        templateUrl: "partial/login.html",
        controller: "loginCtrl"
      })
      .when("/home", {
        templateUrl: "partial/home.html",
        controller: "homeCtrl"
      })
      .when("/add-comment", {
        templateUrl: "partial/addComment.html",
        controller: "addCommentCtrl"
      })
      .when("/delete-comment/:id", {
        templateUrl: "partial/deleteComment.html",
        controller: "deleteCommentCtrl"
      })
      .when("/edit-comment", {
        templateUrl: "partial/deleteComment.html",
        controller: "EditCommentCtrl"
      })
      .otherwise({
        redirectTo: "/login"
      });
  }
]);

app.controller("addCommentCtrl", [
  "$scope",
  "$resource",
  "$location",
  function($scope, $resource, $location) {
    // eslint-disable-next-line no-undef
    console.log(`session value ${sessionStorage.getItem("username")}`);
    // eslint-disable-next-line no-undef
    // $scope.author = sessionStorage.getItem("username");
    $scope.save = function() {
      console.log("comment button clicked");
      // eslint-disable-next-line no-undef
      const Comments = $resource("/api/comments");
      Comments.save($scope.comment, function() {
        $location.path("/home");
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
      sessionStorage.clear();
      // eslint-disable-next-line no-undef
      sessionStorage.setItem("username", $scope.userName);
      // eslint-disable-next-line no-undef
      console.log(sessionStorage.getItem("username"));
      // const userExist = $resource(`/api/users/${$scope.userName}`);
      // userExist.query(function(users) {
      //   if (users === undefined) {
      //     console.log("empty");
      //   }
      // });
    };
  }
]);
app.controller("homeCtrl", [
  "$scope",
  "$resource",
  "$location",
  "$routeParams",
  function($scope, $resource) {
    // eslint-disable-next-line no-undef
    const userName = sessionStorage.getItem("username");
    console.log(userName);
    $scope.username = userName;
    const Users = $resource("/api/users");
    Users.query(function(users) {
      console.log(users);
      $scope.users = users;
    });
    const Comments = $resource(`/api/comments/user/${userName}`);
    Comments.query(function(comments) {
      console.log(comments);
      $scope.comments = comments;
    });
  }
]);
app.controller("deleteCommentCtrl", [
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
      Comment.delete({ id: $routeParams.id }, function() {
        $location.path("/home");
      });
    };
  }
]);
app.controller("followUserCtrl", [
  "$scope",
  "$resource",
  "$location",
  "$routeParams",
  function($scope, $resource, $location, $routeParams) {
    $scope.viewUser = function(username) {
      const Comments = $resource(`/api/comments/user/${username}`);
      Comments.query(function(comments) {
        console.log(comments);
        // $scope.comments = comments;
      });
    };
  }
]);
