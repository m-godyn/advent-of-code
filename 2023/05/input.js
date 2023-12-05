const path = require("path");
const fs = require("fs");

const input = fs
    .readFileSync(path.join(__dirname, "input.txt"), "utf-8")
    .toString()
    .trim()
    .split("\n\n");

function extractMapValues(mapName, inputIndex) {
    return input[inputIndex]
        .trim()
        .split("\n")
        .filter(map => !(map === mapName))
        .map(map => map.trim().split(" ").map(value => parseInt(value)));
}

const seeds = input[0]
    .trim()
    .split(" ")
    .filter(seed => !isNaN(parseInt(seed)))
    .map(seed => parseInt(seed));

const seedToSoilMap = extractMapValues('seed-to-soil map:', 1);
const soilToFertilizerMap = extractMapValues('soil-to-fertilizer map:', 2);
const fertilizerToWaterMap = extractMapValues('fertilizer-to-water map:', 3);
const waterToLightMap = extractMapValues('water-to-light map:', 4);
const lightToTemperatureMap = extractMapValues('light-to-temperature map:', 5);
const temperatureToHumidityMap = extractMapValues('temperature-to-humidity map:', 6);
const humidityToLocationMap = extractMapValues('humidity-to-location map:', 7);

module.exports = {
    seeds,
    maps: [
        seedToSoilMap,
        soilToFertilizerMap,
        fertilizerToWaterMap,
        waterToLightMap,
        lightToTemperatureMap,
        temperatureToHumidityMap,
        humidityToLocationMap,
    ]
}
