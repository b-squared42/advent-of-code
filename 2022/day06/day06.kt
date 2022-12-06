package day06

import java.io.File


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun part1() {
    var input = readFileAsLinesUsingUseLines("day06/input.txt")
    var chars = input[0].split("")
    for (index in 0..chars.size - 5) {
        var list = listOf(chars[index], chars[index + 1], chars[index + 2], chars[index + 3])
        if (list.stream().distinct().filter{it.trim().isNotEmpty()}.toList().size == 4) {
            println(index + 3)
            break
        }
    }
}


fun part2() {
    var input = readFileAsLinesUsingUseLines("day06/input.txt")
    var chars = input[0].split("")
    for (index in 0..chars.size - 15) {
        var list = input[0].substring(index, index+14).split("")
        if (list.stream().distinct().filter{it.trim().isNotEmpty()}.toList().size == 14) {
            println(index + 14)
            break
        }
    }
}

fun main() {
    part2()
}
