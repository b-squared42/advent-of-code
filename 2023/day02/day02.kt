package day02

import readFileAsLinesUsingUseLines

class Day02 {
    fun part1() {
        val input = readFileAsLinesUsingUseLines("2023/day02/input.txt")
        val allowedCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)

        var sumOfValidGames = List(input.size) { index -> index + 1 }.sum()

        val games = input.map { it.split(": ")[1] }

        for ((index, s) in games.withIndex()) {
            val sets = s.split("; ")
            sets@ for (set in sets) {
                val cubes = set.split(", ")
                for (cube in cubes) {
                    val color = cube.split(" ")[1]
                    val value = cube.split(" ")[0].toInt()
                    if (value > allowedCubes[color]!!) {
                        println("Invalid cube: $cube")
                        sumOfValidGames -= index + 1
                        break@sets
                    }
                }
            }
        }
        println("Sum of valid games: $sumOfValidGames")
    }

    fun part2() {
        val input = readFileAsLinesUsingUseLines("2023/day02/input.txt")

        var sumOfPowers = 0
        val games = input.map { it.split(": ")[1] }

        for (s in games) {
            val sets = s.split("; ")
            var minimalCubes = mapOf("red" to 0, "green" to 0, "blue" to 0)
            for (set in sets) {
                val cubes = set.split(", ")
                for (cube in cubes) {
                    val color = cube.split(" ")[1]
                    val value = cube.split(" ")[0].toInt()
                    if (value > minimalCubes[color]!!) {
                        minimalCubes = minimalCubes.plus(color to value)
                    }
                }
            }
            sumOfPowers += minimalCubes.values.reduce(Int::times)
        }
        println("Sum of powers: $sumOfPowers")

    }
}


fun main() {
    val d = Day02()
    d.part1()
    println("--------------------")
    d.part2()
}
