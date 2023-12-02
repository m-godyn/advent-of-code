const games = require('./input');
const MAX_RED_CUBES_VALUE = 12;
const MAX_GREEN_CUBES_VALUE = 13;
const MAX_BLUE_CUBES_VALUE = 14;

let validGames = [];

function isValidRound(value, color) {
    switch (color) {
        case "red":
            return value <= MAX_RED_CUBES_VALUE;
        case "green":
            return value <= MAX_GREEN_CUBES_VALUE;
        case "blue":
            return value <= MAX_BLUE_CUBES_VALUE;
        default:
            return false;
    }
}

Object.keys(games).forEach(game => {
    const isValid = games[game].every(round => {
        const cubes = round.split(",");

        return cubes.every(cube => {
            const [value, color] = cube.trim().split(" ");
            return isValidRound(value, color);
        });
    });

    if (isValid) {
        validGames.push(parseInt(game));
    }
});

console.log(
    validGames.reduce((sum, gameId) => sum + gameId, 0)
);
