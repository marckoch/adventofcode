package year2016.day15

import adventofcode.Problem
import util.lists.areAllEqual

fun main() {
    AOC2016D15(SAMPLE).run()
    AOC2016D15(INPUT).run()
}

data class Disc(val positions: Int, val currentPosition: Int) {
    companion object {
        fun from(s: String): Disc {
            val tokens = s.split(" ")
            return Disc(tokens[3].toInt(), tokens[11].dropLast(1).toInt())
        }
    }

    fun atTime(time: Int): Disc {
        return this.copy(currentPosition = (currentPosition + time) % positions)
    }
}

class AOC2016D15(input: String) : Problem(input) {
    private val initialDiscs = inputLines().map { Disc.from(it) }

    override fun solve1() = solve(initialDiscs)
    override fun solve2() = solve(initialDiscs + listOf(Disc(11, 0)))

    fun solve(discs: List<Disc>): Int {
        return generateSequence(0, Int::inc)
            .first { time ->
                discs.withIndex()
                    .map { it.value.atTime(time + it.index + 1) }
                    .map { it.currentPosition }
                    .areAllEqual()
            }
    }

}
