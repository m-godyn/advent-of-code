const path = require("path");
const fs = require("fs");

const input = fs
    .readFileSync(path.join(__dirname, "input.txt"), "utf-8")
    .toString()
    .trim()
    .split("\n");

for (let i = 0; i < input.length; i++) {
    input[i] = ('.' + input[i] + '.').split('');
}

module.exports = input;
