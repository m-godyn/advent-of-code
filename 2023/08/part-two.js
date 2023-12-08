const {directions, networkMap} = require('./input');

function findGCD(a, b) {
    return b === 0 ? a : findGCD(b, a % b);
}

function findLCM(arr) {
    if (arr.length < 2) {
        return arr[0] || 0;
    }

    let lcm = arr[0];

    for (let i = 1; i < arr.length; i++) {
        const currentNumber = arr[i];
        const currentGCD = findGCD(lcm, currentNumber);
        lcm = (lcm * currentNumber) / currentGCD;
    }

    return lcm;
}

const startingPoint = [...networkMap.keys()].filter(direction => direction.charAt(direction.length - 1) === 'A');
let currentPoint = startingPoint;
let steps4All = [];

currentPoint.forEach(point => {
    let isEndingPointReached = false;
    let steps = 0;

    while (!isEndingPointReached) {
        for (let direction of directions) {
            point = networkMap.get(point)[direction]
            steps++;
            if (point.charAt(point.length - 1) === 'Z') {
                isEndingPointReached = true;
                break;
            }
        }
    }

    steps4All.push(steps);
});

console.log(
    findLCM(steps4All)
);
