package year2024.day01

import kotlin.math.abs

fun main() {
    AOC2024D01(SAMPLE).solvePart1().let { println(it) } // 11
    AOC2024D01(INPUT).solvePart1().let { println(it) }  // 2164381

    AOC2024D01(SAMPLE).solvePart2().let { println(it) } // 31
    AOC2024D01(INPUT).solvePart2().let { println(it) }  // 20719933
}

class AOC2024D01(rawInput: String) {
    val lines = rawInput.lines()
    private val LINE_PATTERN = """(\d*)\s*(\d*)""".toRegex()

    fun solvePart1(): Long {
        // read lines and create two lists of numbers
        val (firstList, secondList) = lines.map {
            val (first, second) = LINE_PATTERN.matchEntire(it)!!.destructured
            Pair(first.toLong(), second.toLong())
        }.unzip() // unzip makes List<Pair<Long, Long>> -> Pair<List<Long>, List<Long>>

        return firstList.sorted().zip(secondList.sorted()).map { abs(it.first - it.second) }.sum()
    }

    fun solvePart2(): Long {
        // read lines and create two lists of numbers
        val (firstList, secondList) = lines.map {
            val (first, second) = LINE_PATTERN.matchEntire(it)!!.destructured
            Pair(first.toLong(), second.toLong())
        }.unzip() // unzip makes List<Pair<Long, Long>> -> Pair<List<Long>, List<Long>>

        val frequencyMapOfSecondList = secondList.groupingBy { it }.eachCount()

        return firstList.sumOf {
            val freq = frequencyMapOfSecondList[it]?.toLong() ?: 0L
            it * freq
        }
    }
}
