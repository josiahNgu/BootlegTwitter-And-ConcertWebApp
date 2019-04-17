const express = require("express");

const router = express.Router();
const monk = require("monk");

const db = monk("localhost:27017/bootlegTwitter");

router.get("/", function(req, res) {
  const collection = db.get("comments");
  collection.find({}, function(err, comments) {
    if (err) throw err;
    res.json(comments);
  });
});
router.post("/", function(req, res) {
  const collection = db.get("comments");
  const date = new Date();
  const currentDate = date.toLocaleString();
  collection.insert(
    {
      // author: req.body.author,
      // eslint-disable-next-line no-undef
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
