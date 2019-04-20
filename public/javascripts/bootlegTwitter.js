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
      .when("/view-profile", {
        templateUrl: "partial/home.html",
        controller: "viewProfileCtrl"
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
    const Comments = $resource(`/api/comments/user/${userName}`);
    const Users = $resource("/api/users");

    Users.query(function(users) {
      console.log(users);
      $scope.users = users;
    });
    Comments.query(function(comments) {
      $scope.comments = comments;
      console.log(comments);
      console.log(userName);
      $scope.username = userName;
    });
    $scope.viewUser = function(username) {
      const UserProfile = $resource(`/api/comments/user/${username}`);
      UserProfile.query(function(comments) {
        $scope.comments = comments;
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
    $scope.viewUser = function(username) {
      const Comments = $resource(`/api/comments/user/jalenRose`);
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
