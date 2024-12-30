package year2016.day03

import adventofcode.Problem

fun main() {
    AOC2016D03(SAMPLE).run()
    AOC2016D03(INPUT).run()
}

class AOC2016D03(input: String) : Problem(input) {
    val numbers = inputLines().map { it.trim().split(Regex("\\s+")).map { s -> s.trim().toInt() } }

    override fun solve1(): Int {
        return numbers.count { isValidTriangle(it) }
    }

    override fun solve2(): Int {
        return numbers.windowed(3, 3).sumOf {
            val left = listOf(it[0][0], it[1][0], it[2][0])
            val middle = listOf(it[0][1], it[1][1], it[2][1])
            val right = listOf(it[0][2], it[1][2], it[2][2])

            listOf(left, middle, right).count { isValidTriangle(it) }
        }
    }

    private fun isValidTriangle(numbers: List<Int>): Boolean {
        val (a, b, c) = numbers
        return a + b > c && b + c > a && c + a > b
    }
}
