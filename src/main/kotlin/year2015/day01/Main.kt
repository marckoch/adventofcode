package year2015.day01

import adventofcode.Problem

fun main() {
    AOC2015D01(INPUT).run()
}

class AOC2015D01(rawInput: String) : Problem(rawInput) {
    val mapChar = { c: Char ->
        when (c) {
            '(' -> 1
            ')' -> -1
            else -> 0
        }
    }

    override fun solve1(): String {
        return rawInput.sumOf { mapChar(it) }.toString()
    }

    override fun solve2(): String {
        var total = 0

        for ((i, c) in rawInput.withIndex()) {
            total += mapChar(c)
            if (total == -1) {
                return "${i + 1}"
            }
        }
        throw IllegalStateException("should never happen")
    }
}
