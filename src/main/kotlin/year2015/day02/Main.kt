package year2015.day02

import adventofcode.Problem
import util.parser.asInts

fun main() {
    AOC2015D02(SAMPLE).run()
    AOC2015D02(INPUT).run()
}

class AOC2015D02(input: String) : Problem(input) {

    private val sides = inputLines().map { line -> line.asInts("x") }

    override fun solve1() = sides.sumOf { calcArea(it) }
    override fun solve2() = sides.sumOf { calcRibbon(it) }

    private fun calcArea(dimensions: List<Int>): Int {
        val (l,w,h) = dimensions
        val minimumArea = minOf(l * w, w * h, h * l)
        return 2 * (l * w) + 2 * (w * h) + 2 * (h * l) + minimumArea
    }

    private fun calcRibbon(dimensions: List<Int>): Int {
        val (l,w,h) = dimensions
        val minimumDistanceAround = 2 * minOf(l + w, w + h, h + l)
        val volume = l * w * h
        return minimumDistanceAround + volume
    }
}


