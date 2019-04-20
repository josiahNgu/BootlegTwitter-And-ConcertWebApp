const express = require("express");
const objectid = require("mongodb").ObjectID;

const router = express.Router();
const monk = require("monk");

const db = monk("localhost:27017/bootlegTwitter");
// this route expect username params after /
router.get("/user/:username", function(req, res) {
  const Comment = db.get("comments");
  console.log(`log in function in comments.js: ${req.params.username}`);
  Comment.find({ author: req.params.username }, function(err, comments) {
    if (err) throw err;
    console.log(comments.author);
    res.json(comments);
  });
});

router.get("/:id", function(req, res) {
  const collection = db.get("comments");
  collection.findOne({ _id: objectid(req.params.id) }, function(err, comment) {
    if (err) throw err;
    res.json(comment);
  });
});
router.delete("/:id", function(req, res) {
  const collection = db.get("comments");
  collection.remove({ _id: objectid(req.params.id) }, function(err, comment) {
    if (err) throw err;
    res.json(comment);
  });
});

router.post("/", function(req, res) {
  const collection = db.get("comments");
  const date = new Date();
  console.log(`router.post: ${req.body}`);
  const currentDate = date.toLocaleString();
  collection.insert(
    {
      author: req.body.author,
      content: req.body.content,
      date: currentDate,
      userMentions: req.body.mention
    },
    function(err, comment) {
      if (err) throw err;
      res.json(comment);
    }
  );
});
router.put("/update/:id", function(req, res) {
  console.log("update a comment");
  const collection = db.get("comments");
  collection.update(
    {
      _id: req.params.id
    },
    {
      $push: {
        replies: req.body.reply
      }
    },
    function(err, updatedComment) {
      if (err) throw err;
      res.json(updatedComment);
    }
  );
});
module.exports = router;
