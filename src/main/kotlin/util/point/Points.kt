package util.point

import util.graph.Direction
import kotlin.math.abs

typealias Point = Pair<Int, Int> // (row, col), e.g. (y, x)

fun Point.neighbors(): List<Point> {
    val north = this.copy(first = first - 1)
    val south = this.copy(first = first + 1)
    val west = this.copy(second = second - 1)
    val east = this.copy(second = second + 1)
    return listOf(north, south, west, east)
}

fun Point.dirTo(to: Point): Direction {
    return when (val diff = to.first - this.first to to.second - this.second) {
        -1 to 0 -> Direction.NORTH
        1 to 0 -> Direction.SOUTH
        0 to -1 -> Direction.WEST
        0 to 1 -> Direction.EAST
        else -> throw IllegalArgumentException("Unknown direction: $diff based on $this to $to")
    }
}

fun step(c: Char): Pair<Int, Int> {
    return when (c) {
        'v' -> Pair(0, -1)
        '^' -> Pair(0, 1)
        '<' -> Pair(-1, 0)
        '>' -> Pair(1, 0)
        else -> Pair(0, 0)
    }
}

fun Point.move(c: Char): Point {
    return when (c) {
        'v' -> this.copy(second = this.second - 1)
        '^' ->  this.copy(second = this.second + 1)
        '<' ->  this.copy(first = this.first - 1)
        '>' -> this.copy(first = this.first + 1)
        else -> this
    }
}

fun Point.manhattanDistanceTo(other: Point): Int {
    return abs(this.first - other.first) + abs(this.second - other.second)
}

infix operator fun Point.plus(p: Point) = Point(this.first + p.first, this.second + p.second)

//operator fun Pair<Int, Int>.plus(that: Pair<Int, Int>): Pair<Int, Int> {
//    return Pair(this.first + that.first, this.second + that.second)
//}
infix operator fun Point.minus(p: Point) = Point(this.first - p.first, this.second - p.second)
