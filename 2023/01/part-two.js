const {input} = require('./input');
const {calibrationSum} = require('./part-one')

function replaceSpelledDigits(input) {
    return input.map(calibration => {
        return calibration
            .replace(/one/g, "o1e")
            .replace(/two/g, "t2o")
            .replace(/three/g, "th3ee")
            .replace(/four/g, "fo4ur")
            .replace(/five/g, "fi5ve")
            .replace(/six/g, "s6x")
            .replace(/seven/g, "se7en")
            .replace(/eight/g, "ei8ht")
            .replace(/nine/g, "ni9ne");
    });
}

const sanitizedInput = replaceSpelledDigits(input);

console.log(calibrationSum(sanitizedInput));
