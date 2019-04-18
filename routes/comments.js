const express = require("express");

const router = express.Router();
const monk = require("monk");

const db = monk("localhost:27017/bootlegTwitter");
// this route expect username params after /
router.get("/:username", function(req, res) {
  const User = db.get("users");
  console.log(`log in function: ${req.params.username}`);
  User.findOne({ username: req.params.username }, function(err, user) {
    if (err) throw err;
    console.log(user);
    res.json(user);
  });
  // const collection = db.get("comments");
  // collection.find({ author: req.params.username }, function(err, comments) {
  //   if (err) throw err;
  //   res.json(comments);
  // });
});
router.post("/", function(req, res) {
  const collection = db.get("comments");
  const date = new Date();
  const currentDate = date.toLocaleString();
  collection.insert(
    {
      author: req.body.author,
      content: req.body.content,
      date: currentDate,
      userMentions: req.body.userMentions
    },
    function(err, comment) {
      if (err) throw err;
      res.json(comment);
    }
  );
});

module.exports = router;
