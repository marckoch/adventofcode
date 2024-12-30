package year2016.day01

import adventofcode.Problem
import util.direction.Direction
import util.point.Point
import util.point.Position
import util.point.manhattanDistanceTo

fun main() {
    AOC2016D01(SAMPLE).run()
    AOC2016D01(SAMPLE2).run()
    AOC2016D01(SAMPLE3).run()
    AOC2016D01(SAMPLE4).run()
    AOC2016D01(INPUT).run()
}

class AOC2016D01(input: String) : Problem(input) {
    val steps = input.split(", ")

    override fun solve1(): Int {
        var pos = Position(Point(0, 0), Direction.NORTH)

        for (s in steps) {
            val turn = s.first()
            val length = s.drop(1).toInt()

            pos = when (turn) {
                'L' -> pos.turnLeft()
                'R' -> pos.turnRight()
                else -> error("Unexpected turn: $turn")
            }
            pos = pos.moveForward(length)
        }

        return pos.point.manhattanDistanceTo(0 to 0)
    }

    override fun solve2(): Int {
        var pos = Position(Point(0, 0), Direction.NORTH)

        val visitedPoints = mutableListOf(pos.point)

        for (s in steps) {
            val turn = s.first()
            val length = s.drop(1).toInt()

            pos = when (turn) {
                'L' -> pos.turnLeft()
                'R' -> pos.turnRight()
                else -> error("Unexpected turn: $turn")
            }
            repeat (length) {
                pos = pos.moveForward(1)

                if (visitedPoints.contains(pos.point)) {
                    return pos.point.manhattanDistanceTo(0 to 0)
                }

                visitedPoints.add(pos.point)
            }
        }
        return 0
    }
}
