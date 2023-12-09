const oasis = require('./input');

function extractDifferences(arr) {
    return arr.slice(1).reduce((diffs, current, index) => {
        const diff = current - arr[index];
        diffs.push(diff);
        return diffs;
    }, []);
}

function sumArray(arr) {
    return arr.reduce((sum, value) => sum + value, 0);
}

function substractSumArray(arr) {
    return arr.reduce((sum, value) => value - sum, 0);
}

function extrapolate(arr, sumFunction) {
    let values = [arr];
    while (!values[0].every(value => value === 0)) {
        values.unshift(extractDifferences(values[0]));
    }
    return sumFunction(
        values
            .slice(1)
            .map(current => sumFunction === sumArray ? current[current.length - 1] : current[0])
    );
}

console.log(
    "Part 1: " + oasis.reduce((sum, history) => sum + extrapolate(history, sumArray), 0)
);

console.log(
    "Part 2: " + oasis.reduce((sum, history) => sum + extrapolate(history, substractSumArray), 0)
);
