package day12

import java.io.File


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun part1() {
    var input = readFileAsLinesUsingUseLines("day12/input.txt")
    val xDimension = input[0].split("").filter { it.trim().isNotEmpty() }.size
    val yDimension = input.size
    val field = Array(xDimension) { Array(yDimension) { 0 } }
    var startPosition: Pair<Int, Int>? = null
    var endPosition: Pair<Int, Int>? = null
    for ((lineIndex, line) in input.withIndex()) {
        for ((charIndex, char) in line.codePoints().toArray().withIndex()) {
            when (char) {
                83 -> {
                    startPosition = Pair(charIndex, lineIndex)
                    field[charIndex][lineIndex] = 0
                }

                69 -> {
                    endPosition = Pair(charIndex, lineIndex)
                    field[charIndex][lineIndex] = 25
                }

                else -> {
                    field[charIndex][lineIndex] = char - 97
                }
            }
        }
    }
    if (startPosition == null || endPosition == null) {
        throw Exception("Unknown start or end")
    }
    var possiblePositions = getValidMoves1(startPosition, field)
    var knownPositions = ArrayList<Pair<Int, Int>>()
    knownPositions.add(startPosition)
    println(move1(possiblePositions, field, endPosition, knownPositions, 1))
}

fun printField(field: Array<Array<Int>>, curPos: Pair<Int, Int>?) {
    for (yIndex in field[0].indices) {
        for (xIndex in field.indices) {
            if (curPos == Pair(xIndex, yIndex)) {
                val red = "\u001b[31m"
                val reset = "\u001b[0m"
                print("$red${field[xIndex][yIndex]}\t$reset")
            } else {
                print("${field[xIndex][yIndex]}\t")
            }
        }
        println()
    }
}

fun move1(
    possiblePositions: List<Pair<Int, Int>>,
    field: Array<Array<Int>>,
    endPosition: Pair<Int, Int>,
    knownPositions: MutableList<Pair<Int, Int>>,
    moves: Int
): Int {
    var newPossiblePositions = ArrayList<Pair<Int, Int>>()
    knownPositions.addAll(possiblePositions)
    for (possiblePosition in possiblePositions) {
        if (possiblePosition == endPosition) {
            return moves
        }
        newPossiblePositions.addAll(getValidMoves1(possiblePosition, field).filter { !knownPositions.contains(it) })
    }
    println(moves)
    return move1(newPossiblePositions.distinct(), field, endPosition, knownPositions, moves + 1)
}

fun move2(
    possiblePositions: List<Pair<Int, Int>>,
    field: Array<Array<Int>>,
    endPosition: Pair<Int, Int>,
    knownPositions: MutableList<Pair<Int, Int>>,
    moves: Int
): Int {
    var newPossiblePositions = ArrayList<Pair<Int, Int>>()
    knownPositions.addAll(possiblePositions)
    for (possiblePosition in possiblePositions) {
        if (field[possiblePosition.first][possiblePosition.second] == 0) {
            return moves
        }
        newPossiblePositions.addAll(getValidMoves2(possiblePosition, field).filter { !knownPositions.contains(it) })
    }
    println(moves)
    return move2(newPossiblePositions.distinct(), field, endPosition, knownPositions, moves + 1)
}

fun getValidMoves1(position: Pair<Int, Int>, field: Array<Array<Int>>): List<Pair<Int, Int>> {
    var list = ArrayList<Pair<Int, Int>>()
    var currentHeight = field[position.first][position.second]
    if (position.first != 0) {
        var maybePossibleHeight = field[position.first - 1][position.second]
        if (currentHeight + 1 >= maybePossibleHeight) list.add(Pair(position.first - 1, position.second))
    }
    if (position.first != field.size - 1) {
        var maybePossibleHeight = field[position.first + 1][position.second]
        if (currentHeight + 1 >= maybePossibleHeight) list.add(Pair(position.first + 1, position.second))
    }
    if (position.second != 0) {
        var maybePossibleHeight = field[position.first][position.second - 1]
        if (currentHeight + 1 >= maybePossibleHeight) list.add(Pair(position.first, position.second - 1))
    }
    if (position.second != field[0].size - 1) {
        var maybePossibleHeight = field[position.first][position.second + 1]
        if (currentHeight + 1 >= maybePossibleHeight) list.add(Pair(position.first, position.second + 1))
    }

    return list.sortedBy { -field[it.first][it.second] }
}
fun getValidMoves2(position: Pair<Int, Int>, field: Array<Array<Int>>): List<Pair<Int, Int>> {
    var list = ArrayList<Pair<Int, Int>>()
    var currentHeight = field[position.first][position.second]
    if (position.first != 0) {
        var maybePossibleHeight = field[position.first - 1][position.second]
        if (maybePossibleHeight + 1 >= currentHeight) list.add(Pair(position.first - 1, position.second))
    }
    if (position.first != field.size - 1) {
        var maybePossibleHeight = field[position.first + 1][position.second]
        if (maybePossibleHeight + 1 >= currentHeight) list.add(Pair(position.first + 1, position.second))
    }
    if (position.second != 0) {
        var maybePossibleHeight = field[position.first][position.second - 1]
        if (maybePossibleHeight + 1 >= currentHeight) list.add(Pair(position.first, position.second - 1))
    }
    if (position.second != field[0].size - 1) {
        var maybePossibleHeight = field[position.first][position.second + 1]
        if (maybePossibleHeight + 1 >= currentHeight) list.add(Pair(position.first, position.second + 1))
    }

    return list.sortedBy { -field[it.first][it.second] }
}


fun part2() {
    var input = readFileAsLinesUsingUseLines("day12/input.txt")
    val xDimension = input[0].split("").filter { it.trim().isNotEmpty() }.size
    val yDimension = input.size
    val field = Array(xDimension) { Array(yDimension) { 0 } }
    var startPosition: Pair<Int, Int>? = null
    for ((lineIndex, line) in input.withIndex()) {
        for ((charIndex, char) in line.codePoints().toArray().withIndex()) {
            when (char) {
                83 -> {
                    field[charIndex][lineIndex] = 0
                }

                69 -> {
                    startPosition = Pair(charIndex, lineIndex)
                    field[charIndex][lineIndex] = 25
                }

                else -> {
                    field[charIndex][lineIndex] = char - 97
                }
            }
        }
    }
    if (startPosition == null) {
        throw Exception("Unknown start")
    }
    var possiblePositions = getValidMoves2(startPosition, field)
    var knownPositions = ArrayList<Pair<Int, Int>>()
    knownPositions.add(startPosition)
    println(move2(possiblePositions, field, startPosition, knownPositions, 1))
}

fun main() {
    part2()
}
