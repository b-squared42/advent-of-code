package day04

import readFileAsLinesUsingUseLines
import kotlin.math.pow

class Day04 {
    fun part1() {
        val input = readFileAsLinesUsingUseLines("2023/day04/input.txt")
        val numberLists = input.map { it.split(":")[1] }.map { it.split("|") }
        val winningNumberLists =
            numberLists.map { strings -> strings[0].split(" ").filter { it.isNotEmpty() }.map { it.toInt() } }
        val drawnNumberLists =
            numberLists.map { strings -> strings[1].split(" ").filter { it.isNotEmpty() }.map { it.toInt() } }

        var points = 0.0
        for ((winningNumberListIndex, winningNumberList) in winningNumberLists.withIndex()) {
            val amountOfCommons = winningNumberList.countCommons(drawnNumberLists[winningNumberListIndex])
            if (amountOfCommons > 0) points += 2.0.pow(amountOfCommons - 1)
        }
        println(points)
    }

    fun part2() {
        val input = readFileAsLinesUsingUseLines("2023/day04/input.txt")
        val cards = List(input.size) { index -> index+1 }.associateWith { 1 }.toMap().toMutableMap()
        val numberLists = input.map { it.split(":")[1] }.map { it.split("|") }
        val winningNumberLists =
            numberLists.map { strings -> strings[0].split(" ").filter { it.isNotEmpty() }.map { it.toInt() } }
        val drawnNumberLists =
            numberLists.map { strings -> strings[1].split(" ").filter { it.isNotEmpty() }.map { it.toInt() } }

        for ((winningNumberListIndex, winningNumberList) in winningNumberLists.withIndex()) {
            val amountOfCommons = winningNumberList.countCommons(drawnNumberLists[winningNumberListIndex])
            for (i in 1 until amountOfCommons+1) {
                cards[winningNumberListIndex+1+i] = cards[winningNumberListIndex+1+i]!! + cards[winningNumberListIndex+1]!!
            }
        }
        println(cards.values.sum())
    }

    private fun <T> List<T>.countCommons(other: List<T>) =
        this.intersect(other.toSet()).count()

}


fun main() {
    val d = Day04()
    d.part1()
    println("--------------------")
    d.part2()
}
