const {input} = require('./input')

function calibrationSum(input) {
    return input
        .map(calibration => {
            const digitsOnly = calibration.replace(/\D/g, '');
            return parseInt(digitsOnly.charAt(0) + digitsOnly.slice(-1));
        })
        .reduce((sum, calibration) => sum + calibration, 0);
}

console.log(calibrationSum(input));

module.exports = {
    calibrationSum,
}
