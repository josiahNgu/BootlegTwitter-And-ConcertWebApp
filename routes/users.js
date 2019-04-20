const express = require("express");

const router = express.Router();
const monk = require("monk");

const db = monk("localhost:27017/bootlegTwitter");
/* GET users listing. */
router.get("/", function(req, res) {
  const collection = db.get("users");
  collection.find({}, function(err, users) {
    if (err) throw err;
    res.json(users);
  });
});
router.get("/:username", function(req, res) {
  const collection = db.get("users");
  collection.find({ username: req.params.username }, function(err, users) {
    if (err) throw err;
    if (users === undefined) {
      return false;
    }
  });
});

router.post("/", function(req) {
  const collection = db.get("users");
  console.log(`router.post: ${req.body}`);
  collection.insert({
    username: req.body.username,
    following: [],
    favorited: []
  });
});
router.put("/updateUserFavorite", function(req) {
  const collection = db.get("users");
  collection.update(
    {
      username: req.body.username
    },
    {
      $push: {
        favorited: req.body.postId
      }
    },
    function(err) {
      if (err) throw err;
    }
  );
});
router.put("/updateFollowing", function(req) {
  const collection = db.get("users");
  collection.update(
    {
      username: req.body.username
    },
    {
      $push: {
        following: req.body.followingName
      }
    },
    function(err) {
      if (err) throw err;
    }
  );
});

module.exports = router;
