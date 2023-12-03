package day03

import readFileAsLinesUsingUseLines
import kotlin.math.pow

class Day03 {

    class Digit(val value: Int, val line: Int, val pos: Int) {
        override fun toString(): String {
            return "Digit(value=$value, line=$line, pos=$pos)"
        }

        override fun equals(other: Any?): Boolean {
            return if (other is Digit) {
                this.value == other.value && this.line == other.line && this.pos == other.pos
            } else {
                false
            }
        }
    }

    fun part1() {
        val input = readFileAsLinesUsingUseLines("2023/day03/input.txt").map { it.split("") }
        var sumOfValidNumbers = 0
        var lineIndex = 0
        while (lineIndex < input.size) {
            val line = input[lineIndex]

            var charIndex = 0
            val digits = mutableListOf<Digit>()
            while (charIndex < line.size) {
                val char = line[charIndex]
                if (char.isDigit()) {
                    digits.add(Digit(char.toInt(), lineIndex, charIndex))
                    while (charIndex < line.size && line[charIndex + 1].isDigit()) {
                        digits.add(Digit(line[charIndex + 1].toInt(), lineIndex, charIndex + 1))
                        charIndex++
                    }

                    var isValid = false
                    for (digit in digits) {
                        val surroundingFields = mutableListOf<String>()
                        for (i in -1..1) {
                            for (j in -1..1) {
                                if (i == 0 && j == 0) {
                                    continue
                                }
                                val x = digit.line + i
                                val y = digit.pos + j
                                if (x >= 0 && x < input.size && y >= 0 && y < input[x].size) {
                                    surroundingFields.add(input[x][y])
                                }
                            }
                        }
                        val filteredFields = surroundingFields.filter { it != "." && !it.isDigit() && it.isNotEmpty() }
                        if (filteredFields.isNotEmpty()) {
                            isValid = true
                            break
                        }
                    }
                    if (isValid) {
                        for ((index, digit) in digits.withIndex()) {
                            sumOfValidNumbers += 10.0.pow(digits.size - 1 - index.toDouble()).toInt() * digit.value
                        }
                    }
                    digits.clear()
                }

                charIndex++
            }
            lineIndex++
        }
        println(sumOfValidNumbers)
    }

    fun part2() {
        val input = readFileAsLinesUsingUseLines("2023/day03/input.txt")
        var gearRatios = 0
        var lineIndex = 0
        while (lineIndex < input.size) {
            var charIndex = 0
            while (charIndex < input[lineIndex].length) {
                val char = input[lineIndex][charIndex]
                if (char == '*') {
                    val surroundingDigits = mutableListOf<Digit>()
                    for (i in -1..1) {
                        for (j in -1..1) {
                            if (i == 0 && j == 0) {
                                continue
                            }
                            val x = lineIndex + i
                            val y = charIndex + j
                            if (x >= 0 && x < input.size && y >= 0 && y < input[x].length) {
                                val surroundingChar = input[x][y]
                                if (surroundingChar.isDigit()) {
                                    surroundingDigits.add(Digit(surroundingChar.digitToInt(), x, y))
                                }
                            }
                        }
                    }
                    val fullNumbers = mutableListOf<MutableList<Digit>>()
                    for ((surroundingIndex, surroundingDigit) in surroundingDigits.withIndex()) {
                        fullNumbers.add(surroundingIndex, mutableListOf())
                        var tempCharIndex = surroundingDigit.pos
                        while (tempCharIndex < input[surroundingDigit.line].length && input[surroundingDigit.line][tempCharIndex + 1].isDigit()) {
                            fullNumbers[surroundingIndex].add(
                                Digit(
                                    input[surroundingDigit.line][tempCharIndex + 1].digitToInt(),
                                    surroundingDigit.line,
                                    tempCharIndex + 1
                                )
                            )
                            tempCharIndex++
                        }
                        tempCharIndex = surroundingDigit.pos
                        while (tempCharIndex > 0 && input[surroundingDigit.line][tempCharIndex - 1].isDigit()) {
                            fullNumbers[surroundingIndex].add(
                                Digit(
                                    input[surroundingDigit.line][tempCharIndex - 1].digitToInt(),
                                    surroundingDigit.line,
                                    tempCharIndex - 1
                                )
                            )
                            tempCharIndex--
                        }
                        fullNumbers[surroundingIndex].add(surroundingDigit)
                    }
                    // when we find the same numbers multiple times, we have to remove all but one


                    val duplicateLessDigits = fullNumbers.removeDuplicates()
                    val numbers = mutableListOf<Int>()
                    for (digits in duplicateLessDigits) {
                        val sorted = digits.sortedBy { it.pos }
                        var c = 0;
                        for ((digitIndex, digit) in sorted.withIndex()) {
                            c += 10.0.pow(sorted.size - 1 - digitIndex.toDouble()).toInt() * digit.value
                        }
                        numbers.add(c)
                    }

                    if (numbers.size == 1) {
                        println("Ignore $numbers")
                    } else if (numbers.size != 2) {
                        throw Exception("Unexpected size of numbers: $numbers")
                    } else {
                        gearRatios += numbers[0] * numbers[1]
                    }
                }
                charIndex++
            }
            lineIndex++
        }
        println(gearRatios)
    }

    private fun String?.isDigit(): Boolean {
        return this?.toIntOrNull() != null
    }

    private fun List<List<Digit>>.removeDuplicates(): List<List<Digit>> {
        val result = mutableListOf<List<Digit>>()
        for (list in this) {
            if (result.none { it.contains(list[0]) }) {
                result.add(list)
            }
        }
        return result
    }
}


fun main() {
    val d = Day03()
    d.part1()
    println("--------------------")
    d.part2()
}
