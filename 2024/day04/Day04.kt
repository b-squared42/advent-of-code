package day04

import readFileAsLines

class Day04 {
    fun part1() {
        val input = readFileAsLines("2024/day04/input.txt")
        val mappedInput = input.map { it.split("") }
        var xmasses = 0
        for (y in mappedInput.indices) {
            for (x in 0 until mappedInput[y].size) {
                xmasses += checkEveryDirection(Pair(y, x), mappedInput)
            }
        }
        println(xmasses)

    }

    private fun checkEveryDirection(
        position: Pair<Int, Int>,
        mappedInput: List<List<String>>,
        word: Boolean = true
    ): Int {
        var xmases = 0
        if (word) {
            val directions = listOf(
                Pair(-1, 0), // top
                Pair(-1, 1), // top right
                Pair(0, 1), // right
                Pair(1, 1), // bottom right
                Pair(1, 0), // bottom
                Pair(1, -1), // bottom left
                Pair(0, -1), // left
                Pair(-1, -1) // top left
            )
            for (direction in directions) {
                if (checkForXMAS(position, mappedInput, direction)) {
                    xmases++
                }

            }
        } else {
            if (checkForCrossMAS(position, mappedInput)) {
                xmases++
            }
        }
        return xmases
    }

    private fun checkForXMAS(
        position: Pair<Int, Int>,
        mappedInput: List<List<String>>,
        direction: Pair<Int, Int>
    ): Boolean {
        try {
            val letter1 = mappedInput[position.first + direction.first * 0][position.second + direction.second * 0]
            val letter2 = mappedInput[position.first + direction.first * 1][position.second + direction.second * 1]
            val letter3 = mappedInput[position.first + direction.first * 2][position.second + direction.second * 2]
            val letter4 = mappedInput[position.first + direction.first * 3][position.second + direction.second * 3]
            val word = letter1 + letter2 + letter3 + letter4
            return word == "XMAS"
        } catch (e: IndexOutOfBoundsException) {
            return false
        }
    }

    private fun checkForCrossMAS(
        position: Pair<Int, Int>,
        mappedInput: List<List<String>>,
    ): Boolean {
        if (mappedInput[position.first][position.second] != "A") {
            return false
        }
        val directions = listOf(
            Pair(-1, 1), // top right
            Pair(1, 1), // bottom right
            Pair(1, -1), // bottom left
            Pair(-1, -1) // top left
        )
        var masses = 0
        for (direction in directions) {
            if (mappedInput[position.first + direction.first * 1][position.second + direction.second * 1] == "M"
                && mappedInput[position.first + direction.first * -1][position.second + direction.second * -1] == "S"
            ) {
                masses++
            }
        }
        return masses >= 2
    }

    fun part2() {
        val input = readFileAsLines("2024/day04/input.txt")
        val mappedInput = input.map { it.split("") }
        var xmasses = 0
        for (y in 1 until mappedInput.size - 1) {
            for (x in 1 until mappedInput[y].size - 1) {
                xmasses += checkEveryDirection(Pair(y, x), mappedInput, false)
            }
        }
        println(xmasses)
    }
}


fun main() {
    Day04().part1()
    println("----------------")
    Day04().part2()
}
