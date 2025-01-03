package year2016.day20

import adventofcode.Problem
import kotlin.math.max

fun main() {
    AOC2016D20(SAMPLE).run()
    AOC2016D20(INPUT).run()
}

class AOC2016D20(input: String) : Problem(input) {
    private val rules = inputLines().map { line -> line.split("-").map { it.toUInt() }.let { it[0] to it[1] } }

    override fun solve1() = findFirstAllowed()
    override fun solve2() = countAllowed()

    // sort the rules and find first gap
    private fun findFirstAllowed(): UInt {
        val sorted = rules.sortedBy { it.first }
        var lastBlocked = 0U
        for ((from, to) in sorted) {
            if (lastBlocked + 1U < from) {
                return lastBlocked + 1U
            }
            lastBlocked = max(to, lastBlocked)
        }
        error("nothing found!")
    }

    // sort the rules and calculate/add free gaps between them
    private fun countAllowed(): UInt {
        val sorted = rules.sortedBy { it.first }
        var count = 0U
        var lastBlocked = 0U
        for ((from, to) in sorted) {
            if (lastBlocked - 1U < from) {
                count += (from - lastBlocked - 1U)
            }
            lastBlocked = max(to, lastBlocked)
        }

        return count + UInt.MAX_VALUE - lastBlocked
    }

    // SLOW !
    fun solveNaive(): Int {
        return generateSequence(0, Int::inc).first {
                i -> !blocked(i.toUInt())
        }
    }

    private fun blocked(i: UInt): Boolean {
        return rules.any { (from, to) -> i in (from .. to) }
    }
}
