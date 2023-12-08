const {directions, networkMap} = require('./input');

const startingPoint = 'AAA';
const endingPoint = 'ZZZ';
let currentPoint = startingPoint;
let steps = 0;
const isEndingPointReached = () => currentPoint === endingPoint;

while (!isEndingPointReached()) {
    for (let direction of directions) {
        currentPoint = networkMap.get(currentPoint)[direction];
        steps++;
        if (isEndingPointReached()) {
            break;
        }
    }
}

console.log(steps);
