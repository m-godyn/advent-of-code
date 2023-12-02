const games = require('./input');

let maxValuesOfCubes = {
    "red": 0,
    "green": 0,
    "blue": 0,
}

function resetMaxValuesOfCubes() {
    Object.keys(maxValuesOfCubes).forEach(key => maxValuesOfCubes[key] = 0);
}

function updateMaxValuesOfCubes(value, color) {
    if (value > maxValuesOfCubes[color]) {
        maxValuesOfCubes[color] = value;
    }
}

let sumOfMultipliedSubsetsCubes = 0;

Object.values(games).forEach(rounds => {
    resetMaxValuesOfCubes();

    rounds.forEach(round => {
        const cubes = round.split(",");
        cubes.forEach(cube => {
            const [value, color] = cube.trim().split(" ");
            updateMaxValuesOfCubes(parseInt(value), color);
        });
    });

    const {red, green, blue} = maxValuesOfCubes;
    sumOfMultipliedSubsetsCubes += red * green * blue;
});

console.log(sumOfMultipliedSubsetsCubes);
