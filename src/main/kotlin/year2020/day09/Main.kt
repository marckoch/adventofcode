package year2020.day09

import kotlin.math.abs

fun main() {
    part1(SAMPLE, 5)
    part1(INPUT, 25)

    part2(SAMPLE, 5)
    part2(INPUT, 25)
}

fun part1(input: String, preambleLength: Int) {
    val numbers = input.lines().map { it.toLong() }

    numbers.windowed(preambleLength + 1)
        .map { numberNotComposable(it, preambleLength) }
        .filter { it > 0 }
        .forEach { println("part1: $it") }
}

fun part2(input: String, preambleLength: Int) {
    val numbers = input.lines().map { it.toLong() }

    numbers.windowed(preambleLength + 1)
        .map { numberNotComposable(it, preambleLength) }
        .first { it > 0 }
        .let { findContigousSet(numbers, it) }
}

fun findContigousSet(numbers: List<Long>, target: Long) {
    for (from in numbers.indices) {
        for (to in from + 2..numbers.lastIndex) { // + 2 because we want at least 2 elements in list
            val s = numbers.subList(from, to)
            if (s.sum() == target) {
                println("from=$from to=$to in $s = $target")
                val x = s.min() + s.max()
                println("part2: $x")
            }
        }
    }
}

fun numberNotComposable(window: List<Long>, preambleLength: Int): Long {
    val preamble = window.take(preambleLength)
    val targetSum = window.last()

    val validPreamble = preamble.filter { it < targetSum }.toSet() // ignore numbers that are too big

    // build set of opposite numbers
    val red = validPreamble.map { abs(it - targetSum) }.toSet()

    return if ((validPreamble intersect red).isEmpty()) targetSum else 0
}