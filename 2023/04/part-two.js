const scratchcards = require('./input');

let cardInstances = 0;
let copies = new Map();
let currentCard = 1;

for (i = 1; i <= scratchcards.length; i++) {
    copies.set(i, 1);
}

for (const scratchcard of scratchcards) {
    let copiesValue = copies.get(currentCard);
    while (copiesValue !== 0) {
        cardInstances++;
        copies.set(currentCard, copiesValue - 1);

        let copiesWon = 0;
        scratchcard[1].forEach(number => {
            if (scratchcard[0].includes(number)) {
                copiesWon++;
            }
        });

        for (let i = copiesWon; i > 0; i--) {
            copies.set(currentCard + i, copies.get(currentCard + i) + 1);
        }

        copiesValue = copies.get(currentCard);
    }
    currentCard++;
}

console.log(cardInstances);