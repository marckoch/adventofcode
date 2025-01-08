package year2017.day01

import adventofcode.Problem

fun main() {
    AOC2017D01(INPUT).run()
}

class AOC2017D01(input: String) : Problem(input) {
    override fun solve1() = solve(offset = 1)
    override fun solve2() = solve(offset = input.length / 2)

    fun solve(offset: Int): Int {
        return input.toCharArray()
            .map { it.digitToInt() }
            .withIndex()
            .filter { (i, n) -> n == input[(i + offset) % input.length].digitToInt() }
            .sumOf { it.value }
    }
}
