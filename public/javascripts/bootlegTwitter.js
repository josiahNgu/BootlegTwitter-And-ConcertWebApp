/* eslint-disable no-undef */
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
      .when("/register", {
        templateUrl: "partial/register.html",
        controller: "registerUserCtrl"
      })
      .when("/view-notfications/:id", {
        templateUrl: "partial/notification.html",
        controller: "notificationCtrl"
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
        redirectTo: "/"
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
    $scope.username = sessionStorage.getItem("username");
    $scope.save = function() {
      console.log($scope.comment);
      const mentionedUser = $scope.comment.content.match(regex);
      console.log(`typeof ${mentionedUser} ${mentionedUser}`);
      const Comments = $resource("/api/comments");
      Comments.save(
        {
          author: $scope.username,
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
  function($scope) {
    $scope.save = function() {
      console.log("login button clicked");
      // eslint-disable-next-line no-undef
      sessionStorage.clear();
      // eslint-disable-next-line no-undef
      sessionStorage.setItem("username", $scope.userName);
      // eslint-disable-next-line no-undef
      console.log(sessionStorage.getItem("username"));
    };
  }
]);
app.controller("registerUserCtrl", [
  "$scope",
  "$resource",
  "$location",
  "$routeParams",
  function($scope, $resource, $location) {
    $scope.save = function() {
      console.log("register button clicked");
      const Users = $resource("/api/users");
      Users.save(
        {
          username: $scope.username
        },
        function() {
          $location.path("/");
        }
      );
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
    const Comments = $resource(`/api/comments/user/${userName}`);
    $scope.comments = {};
    const Users = $resource("/api/users");
    Users.query(function(users) {
      console.log(users);
      // eslint-disable-next-line no-plusplus
      $scope.users = users;
    });
    $scope.goHome = function() {
      const username = sessionStorage.getItem("username");
      const UserProfile = $resource(`/api/comments/user/${username}`);
      UserProfile.query(function(comments) {
        $scope.comments.properties = comments;
        console.log(comments);
      });
    };
    Comments.query(function(comments) {
      $scope.comments.properties = comments;
      console.log(comments);
      console.log(userName);
      $scope.username = userName;
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
    $scope.delete = function(author) {
      if (sessionStorage.getItem("username") === author) {
        Comment.delete({ id: $routeParams.id }, function() {
          $location.path("/home");
        });
      } else {
        console.log("not authorized");
        $scope.alertCondition = true;
      }
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
app.controller("addToFavoriteCtrl", [
  "$scope",
  "$resource",
  "$location",
  "$routeParams",
  function($scope, $resource, $location) {
    $scope.save = function(postId) {
      // eslint-disable-next-line no-undef
      const username = sessionStorage.getItem("username");
      console.log(`${username} favorited: ${postId}`);
      // eslint-disable-next-line no-plusplus
      $scope.comment.favorited++;

      const User = $resource(
        "/api/users/updateUserFavorite",
        { id: "@id" },
        {
          update: { method: "PUT" }
        }
      );
      const Comment = $resource(
        "/api/comments/updateFavoriteNumber",
        { id: "@id" },
        {
          update: { method: "PUT" }
        }
      );
      Comment.update({ postId });
      User.update({ postId, username }, function() {
        $location.path("/home");
      });
    };
  }
]);
app.controller("viewProfileCtrl", [
  "$scope",
  "$resource",
  "$location",
  "$routeParams",
  function($scope, $resource) {
    $scope.username = sessionStorage.getItem("username");
    $scope.viewUser = function(username) {
      const UserProfile = $resource(`/api/comments/profile/${username}`);
      UserProfile.query(function(comments) {
        $scope.comments.properties = comments;
        console.log(comments);
      });
    };
    $scope.followUser = function(followingName) {
      const username = sessionStorage.getItem("username");
      const User = $resource(
        "/api/users/updateFollowing",
        { id: "@id" },
        {
          update: { method: "PUT" }
        }
      );
      User.update({ username, followingName });
    };
  }
]);
app.controller("notificationCtrl", [
  "$scope",
  "$resource",
  "$location",
  "$routeParams",
  function($scope, $resource) {
    const username = sessionStorage.getItem("username");
    $scope.username = username;
    const Comments = $resource(`/api/comments/userNotifications/${username}`);
    Comments.query(function(comments) {
      $scope.comments = comments;
      console.log(comments);
    });
  }
]);
