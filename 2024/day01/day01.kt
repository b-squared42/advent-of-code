package day01

import readFileAsLines

class Day01 {
    fun part1() {
        val input = readFileAsLines("2024/day01/input.txt")
        input.filter { it.isEmpty() }
        val leftList = input.map { it.split("   ")[0].toInt() }
        val rightList = input.map { it.split("   ")[1].toInt() }

        val sortedLeftList = leftList.sorted()
        val sortedRightList = rightList.sorted()

        var totalDistance = 0
        for (i in sortedLeftList.indices) {
            val distance = Math.abs(sortedRightList[i] - sortedLeftList[i])
            totalDistance += distance
        }

        println("Total distance: $totalDistance")
    }

    fun part2() {
        val input = readFileAsLines("2024/day01/input.txt")
        input.filter { it.isEmpty() }
        val leftList = input.map { it.split("   ")[0].toInt() }
        val rightList = input.map { it.split("   ")[1].toInt() }

        var totalDistance = 0
        for (i in leftList.indices) {
            val left = leftList[i]
            val count = rightList.count { it == left }
            totalDistance += left*count
        }

        println("Total distance: $totalDistance")
    }
}


fun main() {
    Day01().part1()
    println("----------------")
    Day01().part2()
}
