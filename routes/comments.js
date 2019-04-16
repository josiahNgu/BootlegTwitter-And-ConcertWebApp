/* eslint-disable func-names */
const express = require("express");

const router = express.Router;
const monk = require("monk");

const db = monk("localhost:27017/bootlegTwitter");

router.get("/", function(req, res) {
  const collection = db.get("comments");
  collection.find({}, function(err, comments) {
    if (err) throw err;
    res.json(comments);
  });
});
module.exports = router;
