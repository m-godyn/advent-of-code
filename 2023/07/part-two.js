const input = require('./input');

const sortOrder = ['A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'];
let fiveOfKind = [];
let fourOfKind = [];
let fullHouse = [];
let threeOfKind = [];
let twoPair = [];
let onePair = [];
let highCard = [];

function recognizeHand(index) {
    let hand = input[index][0].split('');
    let handMap = new Map();

    hand.forEach(card => {
        if (handMap.has(card)) {
            handMap.set(card, handMap.get(card) + 1);
        } else {
            handMap.set(card, 1);
        }
    });

    switch (handMap.size) {
        case 1:
            fiveOfKind.push(index);
            break;
        case 2:
            if (handMap.has('J')) {
                fiveOfKind.push(index);
            } else {
                Math.max(...handMap.values()) === 4 ? fourOfKind.push(index) : fullHouse.push(index);
            }
            break;
        case 3:
            if (handMap.has('J')) {
                if ([2, 3].includes(handMap.get('J'))) {
                    fourOfKind.push(index);
                } else if (Math.max(...handMap.values()) === 3) {
                    fourOfKind.push(index);
                } else {
                    fullHouse.push(index);
                }
            } else {
                Math.max(...handMap.values()) === 3 ? threeOfKind.push(index) : twoPair.push(index);
            }
            break;
        case 4:
            if (handMap.has('J')) {
                threeOfKind.push(index);
            } else {
                onePair.push(index);
            }
            break;
        case 5:
            if (handMap.has('J')) {
                onePair.push(index);
            } else {
                highCard.push(index);
            }
            break;
    }
}

function sortByHandStrength(array) {
    array.sort((a, b) => {
        const handA = input[a][0];
        const handB = input[b][0];

        for (let i = 0; i < Math.min(handA.length, handB.length); i++) {
            const cardA = handA.charAt(i);
            const cardB = handB.charAt(i);

            if (cardA !== cardB) {
                return sortOrder.indexOf(cardB) - sortOrder.indexOf(cardA);
            }
        }
        return a - b;
    });
}

for (let i = 0; i < input.length; i++) {
    recognizeHand(i);
}

let totalWinnings = 0;
let rank = 1;

[
    highCard,
    onePair,
    twoPair,
    threeOfKind,
    fullHouse,
    fourOfKind,
    fiveOfKind,
]
    .filter(type => type.length !== 0)
    .forEach(type => {
        sortByHandStrength(type);
        type.forEach(hand => {
            totalWinnings += parseInt(input[hand][1]) * rank;
            rank++;
        })

    });

console.log(totalWinnings);
