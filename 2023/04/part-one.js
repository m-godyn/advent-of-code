const scratchcards = require('./input');

const pileValue = scratchcards.reduce((sum, scratchcard) => {
    let points = 0;
    scratchcard[1].forEach(number => {
        if (scratchcard[0].includes(number)) {
            points === 0 ? points = 1 : points *= 2;
        }
    });
    return sum + points;
}, 0);

console.log(pileValue);
