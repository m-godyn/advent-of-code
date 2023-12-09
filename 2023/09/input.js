const path = require("path");
const fs = require("fs");

const oasis = fs
    .readFileSync(path.join(__dirname, "input.txt"), "utf-8")
    .toString()
    .trim()
    .split("\n")
    .map(value => value.split(" ").map(Number));

module.exports = oasis;
