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

module.exports = router;
