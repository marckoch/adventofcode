package year2016.day02

import adventofcode.Problem
import util.point.Point
import util.point.manhattanDistanceTo

fun main() {
    AOC2016D02(SAMPLE).run()
    AOC2016D02(INPUT).run()
}

class AOC2016D02(input: String) : Problem(input) {
    override fun solve1(): String {
        val keypad = listOf(
            "123",
            "456",
            "789")
        val startPos = Point(1, 1) // key '5'
        fun isPosOnKeypad(p: Point) = p.first in 0..2 && p.second in 0..2

        return buildCode(keypad, startPos, ::isPosOnKeypad)
    }

    override fun solve2(): String {
        val keypad = listOf(
            "  1  ",
            " 234 ",
            "56789",
            " ABC ",
            "  D  ")
        val startPos = Point(2, 0) // key '5'
        fun isPosOnKeypad(p: Point) = p.manhattanDistanceTo(Point(2, 2)) <= 2

        return buildCode(keypad, startPos, ::isPosOnKeypad)
    }

    private fun buildCode(keypad: List<String>, startPos: Point, isPosOnKeypad: (Point) -> Boolean) : String {
        val instructions = inputLines()
        val code = mutableListOf<Char>()

        var pos = startPos
        for (i in instructions) {
            pos = followInstructions(pos, i, isPosOnKeypad)

            code.add(keypad[pos.first][pos.second])
        }

        return code.joinToString("")
    }

    private fun followInstructions(pos: Point, instruction: String, isPosOnKeypad: (Point) -> Boolean): Point {
        var newPos = pos

        for (c in instruction) {
            val next = when (c) {
                'U' ->  newPos.copy(first = newPos.first - 1)
                'D' ->  newPos.copy(first = newPos.first + 1)
                'L' ->  newPos.copy(second = newPos.second - 1)
                'R' ->  newPos.copy(second = newPos.second + 1)
                else ->  newPos
            }

            // check if next step is still on keypad
            newPos = if (isPosOnKeypad(next)) next else newPos
        }

        return newPos
    }
}
