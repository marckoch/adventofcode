package year2015.day25

import adventofcode.Problem

fun main() {
    AOC2015D25("").run1() // 8997277
}

class AOC2015D25(input: String) : Problem(input) {
    override fun solve1(): Long {
        val g = generateSequence(20151125L) { (it * 252533L) % 33554393L }
        val row = 3010
        val col = 3019
        val n = calcN(row, col)
        return g.elementAt(n - 1) // -1 because starts at 0 like array
    }

    // calculate index of element is row/col
    // e.g. calcN(4, 3) -> 18
    // deduced just by observing the pattern
    private fun calcN(row: Int, col: Int): Int {
        val firstInRow = (1 + sumOneTo(row - 1))
        return firstInRow + (row * (col - 1)) + sumOneTo(col - 1)
    }

    private fun sumOneTo(n: Int) = n * (n + 1) / 2

    override fun solve2() = 1 // no part2 at last day
}

