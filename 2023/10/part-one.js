const input = require('./input');

const pipes = {
    '.': {'north': false, 'south': false, 'east': false, 'west': false,},
    '|': {'north': true, 'south': true, 'east': false, 'west': false,},
    '-': {'north': false, 'south': false, 'east': true, 'west': true,},
    'L': {'north': true, 'south': false, 'east': true, 'west': false,},
    'J': {'north': true, 'south': false, 'east': false, 'west': true,},
    '7': {'north': false, 'south': true, 'east': false, 'west': true,},
    'F': {'north': false, 'south': true, 'east': true, 'west': false,},
    'S': {'north': true, 'south': true, 'east': true, 'west': true,},
};

function checkDirection(i, j, direction) {
    const pipe = pipes[input[i][j]];
    switch (direction) {
        case 'north':
            return pipe['north'] === true && pipes[input[i - 1][j]]['south'] === true;
        case 'south':
            return pipe['south'] === true && pipes[input[i + 1][j]]['north'] === true;
        case 'east':
            return pipe['east'] === true && pipes[input[i][j + 1]]['west'] === true;
        case 'west':
            return pipe['west'] === true && pipes[input[i][j - 1]]['east'] === true;
        default:
            return false;
    }
}

function findStartCoords() {
    for (let i = 0; i < input.length; i++) {
        const j = input[i].indexOf('S');
        if (j !== -1) {
            return [i, j];
        }
    }
    return [];
}

let loopNotFound = true;
let coords = findStartCoords();
let steps = 0;
let previousCoords = [-1, -1];

while (loopNotFound) {
    if (checkDirection(coords[0], coords[1], 'north') && coords[0] - 1 !== previousCoords[0]) {
        steps++;
        previousCoords = [...coords];
        coords[0]--;
    } else if (checkDirection(coords[0], coords[1], 'south') && coords[0] + 1 !== previousCoords[0]) {
        steps++;
        previousCoords = [...coords];
        coords[0]++;
    } else if (checkDirection(coords[0], coords[1], 'west') && coords[1] - 1 !== previousCoords[1]) {
        steps++;
        previousCoords = [...coords];
        coords[1]--;
    } else if (checkDirection(coords[0], coords[1], 'east') && coords[1] + 1 !== previousCoords[1]) {
        steps++;
        previousCoords = [...coords];
        coords[1]++;
    } else {
        console.error("No pipe is connected");
        loopNotFound = false;
        break;
    }

    if (input[coords[0]][coords[1]] === 'S') {
        loopNotFound = false;
        steps /= 2;
    }
}

console.log(steps);
