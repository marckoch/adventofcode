package year2016.day06

import adventofcode.Problem

fun main() {
    AOC2016D06(SAMPLE).run()
    AOC2016D06(INPUT).run()
}

class AOC2016D06(input: String) : Problem(input) {

    override fun solve1(): String {
        return decode { map -> map.maxBy { entry -> entry.value }.key }
    }

    override fun solve2(): String {
        return decode { map -> map.minBy { entry -> entry.value }.key }
    }

    private fun decode(selectChar: (Map<Char, Int>) -> Char): String {
        val length = inputLines().first().length
        val columns = inputLines().fold(Array(length) { mutableListOf<Char>() }) { acc, line ->
            for ((i, c) in line.withIndex()) {
                acc[i].add(c)
            }
            acc
        }
        return columns
            .map { col ->
                val freqMap = col.groupingBy { it }.eachCount()
                selectChar(freqMap)
            }.joinToString(separator = "")
    }
}
