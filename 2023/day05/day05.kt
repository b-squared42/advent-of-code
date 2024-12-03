package day05

import readFileAsLinesUsingUseLines


class Day05 {
    fun part1() {
        val input = readFileAsLinesUsingUseLines("2023/day05/input.txt")
        val seeds = input[0].split("seeds: ")[1].split(" ").map { it.toDouble() }
        val seedToSoilMap = mutableListOf<List<Double>>()
        val soilToFertilizerMap = mutableListOf<List<Double>>()
        val fertilizerToWaterMap = mutableListOf<List<Double>>()
        val waterToLightMap = mutableListOf<List<Double>>()
        val lightToTemperatureMap = mutableListOf<List<Double>>()
        val temperatureToHumidityMap = mutableListOf<List<Double>>()
        val humidityToLocationMap = mutableListOf<List<Double>>()
        var line = 3
        line = inputToMap(input, line, seedToSoilMap)
        line += 2
        line = inputToMap(input, line, soilToFertilizerMap)
        line += 2
        line = inputToMap(input, line, fertilizerToWaterMap)
        line += 2
        line = inputToMap(input, line, waterToLightMap)
        line += 2
        line = inputToMap(input, line, lightToTemperatureMap)
        line += 2
        line = inputToMap(input, line, temperatureToHumidityMap)
        line += 2
        inputToMap(input, line, humidityToLocationMap)

        var lowestLocation = Double.MAX_VALUE
        for (seed in seeds) {
            val soil = seedToSoilMap.toValue(seed)
            val fertilizer = soilToFertilizerMap.toValue(soil)
            val water = fertilizerToWaterMap.toValue(fertilizer)
            val light = waterToLightMap.toValue(water)
            val temperature = lightToTemperatureMap.toValue(light)
            val humidity = temperatureToHumidityMap.toValue(temperature)
            val location = humidityToLocationMap.toValue(humidity)
            if (location < lowestLocation) {
                lowestLocation = location
            }
        }
        println(lowestLocation)
    }

    fun part2() {
        val input = readFileAsLinesUsingUseLines("2023/day05/input.txt")
        val seeds = input[0]
            .split("seeds: ")[1]
            .split(" ")
            .map { it.toDouble() }
            .windowed(2, 2) { (elem1, elem2) -> elem1.rangeUntil(elem1 + elem2) }
            .flatMap { it.toList() }
            .toSet()
            .toList()

        val seedToSoilMap = mutableListOf<List<Double>>()
        val soilToFertilizerMap = mutableListOf<List<Double>>()
        val fertilizerToWaterMap = mutableListOf<List<Double>>()
        val waterToLightMap = mutableListOf<List<Double>>()
        val lightToTemperatureMap = mutableListOf<List<Double>>()
        val temperatureToHumidityMap = mutableListOf<List<Double>>()
        val humidityToLocationMap = mutableListOf<List<Double>>()
        var line = 3
        line = inputToMap(input, line, seedToSoilMap)
        line += 2
        line = inputToMap(input, line, soilToFertilizerMap)
        line += 2
        line = inputToMap(input, line, fertilizerToWaterMap)
        line += 2
        line = inputToMap(input, line, waterToLightMap)
        line += 2
        line = inputToMap(input, line, lightToTemperatureMap)
        line += 2
        line = inputToMap(input, line, temperatureToHumidityMap)
        line += 2
        inputToMap(input, line, humidityToLocationMap)

        var lowestLocation = Double.MAX_VALUE
        for (seed in seeds) {
            val soil = seedToSoilMap.toValue(seed)
            val fertilizer = soilToFertilizerMap.toValue(soil)
            val water = fertilizerToWaterMap.toValue(fertilizer)
            val light = waterToLightMap.toValue(water)
            val temperature = lightToTemperatureMap.toValue(light)
            val humidity = temperatureToHumidityMap.toValue(temperature)
            val location = humidityToLocationMap.toValue(humidity)
            if (location < lowestLocation) {
                lowestLocation = location
            }
        }
        println(lowestLocation)
    }

    private fun List<List<Double>>.toRanges(): List<OpenEndRange<Double>> {
        return this.map { it[1]..<it[1] + it[2] }
    }

    private fun List<List<Double>>.toValue(value: Double): Double {
        for ((index, range) in this.toRanges().withIndex()) {
            if (value in range) {
                return value + (this[index][0] - this[index][1])
            }
        }
        return value
    }

    private fun inputToMap(input: List<String>, line: Int, map: MutableList<List<Double>>): Int {
        var newLine = line
        while (true) {
            if (input.size <= newLine || input[newLine].isEmpty()) {
                break
            }
            map.add(input[newLine].split(" ").map { it.toDouble() })
            newLine++
        }
        return newLine
    }

    private fun OpenEndRange<Double>.toList(): List<Double> {
        var i = this.start
        val list = mutableListOf<Double>()
        while (i < this.endExclusive) {
            list.add(i)
            i += 1
        }
        return list
    }
}


fun main() {
    val d = Day05()
    d.part1()
    println("--------------------")
    d.part2()
}


