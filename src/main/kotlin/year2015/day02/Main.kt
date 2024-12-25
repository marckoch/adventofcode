package year2015.day02

import adventofcode.Problem

fun main() {
    AOC2015D02(SAMPLE).run()
    AOC2015D02(INPUT).run()
}

class AOC2015D02(rawInput: String) : Problem(rawInput) {

    private val sides = rawInput.lines()
        .map { line -> line.split("x").map { it.toInt() } }

    override fun solve1(): String {
        return sides.sumOf { calcArea(it) }.toString()
    }

    private fun calcArea(dimensions: List<Int>): Int {
        val (l,w,h) = dimensions
        val minimumArea = minOf(l * w, w * h, h * l)
        return 2 * (l * w) + 2 * (w * h) + 2 * (h * l) + minimumArea
    }

    override fun solve2(): String {
        return sides.sumOf { calcRibbon(it) }.toString()
    }

    private fun calcRibbon(dimensions: List<Int>): Int {
        val (l,w,h) = dimensions
        val minimumDistanceAround = 2 * minOf(l + w, w + h, h + l)
        val volume = l * w * h
        return minimumDistanceAround + volume
    }
}


