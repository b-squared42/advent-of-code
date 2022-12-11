package day11

import java.io.File
import java.math.BigInteger


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toMutableList() }

fun part1() {
    var input = readFileAsLinesUsingUseLines("day11/input.txt")
    var monkeyList = ArrayList<Monkey>()
    for (lineIndex in 0..input.size step 7) {
        var items = getItemsFromLine(input[lineIndex + 1])
        var operation = getOperationFromLine(input[lineIndex + 2])
        var test = getTestFromLine(input[lineIndex + 3])
        var trueIndex = Integer.parseInt(input[lineIndex + 4].split("monkey ")[1])
        var falseIndex = Integer.parseInt(input[lineIndex + 5].split("monkey ")[1])
        monkeyList.add(Monkey(items, operation, test, trueIndex, falseIndex))
    }

    for (monkeyIndex in 0 until monkeyList.size) {
        for (itemIndex in 0 until monkeyList[monkeyIndex].items.size) {
            var item = monkeyList[monkeyIndex].items[itemIndex]
            var operationMonkeyIndex = monkeyIndex
            for (round in 1..20) {
                var pair = round(item, monkeyList, operationMonkeyIndex, true)
                item = pair.first
                operationMonkeyIndex = pair.second
            }
        }
    }

    println(monkeyList.map { it.inspections }.joinToString())
    monkeyList.sortBy { -it.inspections }
    println(monkeyList[0].inspections)
    println(monkeyList[1].inspections)
    println(monkeyList[0].inspections * monkeyList[1].inspections) //54752
}

fun round(item: BigInteger, monkeyList: ArrayList<Monkey>, oldOperationMonkeyIndex: Int, divide: Boolean): Pair<BigInteger, Int>{
    var operationMonkey = monkeyList[oldOperationMonkeyIndex]
    var newItem = operationMonkey.operation(item)
    if (divide) newItem = newItem.div(3.toBigInteger())
    var newOperationMonkeyIndex = operationMonkey.getNextIndex(newItem)
    if (newOperationMonkeyIndex > oldOperationMonkeyIndex) {
        return round(newItem, monkeyList, newOperationMonkeyIndex, divide)
    }
    return Pair(newItem, newOperationMonkeyIndex)
}
fun getTestFromLine(line: String): (BigInteger) -> Boolean {
    var testDivisor = line.split("by ")[1].toBigInteger()
    return fun(old: BigInteger): Boolean {
        return old.rem(testDivisor) == 0.toBigInteger()
    }
}

fun getOperationFromLine(line: String): (BigInteger) -> BigInteger {
    var operationString = line.split("= ")[1]
    var operatorString = operationString.split(" ")[1]
    var operandLeftString = operationString.split(" ")[0]
    var operandRightString = operationString.split(" ")[2]
    return fun(old: BigInteger): BigInteger {
        var operandLeft = if (operandLeftString == "old") old else operandLeftString.toBigInteger()
        var operandRight = if (operandRightString == "old") old else operandRightString.toBigInteger()

        return when (operatorString) {
            "+" -> {
                operandLeft + operandRight
            }

            "-" -> {
                operandLeft - operandRight
            }

            "*" -> {
                operandLeft * operandRight
            }

            "/" -> {
                operandLeft / operandRight
            }

            else -> throw Exception("Unknown operator!")
        }
    }
}

fun getItemsFromLine(line: String): ArrayList<BigInteger> {
    var items = ArrayList<BigInteger>()
    var itemsString = line.split(": ")[1].split(", ")
    for (itemString in itemsString) {
        items.add(itemString.toBigInteger())
    }
    return items
}


fun part2() {
    var input = readFileAsLinesUsingUseLines("day11/input.txt")
    var monkeyList = ArrayList<Monkey>()
    var masterDivisor = 1.toBigInteger()
    for (lineIndex in 0..input.size step 7) {
        var items = getItemsFromLine(input[lineIndex + 1])
        var operation = getOperationFromLine(input[lineIndex + 2])
        var test = getTestFromLine(input[lineIndex + 3])
        masterDivisor *= input[lineIndex + 3].split("by ")[1].toBigInteger()
        var trueIndex = Integer.parseInt(input[lineIndex + 4].split("monkey ")[1])
        var falseIndex = Integer.parseInt(input[lineIndex + 5].split("monkey ")[1])
        monkeyList.add(Monkey(items, operation, test, trueIndex, falseIndex))
    }

    for (monkeyIndex in 0 until monkeyList.size) {
        for (itemIndex in 0 until monkeyList[monkeyIndex].items.size) {
            var item = monkeyList[monkeyIndex].items[itemIndex]
            var operationMonkeyIndex = monkeyIndex
            for (round in 1..10000) {
                var pair = round(item, monkeyList, operationMonkeyIndex, false)
                item = pair.first
                while (item > masterDivisor) item -= masterDivisor
                operationMonkeyIndex = pair.second
                if (round%1000==0) println("Finished round $round of $monkeyIndex/${monkeyList.size-1} & $itemIndex/${monkeyList[monkeyIndex].items.size-1}. Item: $item")
            }
        }
    }

    println(monkeyList.map { it.inspections }.joinToString())
    monkeyList.sortBy { -it.inspections }
    println(monkeyList[0].inspections)
    println(monkeyList[1].inspections)
    println(monkeyList[0].inspections * monkeyList[1].inspections) //54752
}

fun main() {
    part2()
}
