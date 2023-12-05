const {seeds, maps} = require('./input');

function findMappedValue(values, map) {
    return values.map(value => {
        for (const entry of map) {
            const [destination, source, range] = entry;
            if (value >= source && value <= source + range) {
                value = destination + value - source;
                break;
            }
        }
        return value;
    });
}

function findLocations(seeds) {
    let locations = seeds;
    maps.forEach(map => locations = findMappedValue(locations, map));
    return locations;
}

const locations = findLocations(seeds);

console.log(
    Math.min(...locations)
);
