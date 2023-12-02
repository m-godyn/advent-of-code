const path = require("path");
const fs = require("fs");

const input = fs
    .readFileSync(path.join(__dirname, "input.txt"), "utf-8")
    .toString()
    .trim()
    .split("\n");

const games = {};

input.forEach(entry => {
    let [gameId, rounds] = entry.split(":");
    gameId = gameId.replace(/\D/g, '');
    games[gameId] = rounds.split(";");
});

module.exports = games;
