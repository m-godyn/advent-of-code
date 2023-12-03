const input = require('./input');

const upper = (i, j) => input[i - 1][j];
const bottom = (i, j) => input[i + 1][j];
const left = (i, j) => input[i][j - 1];
const right = (i, j) => input[i][j + 1];
const leftUpper = (i, j) => input[i - 1][j - 1];
const leftBottom = (i, j) => input[i + 1][j - 1];
const rightUpper = (i, j) => input[i - 1][j + 1];
const rightBottom = (i, j) => input[i + 1][j + 1];

function isNumeric(value) {
    return /^[^.]?\d+[^.]?$/.test(value);
}

function isAsterisk(value) {
    return /\*/.test(value);
}

let numbers = [];
let sum = 0;

for (let i = 0; i < input.length; i++) {
    for (let j = 1; j < input[i].length - 1; j++) {
        if (isAsterisk(input[i][j])) {
            let upperNeighbours = leftUpper(i, j) + upper(i, j) + rightUpper(i, j);
            let bottomNeighbours = leftBottom(i, j) + bottom(i, j) + rightBottom(i, j);

            if (isNumeric(upperNeighbours)) {
                numbers.push(parseInt(upperNeighbours));
            } else {
                if (isNumeric(upper(i, j)) && !isNumeric(leftUpper(i, j)) && !isNumeric(rightUpper(i, j))) {
                    numbers.push(upper(i, j));
                }
                if (isNumeric(leftUpper(i, j))) {
                    let number = leftUpper(i, j);
                    let index = 2;
                    while (isNumeric(input[i - 1][j - index])) {
                        number = input[i - 1][j - index] + number;
                        index++;
                    }
                    if (isNumeric(upper(i, j))) {
                        number += upper(i, j);
                    }
                    numbers.push(number);
                }
                if (isNumeric(rightUpper(i, j))) {
                    let number = rightUpper(i, j);
                    let index = 2;
                    while (isNumeric(input[i - 1][j + index])) {
                        number += input[i - 1][j + index];
                        index++;
                    }
                    if (isNumeric(upper(i, j))) {
                        number = upper(i, j) + number;
                    }
                    numbers.push(number);
                }
            }

            if (isNumeric(bottomNeighbours)) {
                numbers.push(parseInt(bottomNeighbours));
            } else {
                if (isNumeric(bottom(i, j)) && !isNumeric(leftBottom(i, j)) && !isNumeric(rightBottom(i, j))) {
                    numbers.push(bottom(i, j));
                }
                if (isNumeric(leftBottom(i, j))) {
                    let number = leftBottom(i, j);
                    let index = 2;
                    while (isNumeric(input[i + 1][j - index])) {
                        number = input[i + 1][j - index] + number;
                        index++;
                    }
                    if (isNumeric(bottom(i, j))) {
                        number += bottom(i, j);
                    }
                    numbers.push(number);
                }
                if (isNumeric(rightBottom(i, j))) {
                    let number = rightBottom(i, j);
                    let index = 2;
                    while (isNumeric(input[i + 1][j + index])) {
                        number += input[i + 1][j + index];
                        index++;
                    }
                    if (isNumeric(bottom(i, j))) {
                        number = bottom(i, j) + number;
                    }
                    numbers.push(number);
                }
            }

            // next-to neighbours
            if (isNumeric(left(i, j))) {
                let number = left(i, j);
                let index = 2;
                while (isNumeric(input[i][j - index])) {
                    number = input[i][j - index] + number;
                    index++;
                }
                numbers.push(number);
            }

            if (isNumeric(right(i, j))) {
                let number = right(i, j);
                let index = 2;
                while (isNumeric(input[i][j + index])) {
                    number += input[i][j + index];
                    index++;
                }
                numbers.push(number);
            }

            if (numbers.length === 2) {
                sum += numbers[0] * numbers[1];
            }

            numbers = [];
        }
    }
}

console.log(sum);
