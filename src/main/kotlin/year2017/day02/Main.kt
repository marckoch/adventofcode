package year2017.day02

import adventofcode.Problem
import util.lists.cartesianProduct
import util.parser.asInts

fun main() {
    AOC2017D02(SAMPLE).run1()
    AOC2017D02(SAMPLE2).run2()
    AOC2017D02(INPUT).run()
}

class AOC2017D02(input: String) : Problem(input) {
    override fun solve1() : Int {
        return inputLines().fold(0) { acc, line ->
            val numbers = line.asInts()
            acc + numbers.max() - numbers.min()
        }
    }

    override fun solve2() : Int {
        return inputLines().fold(0) { acc, line ->
            val numbers = line.asInts()
            val divResult = numbers.cartesianProduct(numbers)
                .filter { (n1, n2) -> n1 > n2 }
                .filter { (n1, n2) -> n1 % n2 == 0 }
                .map { (n1, n2) -> n1 / n2 }
                .first()
            acc + divResult
        }
    }
}
