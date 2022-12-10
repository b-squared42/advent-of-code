package day10

import java.io.File


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun part1() {
    var input = readFileAsLinesUsingUseLines("day10/input.txt")
    var map = HashMap<Int, Int>()
    var cycle = 1
    var register = 1
    for (line in input) {
        if (line.startsWith("noop")) {
            cycle += 1
            continue
        }
        cycle += 2
        register += Integer.parseInt(line.split(" ")[1])
        map[cycle] = register
    }
    var result = 0
    for (step in 20..220 step 40) {
        result += (getFromMap(map, step) * step)
    }
    println(map)
    println(result)
}

fun getFromMap(map: java.util.HashMap<Int, Int>, index: Int): Int {
    if (map.containsKey(index)) {
        return map[index]!!
    }
    return getFromMap(map, index - 1)
}


fun part2() {
    var input = readFileAsLinesUsingUseLines("day10/input.txt")
    var cycle = 0
    var register = 1
    var row = 0
    var array = Array(240) { "." }
    for (line in input) {
        var spritePosition = register - 1..register + 1


        if (spritePosition.contains(cycle)) array[cycle + (row * 40)] = "#"
        cycle++
        if (cycle > 39) {
            cycle -= 40
            row++
        }
        if (!line.startsWith("noop")) {
            if (spritePosition.contains(cycle)) array[cycle + (row * 40)] = "#"
            cycle++
            if (cycle > 39) {
                cycle -= 40
                row++
            }
            register += Integer.parseInt(line.split(" ")[1])
        }
    }
    for ((index, str) in array.withIndex()) {
        if (index % 40 == 0) println()
        print(str)
    }
}

fun main() {
    part2()
}
