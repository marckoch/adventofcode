package year2015.day17

import adventofcode.Problem
import util.lists.powerSetBitwise

fun main() {
    AOC2015D17(SAMPLE, 25).run()
    AOC2015D17(INPUT, 150).run()
}

class AOC2015D17(input: String, private val liters: Int) : Problem(input) {
    private val containers = inputLines().map { it.toInt() }.sorted().reversed()
    private val powerSets = containers.powerSetBitwise()

    override fun solve1(): Int {
        return powerSets.count { it.sum() == liters }
    }

    override fun solve2(): Any {
        val minSize = powerSets.filter { it.sum() == liters }.minOf { list -> list.size }

        return powerSets.count { it.sum() == liters && it.size == minSize }
    }
}
