package year2015.day06

import adventofcode.Problem
import util.parser.findTokens

fun main() {
    AOC2015D06(INPUT).run()
}

class AOC2015D06(input: String) : Problem(input) {
    val LINE_PATTERN = """(turn on|turn off|toggle) (\d*),(\d*) through (\d*),(\d*)""".toRegex()

    override fun solve1(): Int {
        val initialLights = Array(1000) { BooleanArray(1000) { false } }

        fun apply(command: String, acc: Array<BooleanArray>, x: Int, y: Int) {
            when (command) {
                "turn on" -> acc[x][y] = true
                "turn off" -> acc[x][y] = false
                "toggle" -> acc[x][y] = !acc[x][y]
            }
        }

        val processedLights = process(initialLights, ::apply)

        return processedLights.sumOf { row -> row.count { it } }
    }

    override fun solve2(): Int {
        val initialLights = Array(1000) { IntArray(1000) { 0 } }

        fun apply(command: String, acc: Array<IntArray>, x: Int, y: Int) {
            when (command) {
                "turn on" -> acc[x][y] += 1
                "turn off" -> {
                    if (acc[x][y] > 0) acc[x][y] -= 1
                }
                "toggle" -> acc[x][y] += 2
            }
        }

        val processedLights = process(initialLights, ::apply)

        return processedLights.sumOf { row -> row.sum() }
    }

    private fun <T> process(initialGrid: Array<T>, commandFn: (String, Array<T>, Int, Int) -> Unit): Array<T> {
        return inputLines().fold(initialGrid) { acc, line ->
            val (command, _) = LINE_PATTERN.findTokens(line)
            val (aX, aY, bX, bY) = LINE_PATTERN.findTokens(line).drop(1).map { it.toInt() }

            for (x in aX..bX) {
                for (y in aY..bY) {
                    commandFn(command, acc, x, y)
                }
            }
            acc
        }
    }
}
