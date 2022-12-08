package day08

import java.io.File


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun part1() {
    var input = readFileAsLinesUsingUseLines("day08/input.txt")
    var arr = ArrayList<List<Int>>()
    for (line in input) {
        arr.add(line.split("").filter { it.trim().isNotEmpty() }.map { Integer.parseInt(it) })
    }
    //            y  x
    //println(arr[1][3]) // => 1
    var result = arr.size * 2 + (arr[0].size - 2) * 2
    for (y in 1 until arr.size - 1) {
        for (x in 1 until arr[y].size - 1) {
            var tree = arr[y][x]
            var hidden = arrayOf(false, false, false, false)
            for (treeY in 0 until y) {
                if (arr[treeY][x] >= tree) {
                    hidden[0] = true
                    break
                }
            }
            for (treeY in y + 1 until arr.size) {
                if (arr[treeY][x] >= tree) {
                    hidden[2] = true
                    break
                }
            }
            for (treeX in 0 until x) {
                if (arr[y][treeX] >= tree) {
                    hidden[3] = true
                    break
                }
            }
            for (treeX in x + 1 until arr[y].size) {
                if (arr[y][treeX] >= tree) {
                    hidden[1] = true
                    break
                }
            }
            if (hidden.any { !it }) result++
        }

    }
    println(result)
}


fun part2() {
    var input = readFileAsLinesUsingUseLines("day08/input.txt")
    var arr = ArrayList<List<Int>>()
    for (line in input) {
        arr.add(line.split("").filter { it.trim().isNotEmpty() }.map { Integer.parseInt(it) })
    }
    //            y  x
    //println(arr[1][3]) // => 1
    var highestScenicScore = 0
    for (y in 1 until arr.size - 1) {
        for (x in 1 until arr[y].size - 1) {
            var tree = arr[y][x]
            var scenicScore = arrayOf(0, 0, 0, 0)
            for (treeY in y-1 downTo 0) {
                scenicScore[0]++
                if (arr[treeY][x] >= tree) {
                    break
                }
            }
            for (treeY in y + 1 until arr.size) {
                scenicScore[2]++
                if (arr[treeY][x] >= tree) {
                    break
                }
            }
            for (treeX in  x-1 downTo 0) {
                scenicScore[3]++
                if (arr[y][treeX] >= tree) {
                    break
                }
            }
            for (treeX in x + 1 until arr[y].size) {
                scenicScore[1]++
                if (arr[y][treeX] >= tree) {
                    break
                }
            }
            println("$tree => T: ${scenicScore[0]} | R: ${scenicScore[1]} | B: ${scenicScore[2]} | L: ${scenicScore[3]}")
            var treeScore = 1
            scenicScore.forEach { treeScore *= it }
            highestScenicScore = if (treeScore > highestScenicScore) treeScore else highestScenicScore
        }

    }
    println(highestScenicScore)

}

fun main() {
    part2()
}
