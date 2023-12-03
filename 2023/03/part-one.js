const input = require('./input');

function isNumeric(value) {
    return /^[^.]?\d+[^.]?$/.test(value);
}

function isSymbol(value) {
    return (value !== '.') && (!isNumeric(value));
}

let sum = 0;
let number = '';
let numberIndexes = [];
let row = NaN;

function checkVerticalNeighbours(i) {
    let isAdjacent = false;
    for (const index of numberIndexes) {
        if (isSymbol(input[i][index])) {
            isAdjacent = true;
            break;
        }
    }
    return isAdjacent;
}

function checkHorizontalNeighbours(i) {
    return isSymbol(input[i][numberIndexes[0] - 1]) || isSymbol(input[i][numberIndexes[numberIndexes.length - 1] + 1]);
}

for (let i = 0; i < input.length; i++) {
    for (let j = 1; j < input[i].length - 1; j++) {
        if (isNumeric(input[i][j])) {
            numberIndexes.push(j);
            row = i; // edge-case - if number is at the end of line
        } else {
            if (numberIndexes.length !== 0) {
                numberIndexes.forEach(index => number += input[row][index]);
                let isAdjacent = false;
                if (row !== 0) {
                    isAdjacent = checkVerticalNeighbours(row - 1) || checkHorizontalNeighbours(row - 1);
                }
                if (!isAdjacent && row !== input.length - 1) {
                    isAdjacent = checkVerticalNeighbours(row + 1) || checkHorizontalNeighbours(row + 1);
                }
                if (!isAdjacent) {
                    isAdjacent = checkHorizontalNeighbours(row) || checkHorizontalNeighbours(row);
                }
                if (isAdjacent) {
                    sum += parseInt(number);
                }
                if (number === '...') {
                    console.log(i + "; " + numberIndexes);
                }
                number = ''
                numberIndexes = [];
                row = NaN;
            }
        }
    }
}

console.log(sum);
