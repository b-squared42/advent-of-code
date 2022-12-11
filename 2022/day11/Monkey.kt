package day11

import java.math.BigInteger

class Monkey {
    var items: ArrayList<BigInteger>
    var operation: (BigInteger) -> BigInteger
    var test: (BigInteger) -> Boolean
    var trueIndex: Int
    var falseIndex: Int
    var inspections: BigInteger = 0.toBigInteger()

    constructor(
        items: ArrayList<BigInteger>,
        operation: (BigInteger) -> BigInteger,
        test: (BigInteger) -> Boolean,
        trueIndex: Int,
        falseIndex: Int
    ) {
        this.items = items
        this.operation = fun(i: BigInteger): BigInteger {
            inspections++
            return operation(i)
        }
        this.test = test
        this.trueIndex = trueIndex
        this.falseIndex = falseIndex
    }

    fun getNextIndex(i: BigInteger): Int {
        if (test(i))
            return trueIndex
        return falseIndex
    }
}
