package year2023.day04

import kotlin.math.pow

val LINE_PATTERN = """Card ([\d ]+): ([\d ]+) \| ([\d ]+)""".toRegex()

fun main() {
    part1(SAMPLE) // 13
    part1(INPUT)  // 23441

    part2(SAMPLE) // 30
    part2(INPUT)  // 5923918
}

fun part1(input: String) {
    input.lines()
        .map { line ->
            val (g, w, n) = LINE_PATTERN.matchEntire(line)!!.destructured
            // println("g=[$g] w=[$w] n=[$n]")
            // val gameNumber = g.trim().toInt()

            val winningNumbers = extractNumberSet(w)
            val numbers = extractNumberSet(n)

            val matches = winningNumbers.intersect(numbers).size

            if (matches > 0) {
                val worth = 2.0.pow(matches - 1).toInt()
                // println("game $gameNumber: $matches matches -> worth ${worth}")
                worth
            } else
                0
        }
        .sumOf { it }
        .let { println(it) }
}

fun part2(input: String) {
    val noOfCards = input.lines().count()
    val cards = Array(noOfCards) { 1 } // we start with one of each card

    input.lines()
        .forEachIndexed { index, line ->
            val (g, w, n) = LINE_PATTERN.matchEntire(line)!!.destructured
            // println("g=[$g] w=[$w] n=[$n]")
            // val gameNumber = g.trim().toInt()

            val winningNumbers = extractNumberSet(w)
            val numbers = extractNumberSet(n)

            val noOfWonCards = winningNumbers.intersect(numbers).size
            // println("game $gameNumber: noOfWonCards $noOfWonCards")

            if (noOfWonCards > 0) {
                // add current card count to all 'noOfWonCards' cards that are after it
                for (i in 1  until noOfWonCards + 1) {
                    cards[index + i] += cards[index]
                }
            }

            // println(cards.toList())
        }

    println(cards.sum())
}

fun extractNumberSet(s: String): Set<Int> {
    return s.trim()
        .split("\\s+".toRegex())
        .map { it.toInt() }
        .toSet()
}