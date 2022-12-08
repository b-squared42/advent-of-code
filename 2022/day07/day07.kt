package day07

import java.io.File


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun part1() {
    var input = readFileAsLinesUsingUseLines("day07/input.txt")
    var rootDir = Dir(null, "/")
    var currentDir = rootDir
    for (line in input) {
        var parts = line.split(" ")
        if (parts[0] == "$") {
            if (parts[1] == "cd" && parts[2] == "..") {
                currentDir = currentDir.parent
            } else if (parts[1] == "cd" && parts[2] != "/") {
                var newDir = Dir(currentDir, parts[2])
                currentDir.addDir(newDir)
                currentDir = newDir
            }
        } else if (parts[0] != "dir") {
            currentDir.addFile(File(currentDir, parts[1], Integer.parseInt(parts[0])))
        }
    }
    println(rootDir.getMaxSizedDirs(100000).sum())
}


fun part2() {
    var input = readFileAsLinesUsingUseLines("day07/input.txt")
    var rootDir = Dir(null, "/")
    var currentDir = rootDir
    for (line in input) {
        var parts = line.split(" ")
        if (parts[0] == "$") {
            if (parts[1] == "cd" && parts[2] == "..") {
                currentDir = currentDir.parent
            } else if (parts[1] == "cd" && parts[2] != "/") {
                var newDir = Dir(currentDir, parts[2])
                currentDir.addDir(newDir)
                currentDir = newDir
            }
        } else if (parts[0] != "dir") {
            currentDir.addFile(File(currentDir, parts[1], Integer.parseInt(parts[0])))
        }
    }
    var sizeToFree = 30000000 - (70000000 - rootDir.getSize())
    var allSizes = rootDir.getMaxSizedDirs(Int.MAX_VALUE)
    println(allSizes.filter { it > sizeToFree }.sortedBy { it }[0])
}

fun main() {
    part2()
}
