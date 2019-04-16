/* eslint-disable  func-names */

const express = require("express");

const router = express.Router();

/* GET home page. */
router.get("/", function(req, res) {
  res.render("layout");
});

module.exports = router;
