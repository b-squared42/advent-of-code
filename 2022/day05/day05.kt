package day05

import java.io.File


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun getEmptyLine(input: List<String>): Int {
    for ((index, line) in input.withIndex()) {
        if (line.isEmpty()) return index
    }
    throw Exception("no empty line found")
}

fun movePart1(stacks: HashMap<String, ArrayDeque<String>>, from: String, to: String, amount: Int) {
    if (amount == 0) return

    var crate = stacks[from]?.removeFirst()
    if (crate != null) stacks[to]?.add(0, crate)
    movePart1(stacks, from, to, amount - 1)
}

fun movePart2(stacks: HashMap<String, ArrayDeque<String>>, from: String, to: String, amount: Int) {
    if (amount == 0) return

    for (index in amount-1 downTo 0) {
        var crate = stacks[from]?.removeAt(index)
        if (crate != null) stacks[to]?.add(0, crate)
    }
}

fun part1() {
    val input = readFileAsLinesUsingUseLines("day05/input.txt")
    var emptyIndex = getEmptyLine(input)
    var titles = input[emptyIndex - 1].split("").filter { it.trim().isNotEmpty() }
    var stacks = HashMap<String, ArrayDeque<String>>()
    for (lineIndex in emptyIndex - 2 downTo 0) {
        var chars = input[lineIndex].chunked(4).map { it.substring(1, 2) }
        for ((index, char) in chars.withIndex()) {
            var list = stacks[titles[index]]
            if (list == null) {
                list = ArrayDeque()
            }
            if (char.trim().isNotEmpty()) list.add(0, char)
            stacks[titles[index]] = list
        }
    }
    for (stack in stacks) {
        println(stack)
    }
    for (lineIndex in emptyIndex + 1 until input.size) {
        var words = input[lineIndex].split(" ")
        movePart1(stacks, words[3], words[5], Integer.parseInt(words[1]))
    }
    for (index in titles) {
        print(stacks[index]?.first())
    }
}


fun part2() {
    val input = readFileAsLinesUsingUseLines("day05/input.txt")
    var emptyIndex = getEmptyLine(input)
    var titles = input[emptyIndex - 1].split("").filter { it.trim().isNotEmpty() }
    var stacks = HashMap<String, ArrayDeque<String>>()
    for (lineIndex in emptyIndex - 2 downTo 0) {
        var chars = input[lineIndex].chunked(4).map { it.substring(1, 2) }
        for ((index, char) in chars.withIndex()) {
            var list = stacks[titles[index]]
            if (list == null) {
                list = ArrayDeque()
            }
            if (char.trim().isNotEmpty()) list.add(0, char)
            stacks[titles[index]] = list
        }
    }
    for (stack in stacks) {
        println(stack)
    }
    for (lineIndex in emptyIndex + 1 until input.size) {
        var words = input[lineIndex].split(" ")
        movePart2(stacks, words[3], words[5], Integer.parseInt(words[1]))
    }
    for (index in titles) {
        print(stacks[index]?.first())
    }
}

fun main() {
    part2()
}
