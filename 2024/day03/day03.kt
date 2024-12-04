package day03

import readFileAsLines

class Day03 {
    fun part1() {
        val input = readFileAsLines("2024/day03/input.txt")
        val fullInput = input.joinToString()
        val regex = Regex("""mul\(\d+,\d+\)""")

        var sum = 0
        regex.findAll(fullInput).forEach {
            val values = it.value.substring(4, it.value.length - 1).split(",")
            sum += values[0].toInt() * values[1].toInt()
        }
        println(sum)

    }

    fun part2() {
        val input = readFileAsLines("2024/day03/input.txt")
        val fullInput = input.joinToString()

        var remainingText = fullInput
        var searchedWord = "don't()"
        var filteredText = ""
        while (true) {
            if (searchedWord == "don't()") {
                filteredText += remainingText.substringBefore(searchedWord)
            }
            remainingText = remainingText.substringAfter(searchedWord)
            searchedWord = if (searchedWord == "don't()") {
                "do()"
            } else {
                "don't()"
            }
            if (!remainingText.contains(searchedWord)) {
                if (searchedWord == "don't()") {
                    filteredText += remainingText
                }
                break
            }
        }

        val regex = Regex("""mul\(\d+,\d+\)""")

        var sum = 0
        regex.findAll(filteredText).forEach {
            val values = it.value.substring(4, it.value.length - 1).split(",")
            sum += values[0].toInt() * values[1].toInt()
        }
        println(sum)
    }
}


fun main() {
    Day03().part1()
    println("----------------")
    Day03().part2()
}
