package day04

import java.io.File


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun part1() {
    var input = readFileAsLinesUsingUseLines("day04/input.txt")
    var result = 0
    for (line in input) {
        var pairs = line.split(",")
        var firstAreaStart = pairs[0].split("-")[0]
        var firstAreaEnd = pairs[0].split("-")[1]
        var secondAreaStart = pairs[1].split("-")[0]
        var secondAreaEnd = pairs[1].split("-")[1]
        var firstAreaRange = Integer.parseInt(firstAreaStart)..Integer.parseInt(firstAreaEnd)
        var secondAreaRange = Integer.parseInt(secondAreaStart)..Integer.parseInt(secondAreaEnd)
        val firstContainsSecond = firstAreaRange.all { secondAreaRange.contains(it) }
        val secondContainsFirst = secondAreaRange.all { firstAreaRange.contains(it) }
        if (firstContainsSecond || secondContainsFirst) result++

    }
    println(result)
}


fun part2() {
    var input = readFileAsLinesUsingUseLines("day04/input.txt")
    var result = 0
    for (line in input) {
        var pairs = line.split(",")
        var firstAreaStart = pairs[0].split("-")[0]
        var firstAreaEnd = pairs[0].split("-")[1]
        var secondAreaStart = pairs[1].split("-")[0]
        var secondAreaEnd = pairs[1].split("-")[1]
        var firstAreaRange = Integer.parseInt(firstAreaStart)..Integer.parseInt(firstAreaEnd)
        var secondAreaRange = Integer.parseInt(secondAreaStart)..Integer.parseInt(secondAreaEnd)
        val firstContainsSecond = firstAreaRange.any { secondAreaRange.contains(it) }
        val secondContainsFirst = secondAreaRange.any { firstAreaRange.contains(it) }
        if (firstContainsSecond || secondContainsFirst) result++

    }
    println(result)
}

fun main() {
    part2()
}
