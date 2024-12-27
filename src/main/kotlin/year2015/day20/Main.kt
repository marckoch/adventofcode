package year2015.day20

import adventofcode.Problem

fun main() {
    AOC2015D20(SAMPLE).run()
    AOC2015D20(INPUT).run()
}

class AOC2015D20(input: String) : Problem(input) {
    override fun solve1(): Int {
        val numPresents = input.toInt()
        val houses = IntArray(numPresents / 10)
        for (i in 1 until houses.size) {
            (i until houses.lastIndex step i).asSequence().forEach { j ->
                houses[j] += i * 10
            }
        }
        return houses.indexOfFirst { it >= numPresents }
    }

    override fun solve2(): Int {
        val numPresents = input.toInt()
        val houses = IntArray(numPresents / 11)
        for (i in 1 until houses.size) {
            (i until houses.lastIndex step i).asSequence().take(50).forEach { j ->
                houses[j] += i * 11
            }
        }
        return houses.indexOfFirst { it >= numPresents }
    }
}
