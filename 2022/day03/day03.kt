package day03

import java.io.File


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun part1() {
    var input = readFileAsLinesUsingUseLines("day03/input.txt")
    var result = 0
    for (line in input) {
        result += handleLine(line)
    }
    println(result)
}

fun charToValue(c: Char): Int {
    if (c.isLowerCase()) {
        return c.code - 96
    } else if (c.isUpperCase()) {
        return c.code - 38
    }
    return 0
}

fun handleLine(line: String): Int {
    var firstCompartment = line.subSequence(0, line.length / 2)
    var secondCompartment = line.subSequence(line.length / 2, line.length)
    for (char in firstCompartment) {
        if (secondCompartment.contains(char)) {
            println("$char goes to ${charToValue(char)}")
            return charToValue(char)
        }
    }
    return 0
}

fun part2() {
    var input = readFileAsLinesUsingUseLines("day03/input.txt")
    var result = 0
    for (index in (0..input.size-2) step 3) {
        println(index)
        var line1 = input[index]
        var line2 = input[index+1]
        var line3 = input[index+2]
        for (c in line1) {
            if (line2.contains(c) && line3.contains(c)) {
                println(c)
                result += charToValue(c)
                break
            }
        }
    }
    println(result)
}

fun main() {
    part2 ()
}
