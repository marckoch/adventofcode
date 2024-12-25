package year2015.day01

import adventofcode.Problem

fun main() {
    AOC2015D01(INPUT).run()
}

class AOC2015D01(input: String) : Problem(input) {
    val mapChar = { c: Char ->
        when (c) {
            '(' -> 1
            ')' -> -1
            else -> 0
        }
    }

    override fun solve1() = input.sumOf { mapChar(it) }

    override fun solve2(): Int {
        var total = 0

        for ((i, c) in input.withIndex()) {
            total += mapChar(c)
            if (total == -1) {
                return i + 1
            }
        }
        throw IllegalStateException("should never happen")
    }
}
