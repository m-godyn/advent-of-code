const path = require("path");
const fs = require("fs");

const input = fs
    .readFileSync(path.join(__dirname, "input.txt"), "utf-8")
    .toString()
    .trim()
    .split("\n\n");

const directions = input[0].split("");
const networkMap = new Map();

const temp = input[1]
    .split("\n")
    .map(input => input.split(" = "))

for (let i = 0; i < temp.length; i++) {
    temp[i][1] = temp[i][1]
        .split(", ")
        .map(item => item.replace(/[()]/g, ''));

    networkMap.set(
        temp[i][0],
        {
            'L': temp[i][1][0],
            'R': temp[i][1][1],
        },
    );
}

module.exports = {
    directions,
    networkMap,
};
