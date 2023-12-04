const path = require("path");
const fs = require("fs");

const input = fs
    .readFileSync(path.join(__dirname, "input.txt"), "utf-8")
    .toString()
    .trim()
    .split("\n");

let scratchcards = [];

input.forEach(entry => {
    const [winningNumbers, ownedNumbers] = entry.split(":")[1].trim().split("|");
    scratchcards.push(
        [
            winningNumbers.trim().split(" ").filter(number => number !== ''),
            ownedNumbers.trim().split(" ").filter(number => number !== ''),
        ]
    );
});

module.exports = scratchcards;
