package day09

import java.io.File


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun part1() {
    var input = readFileAsLinesUsingUseLines("day09/input.txt")
    var headPosition = Pair(0, 0)
    var tailPosition = Pair(0, 0)
    var tailPositions = ArrayList<Pair<Int, Int>>()
    tailPositions.add(tailPosition)
    for (line in input) {
        var values = line.split(" ")
        val direction = values[0]
        for (step in 1..Integer.parseInt(values[1])) {
            when (direction) {
                "R" -> headPosition = headPosition.copy(second = headPosition.second + 1)
                "U" -> headPosition = headPosition.copy(first = headPosition.first - 1)
                "L" -> headPosition = headPosition.copy(second = headPosition.second - 1)
                "D" -> headPosition = headPosition.copy(first = headPosition.first + 1)
            }
            //move tail
            if (!headTouchingTail(headPosition, tailPosition)) {
                if (headPosition.first == tailPosition.first) {
                    tailPosition = if (headPosition.second > tailPosition.second)
                        tailPosition.copy(second = tailPosition.second + 1)
                    else tailPosition.copy(second = tailPosition.second - 1)
                } else if (headPosition.second == tailPosition.second) {
                    tailPosition = if (headPosition.first > tailPosition.first)
                        tailPosition.copy(first = tailPosition.first + 1)
                    else tailPosition.copy(first = tailPosition.first - 1)
                } else if (headPosition.first > tailPosition.first && headPosition.second > tailPosition.second) {
                    tailPosition = Pair(tailPosition.first + 1, tailPosition.second + 1)
                } else if (headPosition.first < tailPosition.first && headPosition.second > tailPosition.second) {
                    tailPosition = Pair(tailPosition.first - 1, tailPosition.second + 1)
                } else if (headPosition.first > tailPosition.first && headPosition.second < tailPosition.second) {
                    tailPosition = Pair(tailPosition.first + 1, tailPosition.second - 1)
                } else if (headPosition.first < tailPosition.first && headPosition.second < tailPosition.second) {
                    tailPosition = Pair(tailPosition.first - 1, tailPosition.second - 1)
                }
            }
            tailPositions.add(tailPosition)
        }
    }
    println(tailPositions.distinct().size)
}

fun headTouchingTail(headPosition: Pair<Int, Int>, tailPosition: Pair<Int, Int>): Boolean {
    if (headPosition == tailPosition) return true
    if (headPosition.first == tailPosition.first
        && (headPosition.second == tailPosition.second + 1
                || headPosition.second == tailPosition.second - 1)
    ) {
        return true
    }
    if (headPosition.second == tailPosition.second
        && (headPosition.first == tailPosition.first + 1
                || headPosition.first == tailPosition.first - 1)
    ) {
        return true
    }
    if ((headPosition.first == tailPosition.first + 1 || headPosition.first == tailPosition.first - 1)
        &&
        (headPosition.second == tailPosition.second + 1 || headPosition.second == tailPosition.second - 1)
    )
        return true


    return false
}


fun part2() {
    var input = readFileAsLinesUsingUseLines("day09/input.txt")
    var positions = Array(10) { Pair(0, 0) }
    var tailPositions = ArrayList<Pair<Int, Int>>()
    tailPositions.add(positions.last())
    for (line in input) {
        var values = line.split(" ")
        val direction = values[0]
        for (step in 1..Integer.parseInt(values[1])) {
            when (direction) {
                "R" -> positions[0] = positions[0].copy(second = positions[0].second + 1)
                "U" -> positions[0] = positions[0].copy(first = positions[0].first - 1)
                "L" -> positions[0] = positions[0].copy(second = positions[0].second - 1)
                "D" -> positions[0] = positions[0].copy(first = positions[0].first + 1)
            }
            //move tail
            for (index in 1 until positions.size) {
                if (!headTouchingTail(positions[index-1], positions[index])) {
                    if (positions[index-1].first == positions[index].first) {
                        positions[index] = if (positions[index-1].second > positions[index].second)
                            positions[index].copy(second = positions[index].second + 1)
                        else positions[index].copy(second = positions[index].second - 1)
                    } else if (positions[index-1].second == positions[index].second) {
                        positions[index] = if (positions[index-1].first > positions[index].first)
                            positions[index].copy(first = positions[index].first + 1)
                        else positions[index].copy(first = positions[index].first - 1)
                    } else if (positions[index-1].first > positions[index].first && positions[index-1].second > positions[index].second) {
                        positions[index] = Pair(positions[index].first + 1, positions[index].second + 1)
                    } else if (positions[index-1].first < positions[index].first && positions[index-1].second > positions[index].second) {
                        positions[index] = Pair(positions[index].first - 1, positions[index].second + 1)
                    } else if (positions[index-1].first > positions[index].first && positions[index-1].second < positions[index].second) {
                        positions[index] = Pair(positions[index].first + 1, positions[index].second - 1)
                    } else if (positions[index-1].first < positions[index].first && positions[index-1].second < positions[index].second) {
                        positions[index] = Pair(positions[index].first - 1, positions[index].second - 1)
                    }
                }
                tailPositions.add(positions.last())
            }


        }
    }
    println(tailPositions.distinct().size)


}

fun main() {
    part2()
}
