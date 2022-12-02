package day02

import java.io.File

class Element {
    var value: Int
    var letters: Array<String>

    companion object {
        var ROCK: Element = Element(1, arrayOf("A", "X"))
        var PAPER: Element = Element(2, arrayOf("B", "Y"))
        var SCISSOR: Element = Element(3, arrayOf("C", "Z"))

        fun getElementByValue(value: Int): Element {
            if (ROCK.value == value) return ROCK
            if (PAPER.value == value) return PAPER
            if (SCISSOR.value == value) return SCISSOR
            throw Exception("Unkown element! $value")
        }

        fun getElementPart1(input: String): Element {
            if (ROCK.letters.contains(input)) return ROCK
            if (PAPER.letters.contains(input)) return PAPER
            if (SCISSOR.letters.contains(input)) return SCISSOR
            throw Exception("Unkown element!")
        }

        fun getElementPart2(opponent: Element, input: String): Element {
            if (input == "X") {
                var myValue = opponent.value - 1
                return getElementByValue(if (myValue == 0) 3 else myValue)
            }
            if (input == "Y") return getElementByValue(opponent.value)
            if (input == "Z") {
                var myValue = opponent.value + 1
                return getElementByValue(if (myValue == 4) 1 else myValue)
            }
            throw Exception("Unkown element! $input")
        }
    }

    private constructor(value: Int, letters: Array<String>) {
        this.value = value
        this.letters = letters
    }

    override fun toString(): String {
        return value.toString()
    }
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }
fun outcome(opponent: Element, me: Element): Int {
    var winresult = 0;
    if (opponent == me) winresult += 3
    if (opponent.value - me.value == -1 ||
        opponent.value - me.value == 2) winresult += 6
    winresult += me.value
    return winresult
}

fun part1() {
    var input = readFileAsLinesUsingUseLines("day02/input.txt")
    var result = 0
    for (line in input) {
        var picks = line.split(" ")
        result += outcome(Element.getElementPart1(picks[0]), Element.getElementPart1(picks[1]))
    }
    println(result)
}

fun part2() {
    var input = readFileAsLinesUsingUseLines("day02/input.txt")
    var result = 0
    for (line in input) {
        var picks = line.split(" ")
        var opponent = Element.getElementPart1(picks[0])
        var myElement = Element.getElementPart2(opponent, picks[1])
        result += outcome(opponent, myElement)
    }
    println(result)
}

fun main() {
    part2()
}
