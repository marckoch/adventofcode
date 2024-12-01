package year2024.day01

import kotlin.math.abs

val LINE_PATTERN = """(\d*)\s*(\d*)""".toRegex()

fun main() {
    part1(SAMPLE).let { println(it) } // 11
    part1(INPUT).let { println(it) }  // 2164381

    part2(SAMPLE).let { println(it) } // 31
    part2(INPUT).let { println(it) }  // 20719933
}

fun part1(input: String): Long {
    // read lines and create two lists of numbers
    val (firstList, secondList) = input.lines().map {
        val (first, second) = LINE_PATTERN.matchEntire(it)!!.destructured
        Pair(first.toLong(), second.toLong())
    }.unzip() // unzip makes List<Pair<Long, Long>> -> Pair<List<Long>, List<Long>>

    return firstList.sorted().zip(secondList.sorted()).map { abs(it.first - it.second) }.sum()
}

fun part2(input: String): Long {
    // read lines and create two lists of numbers
    val (firstList, secondList) = input.lines().map {
        val (first, second) = LINE_PATTERN.matchEntire(it)!!.destructured
        Pair(first.toLong(), second.toLong())
    }.unzip() // unzip makes List<Pair<Long, Long>> -> Pair<List<Long>, List<Long>>

    val frequencyMapOfSecondList = secondList.groupingBy { it }.eachCount()

    return firstList.sumOf {
        val freq = frequencyMapOfSecondList[it]?.toLong() ?: 0L
        it * freq
    }
}