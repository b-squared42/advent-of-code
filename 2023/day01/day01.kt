package day01

import readFileAsLinesUsingUseLines

class Day01 {
    fun part1() {
        val input = readFileAsLinesUsingUseLines("2023/day01/input.txt")
        var sum = 0
        for (line in input) {
            val regex = Regex("""(\d)""")
            val numbersAsString = regex.findAll(line).map { it.value }.toList()
            sum += (numbersAsString[0] + numbersAsString[numbersAsString.size - 1]).toInt()
        }
        println("Part1: $sum")
    }

    fun part2() {
        val input = readFileAsLinesUsingUseLines("2023/day01/input.txt")
        var sum = 0
        for (line in input) {
            val numberedLine = line.replace("one", "one1one")
                .replace("two", "two2two")
                .replace("three", "three3three")
                .replace("four", "four4four")
                .replace("five", "five5five")
                .replace("six", "six6six")
                .replace("seven", "seven7seven")
                .replace("eight", "eight8eight")
                .replace("nine", "nine9nine")
            val regex = Regex("""(\d)""")
            val numbersAsString = regex.findAll(numberedLine).map { it.value }.toList()
            sum += (numbersAsString[0] + numbersAsString[numbersAsString.size - 1]).toInt()
        }
        println("Part2: $sum")

    }
}


fun main() {
    Day01().part1()
    Day01().part2()
}
