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
      .when("/add-reply", {
        templateUrl: "partial/home.html",
        controller: "homeCtrl"
      })
      .when("/view-profile", {
        templateUrl: "partial/home.html",
        controller: "followUserCtrl"
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
    const regex = /@[a-z]*/gi;
    // eslint-disable-next-line no-undef
    console.log(`session value: ${sessionStorage.getItem("username")}`);
    // eslint-disable-next-line no-undef
    // $scope.author = sessionStorage.getItem("username");
    $scope.save = function() {
      console.log($scope.comment);
      const mentionedUser = $scope.comment.content.match(regex);
      console.log(`typeof ${mentionedUser} ${mentionedUser}`);
      const Comments = $resource("/api/comments");
      Comments.save(
        {
          author: $scope.comment.author,
          content: $scope.comment.content,
          mention: mentionedUser
        },
        function() {
          $location.path("/home");
        }
      );
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
      $scope.comments = comments;
      console.log(comments);
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
app.controller("addReplyController", [
  "$scope",
  "$resource",
  "$route",
  function($scope, $resource, $route) {
    $scope.addComment = function(commentId, userComment) {
      console.log(`addComment : ${commentId}`);
      console.log(userComment);
      const Collection = $resource(
        `/api/comments/update/:id`,
        { id: "@id" },
        {
          update: { method: "PUT" }
        }
      );
      Collection.update({ id: commentId, reply: userComment }, function() {
        $route.reload();
      });
    };
  }
]);
app.controller("followUserCtrl", [
  "$scope",
  "$resource",
  "$location",
  "$routeParams",
  function($scope, $resource) {
    $scope.viewUser = function(username) {
      const Comments = $resource(`/api/comments/user/${username}`);
      Comments.query(function(comments) {
        console.log(comments);
        $scope.comments = comments;
      });
    };
    // $scope.followUser = function(username) {
    //   const User = $resource(`/api/users/updateUser/${username}`);
    //   User.
    // };
  }
]);
