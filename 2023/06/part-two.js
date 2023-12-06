const input = require('./input');

const concatenatedNumbers = input.map(innerArray => parseInt(innerArray.join('')));
const time = concatenatedNumbers[0];
const distance = concatenatedNumbers[1];

let waysToWinCount = 0;

for (let buttonTime = 1; buttonTime < time; buttonTime++) {
    const remainingTime = time - buttonTime;
    const winningDistance = buttonTime * remainingTime;

    if (winningDistance > distance) {
        waysToWinCount++;
    }
}

console.log(waysToWinCount);
