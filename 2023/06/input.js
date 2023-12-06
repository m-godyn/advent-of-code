const path = require("path");
const fs = require("fs");

const input = fs
    .readFileSync(path.join(__dirname, "input.txt"), "utf-8")
    .toString()
    .trim()
    .split("\n")
    .map(race => race.split(" ").filter(value => !isNaN(parseInt(value))).map(Number));

module.exports = input;
