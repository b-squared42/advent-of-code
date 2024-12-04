package day02

import readFileAsLines

class Day02 {
    fun part1() {
        val reports = readFileAsLines("2024/day02/input.txt")
        var safeReports = 0
        for (report in reports) {
            val levels = report.split(" ").map { it.toInt() }
            val reportSafe = isReportSafe(levels)
            if (reportSafe) {
                safeReports++
            }
        }

        println(safeReports)
    }

    fun part2() {
        val reports = readFileAsLines("2024/day02/input.txt")
        var safeReports = 0
        for (report in reports) {
            val levels = report.split(" ").map { it.toInt() }
            val levelCombinations = mutableListOf(levels)
            for (i in levels.indices) {
                levelCombinations.add(levels.filterIndexed({ index, _ -> index != i }))
            }
            for (combination in levelCombinations) {
                val reportSafe = isReportSafe(combination)
                if (reportSafe) {
                    safeReports++
                    break
                }
            }
        }
        println(safeReports)

    }

    fun isReportSafe(levels: List<Int>): Boolean {
        val increasing = levels[0] < levels[1]
        var unsafe = false
        levels.windowed(2).forEach {
            if (!((increasing && it[1] - it[0] in 1..3) || (!increasing && it[0] - it[1] in 1..3))) {
                unsafe = true
            }
        }
        return !unsafe
    }
}


fun main() {
    Day02().part1()
    println("----------------")
    Day02().part2()
}
