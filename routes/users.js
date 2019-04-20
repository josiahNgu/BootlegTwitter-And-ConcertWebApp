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
  console.log("empty");
  collection.find({ username: req.params.username }, function(err, users) {
    if (err) throw err;
    if (users.length === 0) {
      console.log("empty");
    }
    res.json(users);
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
        favorites: req.body.postId
      }
    },
    function(err) {
      if (err) throw err;
    }
  );
});

module.exports = router;
