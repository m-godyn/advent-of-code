const input = require('./input');

const racesCount = input[0].length;
let waysToWin = 1;

for (let race = 0; race < racesCount; race++) {
    const time = input[0][race];
    const distance = input[1][race];

    let waysToWinCount = 0;

    for (let buttonTime = 1; buttonTime < time; buttonTime++) {
        const remainingTime = time - buttonTime;
        const winningDistance = buttonTime * remainingTime;

        if (winningDistance > distance) {
            waysToWinCount++;
        }
    }

    waysToWin *= waysToWinCount;
}

console.log(waysToWin);
