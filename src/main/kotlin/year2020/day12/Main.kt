package year2020.day12

import kotlin.math.abs

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

val LINE = """([NSWELRF])(\d*)""".toRegex()

fun part1(input: String) {
    val start = Position()
    println("start: $start")

    var ship = start

    input.lines().forEach {
        LINE.find(it)!!.destructured.toList().let { instruction ->
            val command = instruction[0]
            val steps = instruction[1].toInt()
            ship = when (command) {
                "N" -> ship.move(Direction.NORTH, steps)
                "S" -> ship.move(Direction.SOUTH, steps)
                "W" -> ship.move(Direction.WEST, steps)
                "E" -> ship.move(Direction.EAST, steps)
                "F" -> ship.stepForward(steps)
                "L" -> ship.turn('L', steps / 90)
                "R" -> ship.turn('R', steps / 90)
                else -> throw IllegalArgumentException("computer says no to this instruction: $instruction")
            }
            // println("$it -> $currentPos")
        }
    }
    println("end:   $ship")
    println("part1: ${ship.manhattan(start)}")
}

fun part2(input: String) {
    val start = Position()
    println("start: $start")

    var ship = start
    var waypoint = Position(-1, 10) // always relative to the ship!

    input.lines().forEach {
        LINE.find(it)!!.destructured.toList().let { instruction ->
            val command = instruction[0]
            val steps = instruction[1].toInt()
            when (command) {
                "N" -> waypoint = waypoint.move(Direction.NORTH, steps)
                "S" -> waypoint = waypoint.move(Direction.SOUTH, steps)
                "W" -> waypoint = waypoint.move(Direction.WEST, steps)
                "E" -> waypoint = waypoint.move(Direction.EAST, steps)
                "F" -> ship = ship.moveShipTowardsWaypoint(waypoint, steps)
                "L" -> waypoint = waypoint.rotateAroundShip('L', steps / 90)
                "R" -> waypoint = waypoint.rotateAroundShip('R', steps / 90)
                else -> throw IllegalArgumentException("computer says no to this instruction: $instruction")
            }
            // println("$it -> ship: $ship, waypoint=$waypoint")
        }
    }
    println("end:   $ship")
    println("part1: ${ship.manhattan(start)}")
}

data class Position(val row: Int = 0, val col: Int = 0, val facing: Direction = Direction.EAST) {
    fun moveShipTowardsWaypoint(waypoint: Position, steps: Int) =
        Position(row + (waypoint.row * steps), col + (waypoint.col * steps))

    fun rotateAroundShip(direction: Char, times: Int): Position {
        return if (times == 2) {
            Position(-row, -col)
        } else if ((times == 1 && direction == 'L') || (times == 3 && direction == 'R')) {
            Position(-col, row)
        } else if ((times == 1 && direction == 'R') || (times == 3 && direction == 'L')) {
            Position(col, -row)
        } else
            throw IllegalArgumentException("computer says no to this rotation of waypoint: $direction, $times")
    }

    fun turn(turn: Char, times: Int): Position {
        return List(times) { turn }.fold(this) { acc, c -> acc.turnOnce(c) }
    }

    fun turnOnce(turn: Char): Position {
        return when (Pair(facing, turn)) {
            Pair(Direction.NORTH, 'R') -> copy(facing = Direction.EAST)
            Pair(Direction.EAST, 'R') -> copy(facing = Direction.SOUTH)
            Pair(Direction.SOUTH, 'R') -> copy(facing = Direction.WEST)
            Pair(Direction.WEST, 'R') -> copy(facing = Direction.NORTH)
            Pair(Direction.NORTH, 'L') -> copy(facing = Direction.WEST)
            Pair(Direction.WEST, 'L') -> copy(facing = Direction.SOUTH)
            Pair(Direction.SOUTH, 'L') -> copy(facing = Direction.EAST)
            Pair(Direction.EAST, 'L') -> copy(facing = Direction.NORTH)
            else -> throw IllegalArgumentException("computers says no: illegal facing/turn $facing $turn")
        }
    }

    fun move(direction: Direction, steps: Int): Position {
        return when (direction) {
            Direction.NORTH -> copy(row = row - steps)
            Direction.SOUTH -> copy(row = row + steps)
            Direction.WEST -> copy(col = col - steps)
            Direction.EAST -> copy(col = col + steps)
        }
    }

    fun stepForward(steps: Int): Position {
        return when (facing) {
            Direction.NORTH -> copy(row = row - steps)
            Direction.SOUTH -> copy(row = row + steps)
            Direction.WEST -> copy(col = col - steps)
            Direction.EAST -> copy(col = col + steps)
        }
    }

    fun manhattan(other: Position): Int {
        return abs(row - other.row) + abs(col - other.col)
    }
}

enum class Direction { NORTH, WEST, SOUTH, EAST }
