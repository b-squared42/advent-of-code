package day01

import java.io.File

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }


fun part1() {
    var input = readFileAsLinesUsingUseLines("day01/input.txt")

    var mostCalories = 0;
    var tempCalories = 0;
    for (line in input) {
        if (line.trim().isEmpty()) {
            mostCalories = if (mostCalories > tempCalories) mostCalories else tempCalories
            tempCalories = 0
            continue
        }
        tempCalories += Integer.parseInt(line);
    }

    println(mostCalories)
}

fun part2() {
    var input = readFileAsLinesUsingUseLines("day01/input.txt")
    var mostCalories = intArrayOf(0, 0, 0);
    var tempCalories = 0;
    for (line in input) {
        if (line.trim().isEmpty()) {
            if (tempCalories > mostCalories.first()) {
                mostCalories[0] = tempCalories
                mostCalories.sort()
            }
            tempCalories = 0
            continue
        }
        tempCalories += Integer.parseInt(line);
    }
    println(mostCalories.contentToString())
    println(mostCalories.sum())
}

fun main() {
    part2()
}
