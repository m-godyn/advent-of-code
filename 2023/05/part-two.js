const {seeds, maps} = require('./input');
const reversedMaps = maps.reverse();

function findMappedValue(value, map) {
    for (const entry of map) {
        const [destination, source, range] = entry;
        if (destination <= value && destination + range > value) {
            value = source + value - destination;
            break;
        }
    }
    return value;
}

function doesSeedExist(seed) {
    for (let i = 0; i < seeds.length; i += 2) {
        if (seed >= seeds[i] && seed <= seeds[i] + seeds[i + 1]) {
            return true;
        }
    }
    return false
}

let locationFound = false;
let location = 0;
while (!locationFound) {
    location++;
    let seed = location;
    reversedMaps.forEach(map => seed = findMappedValue(seed, map));
    locationFound = doesSeedExist(seed);
}

console.log(location);
